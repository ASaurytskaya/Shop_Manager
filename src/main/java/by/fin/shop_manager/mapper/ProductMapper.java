package by.fin.shop_manager.mapper;

import by.fin.shop_manager.core.dto.ImageDto;
import by.fin.shop_manager.core.dto.ProductCreateDto;
import by.fin.shop_manager.core.dto.ProductDto;
import by.fin.shop_manager.dao.entity.Product;
import by.fin.shop_manager.dao.entity.ProductImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    private static final Logger logger = LoggerFactory.getLogger(ProductMapper.class);

    // Convert a list of Product entities to a list of ProductDtos
    public List<ProductDto> toDtoList(List<Product> products) {
        logger.info("Converting {} products to DTOs", products.size());

        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Convert ProductCreateDTO to Product
    public Product toEntity(ProductCreateDto productCreateDto) {
        logger.info("Converting ProductCreateDto to Product: {}", productCreateDto.getName());

        Product entity = new Product.Builder()
                .setName(productCreateDto.getName())
                .setBrand(productCreateDto.getBrand())
                .setModel(productCreateDto.getModel())
                .setCategory(productCreateDto.getCategory())
                .setColor(productCreateDto.getColor())
                .setPrice(productCreateDto.getPrice())
                .setWeight(productCreateDto.getWeight())
                .setAvailableUnits(productCreateDto.getAvailableUnits())
                .setRating(productCreateDto.getRating())
                .setWarranty(productCreateDto.getWarranty())
                .setFeatures(String.join(";", productCreateDto.getFeatures()))
                .build();

        logger.info("Product created: {}", entity.getId());

        return entity;
    }

    // Convert Product entity to ProductDto
    public ProductDto toDto(Product product) {
        logger.info("Converting Product to ProductDto: {}", product.getId());

        List<ImageDto> images = product.getImages().stream()
                .map(image -> new ImageDto(image.getName(), image.getImageData()))
                .collect(Collectors.toList());

        ProductDto dto = new ProductDto.Builder()
                .setId(product.getId())
                .setName(product.getName())
                .setBrand(product.getBrand())
                .setModel(product.getModel())
                .setCategory(product.getCategory())
                .setDescription(product.getDescription())
                .setColor(product.getColor())
                .setPrice(product.getPrice())
                .setWeight(product.getWeight())
                .setAvailableUnits(product.getAvailableUnits())
                .setRating(product.getRating())
                .setWarranty(product.getWarranty())
                .setFeatures(product.getFeatures())
                .setImages(images)
                .build();

        logger.info("Product DTO created: {}", dto.getId());

        return dto;
    }

    // Update existing Product entity from ProductCreateDTO
    public void updateProductFromDto(ProductCreateDto productCreateDto, Product product) {
        logger.info("Updating Product with ID: {}", product.getId());

        product.setName(productCreateDto.getName());
        product.setBrand(productCreateDto.getBrand());
        product.setModel(productCreateDto.getModel());
        product.setCategory(productCreateDto.getCategory());
        product.setColor(productCreateDto.getColor());
        product.setPrice(productCreateDto.getPrice());
        product.setWeight(productCreateDto.getWeight());
        product.setAvailableUnits(productCreateDto.getAvailableUnits());
        product.setRating(productCreateDto.getRating());
        product.setWarranty(productCreateDto.getWarranty());
        product.setFeatures(productCreateDto.getFeatures());
        product.generateDescription();

        logger.info("Product updated: {}", product.getId());
    }

}
