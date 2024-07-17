package bg.softuni.buildershop.config;

import bg.softuni.buildershop.model.dto.FavoriteProductDTO;
import bg.softuni.buildershop.model.dto.ProductSummaryDTO;
import bg.softuni.buildershop.model.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(ProductEntity.class, ProductSummaryDTO.class)
                .addMappings(mapper ->
                        mapper.map(src -> src.getCategory().getName(), ProductSummaryDTO::setCategory));
        modelMapper.createTypeMap(ProductEntity.class, FavoriteProductDTO.class)
                .addMappings(mapper ->
                        mapper.map(src -> src.getCategory().getName(), FavoriteProductDTO::setCategory));
        return modelMapper;
    }
}