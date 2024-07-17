package by.fin.shop_manager.controller;

import by.fin.shop_manager.core.dto.ImageDto;
import by.fin.shop_manager.core.dto.ProductCreateDto;
import by.fin.shop_manager.core.dto.ProductDto;
import by.fin.shop_manager.dao.entity.Product;
import by.fin.shop_manager.mapper.ProductMapper;
import by.fin.shop_manager.service.api.IProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductMapper productMapper;
    private final IProductService productService;

    public ProductController(ProductMapper productMapper, IProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductCreateDto productCreateDto) {
        logger.info("Received request to create product: {}", productCreateDto.getName());
        long productId = productService.saveProduct(productCreateDto);
        logger.info("Product created successfully with ID: {}", productId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductCreateDto productCreateDto) {
        logger.info("Received request to update product with ID: {}", id);
        productService.updateProduct(id, productCreateDto);
        logger.info("Product updated successfully with ID: {}", id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/update_images")
    public ResponseEntity<?> updateProductImages(@PathVariable long id, @RequestBody List<ImageDto> list) {
        logger.info("Received request to update images for product with ID: {}", id);
        productService.updateProductImages(id, list);
        logger.info("Images successfully updated for product with ID: {}", id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        logger.info("Received request to get list of all products.");
        List<Product> products = productService.getAllProducts();
        List<ProductDto> result = productMapper.toDtoList(products);
        logger.info("Fetched {} products.", result.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        logger.info("Received request to get product with ID: {}", id);
        Product product = productService.getProductById(id);
        ProductDto productDto = productMapper.toDto(product);
        logger.info("Fetched product with ID: {}", id);

        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/highest-rated")
    public ResponseEntity<ProductDto> getHighestRatedProduct() {
        logger.info("Received request to get product with the highest rating.");
        Product product = productService.getHighestRatedProduct();
        ProductDto productDto = productMapper.toDto(product);
        logger.info("Fetched highest rated product with ID: {}", product.getId());

        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/most-expensive")
    public ResponseEntity<ProductDto> getMostExpensiveProduct() {
        logger.info("Received request to get product with the highest price.");
        Product product = productService.getMostExpensiveProduct();
        ProductDto productDto = productMapper.toDto(product);
        logger.info("Fetched most expensive product with ID: {}", product.getId());

        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/cheapest")
    public ResponseEntity<ProductDto> getCheapestProduct() {
        logger.info("Received request to get product with the lowest price.");
        Product product = productService.getCheapestProduct();
        ProductDto productDto = productMapper.toDto(product);
        logger.info("Fetched cheapest product with ID: {}", product.getId());

        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        logger.info("Received request to delete product with ID: {}", id);
        productService.deleteProduct(id, productDto);
        logger.info("Product deleted successfully with ID: {}", id);

        return ResponseEntity.noContent().build();
    }

}
