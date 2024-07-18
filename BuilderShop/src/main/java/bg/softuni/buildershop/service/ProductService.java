package bg.softuni.buildershop.service;

import bg.softuni.buildershop.model.dto.AddProductDTO;
import bg.softuni.buildershop.model.dto.FavoriteProductDTO;
import bg.softuni.buildershop.model.dto.ProductSummaryDTO;
import bg.softuni.buildershop.model.entity.CategoryEntity;
import bg.softuni.buildershop.model.entity.ProductEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.repository.ProductRepository;
import bg.softuni.buildershop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.*;

import static java.util.stream.Collectors.toList;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private static String UPLOADED_FOLDER = "C://temp//";
    private final ExRateService exRateService;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserService userService, ModelMapper modelMapper, CategoryService categoryService, ExRateService exRateService, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.exRateService = exRateService;
        this.userRepository = userRepository;
    }

    public ProductEntity getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public void addProduct(AddProductDTO addProductDTO, Long userId) throws IOException {
        UserEntity userEntity = this.userService.findUserById(userId).orElse(null);
        CategoryEntity categoryEntity = this.categoryService.findCategory(addProductDTO.getCategory());
        ProductEntity productEntity = new ProductEntity();
        categoryEntity.getProducts().add(productEntity);
        productEntity.setAuthor(userEntity);
        productEntity.setCategory(categoryEntity);
        productEntity.setDescription(addProductDTO.getDescription());
        productEntity.setName(addProductDTO.getName());
        productEntity.setPrice(addProductDTO.getPrice());
        userEntity.getProducts().add(productEntity);
        MultipartFile image = addProductDTO.getImage();
        byte[] bytes = image.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + image.getOriginalFilename());
        Files.write(path, bytes);

        productEntity.setImage("static/images/" + image.getOriginalFilename());
        productRepository.save(productEntity);
    }

    @Transactional
    public void removeProductById(Long id, Principal principal) {
        ProductEntity productEntity = this.productRepository.findById(id).orElse(null);
        if (productEntity == null) {
            return;
        }
        Long principalId = this.userService.getUserIdFromPrincipal(principal);
        Long ownerId = productEntity.getAuthor().getId();
        UserEntity loggedUser = this.userService.findUserById(principalId).orElse(null);
        boolean isAdmin = false;
        for (UserRoleEntity role : loggedUser.getRoles()) {
            if (role.getRole().name().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }
        if (Objects.equals(principalId, ownerId) || isAdmin) {
            productEntity.getAuthor().getProducts().remove(productEntity);
            productEntity.setAuthor(null);
            productEntity.setCategory(null);

            List<UserEntity> users = this.userRepository.findAll();
            for (UserEntity user : users) {
                Iterator<ProductEntity> iterator = user.getFavoriteProducts().iterator();
                while (iterator.hasNext()) {
                    ProductEntity favoriteProduct = iterator.next();
                    if (favoriteProduct.equals(productEntity)) {
                        iterator.remove();
                    }
                }
            }

            userRepository.saveAll(users);
            productRepository.delete(productEntity);
        }
    }

    public List<FavoriteProductDTO> getFavoriteProducts(Principal principal) {
        Long principalId = this.userService.getUserIdFromPrincipal(principal);
        UserEntity userEntity = this.userService.findUserById(principalId).orElse(null);

        return userEntity
                .getFavoriteProducts()
                .stream()
                .map(productEntity -> this.modelMapper.map(productEntity, FavoriteProductDTO.class))
                .toList();
    }

    public List<ProductSummaryDTO> getAllProducts(Principal principal) {

        return this.productRepository
                .findAllByAuthorUsernameNot(principal.getName())
                .stream()
                .map(p -> this.modelMapper.map(p, ProductSummaryDTO.class))
                .map(productSummaryDTO ->
                    productSummaryDTO.setCurrencies(exRateService.allSupportedRates()))
                .toList();
    }

    public void addProductToFavorite(Long id, Principal principal) {
        Long principalId = this.userService.getUserIdFromPrincipal(principal);
        UserEntity user = this.userService.findUserById(principalId).orElse(null);
        ProductEntity productEntity = this.productRepository.findById(id).orElse(null);
        for (ProductEntity favoriteProduct : user.getFavoriteProducts()) {
            if (favoriteProduct.getId().equals(productEntity.getId())) {
                return;
            }
        }
        if (user != null && productEntity != null) {
            user.getFavoriteProducts().add(productEntity);
            productEntity.getUsersWhoFavorited().add(user);
            userRepository.save(user);
        }
    }
    @Transactional
    public void removeProductFromFavorites(Long id, Principal principal) {
        ProductEntity productEntity = this.productRepository.findById(id).orElse(null);
        Long principalId = this.userService.getUserIdFromPrincipal(principal);
        UserEntity user = this.userService.findUserById(principalId).orElse(null);
        productEntity.getUsersWhoFavorited().remove(user);
        user.getFavoriteProducts().remove(productEntity);

        userRepository.save(user);
    }
}
