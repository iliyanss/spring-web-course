package bg.softuni.buildershop;

import bg.softuni.buildershop.model.dto.AddProductDTO;
import bg.softuni.buildershop.model.dto.FavoriteProductDTO;
import bg.softuni.buildershop.model.dto.ProductSummaryDTO;
import bg.softuni.buildershop.model.entity.CategoryEntity;
import bg.softuni.buildershop.model.entity.ProductEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.model.enums.CategoryEnum;
import bg.softuni.buildershop.model.enums.UserRoleEnum;
import bg.softuni.buildershop.repository.ProductRepository;
import bg.softuni.buildershop.repository.UserRepository;
import bg.softuni.buildershop.repository.UserRoleRepository;
import bg.softuni.buildershop.service.CategoryService;
import bg.softuni.buildershop.service.ExRateService;
import bg.softuni.buildershop.service.ProductService;
import bg.softuni.buildershop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProductServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserDetailsService builderUserDetailsService;
    @Mock
    private UserRoleRepository roleRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserService userService;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private ProductService productService;
    @Mock
    private ExRateService exRateService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    // ProductService tests

    @Test
    void testGetProductById() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductEntity result = productService.getProductById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testAddProduct() throws IOException {
        AddProductDTO addProductDTO = new AddProductDTO();
        addProductDTO.setName("Test Product");
        addProductDTO.setDescription("Test Description");
        addProductDTO.setPrice(100.0);
        addProductDTO.setCategory(CategoryEnum.BOOKS);
        MultipartFile image = mock(MultipartFile.class);
        when(image.getBytes()).thenReturn(new byte[0]);
        when(image.getOriginalFilename()).thenReturn("image.jpg");
        addProductDTO.setImage(image);

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));

        CategoryEntity category = new CategoryEntity();
        category.setName(CategoryEnum.BOOKS);
        when(categoryService.findCategory(CategoryEnum.BOOKS)).thenReturn(category);

        try (var mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.write(any(Path.class), any(byte[].class))).thenReturn(null);

            ProductEntity productEntity = new ProductEntity();
            productEntity.setAuthor(user);
            productEntity.setCategory(category);
            productEntity.setDescription(addProductDTO.getDescription());
            productEntity.setName(addProductDTO.getName());
            productEntity.setPrice(addProductDTO.getPrice());
            productEntity.setImage("static/images/" + image.getOriginalFilename());

            when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
            when(userRepository.save(any(UserEntity.class))).thenReturn(user);
            productService.addProduct(addProductDTO, user.getId());

            verify(productRepository, times(1)).save(any(ProductEntity.class));
        }
    }

    @Test
    void testRemoveProductById() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        UserEntity user = new UserEntity();
        user.setId(1L);
        UserRoleEntity role = new UserRoleEntity();
        role.setRole(UserRoleEnum.USER);
        user.setRoles(List.of(role));

        when(userService.getUserIdFromPrincipal(principal)).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));

        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setAuthor(user);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.removeProductById(1L, principal);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testGetFavoriteProducts() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        UserEntity user = new UserEntity();
        user.setId(1L);
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        user.setFavoriteProducts(List.of(product));

        when(userService.getUserIdFromPrincipal(principal)).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));

        FavoriteProductDTO favoriteProductDTO = new FavoriteProductDTO();
        favoriteProductDTO.setId(1L);
        when(modelMapper.map(product, FavoriteProductDTO.class)).thenReturn(favoriteProductDTO);

        List<FavoriteProductDTO> result = productService.getFavoriteProducts(principal);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void testGetAllProducts() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        ProductEntity product = new ProductEntity();
        product.setId(1L);

        when(productRepository.findAllByAuthorUsernameNot("username")).thenReturn(List.of(product));

        ProductSummaryDTO productSummaryDTO = new ProductSummaryDTO();
        productSummaryDTO.setId(1L);
        when(modelMapper.map(product, ProductSummaryDTO.class)).thenReturn(productSummaryDTO);
        when(exRateService.allSupportedRates()).thenReturn(List.of("USD", "EUR"));

        List<ProductSummaryDTO> result = productService.getAllProducts(principal);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void testAddProductToFavorite() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFavoriteProducts(new ArrayList<>());

        when(userService.getUserIdFromPrincipal(principal)).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));

        ProductEntity product = new ProductEntity();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.addProductToFavorite(1L, principal);

        assertTrue(user.getFavoriteProducts().contains(product));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRemoveProductFromFavorites() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        UserEntity user = new UserEntity();
        user.setId(1L);
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        user.setFavoriteProducts(new ArrayList<>(List.of(product)));

        when(userService.getUserIdFromPrincipal(principal)).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.removeProductFromFavorites(1L, principal);

        assertFalse(user.getFavoriteProducts().contains(product));
        verify(userRepository, times(1)).save(user);
    }
}
