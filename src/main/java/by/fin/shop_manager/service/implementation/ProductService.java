package by.fin.shop_manager.service.implementation;

import by.fin.shop_manager.core.dto.ImageDto;
import by.fin.shop_manager.core.dto.ProductCreateDto;
import by.fin.shop_manager.core.dto.ProductDto;
import by.fin.shop_manager.dao.api.IProductDao;
import by.fin.shop_manager.dao.entity.Product;
import by.fin.shop_manager.dao.entity.ProductImage;
import by.fin.shop_manager.exception.BadRequestException;
import by.fin.shop_manager.exception.ProductNotFoundException;
import by.fin.shop_manager.mapper.ProductMapper;
import by.fin.shop_manager.service.api.IImageService;
import by.fin.shop_manager.service.api.IProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final IProductDao productDao;
    private final ProductMapper productMapper;
    private final IImageService imageService;

    private static final String PRODUCT_UPDATE_DISCREPANCY = "The product information in the database does not match the provided details. Please update the product information.";

    public ProductService(IProductDao productDao, ProductMapper productMapper, IImageService imageService) {
        this.productDao = productDao;
        this.productMapper = productMapper;
        this.imageService = imageService;
    }

    @Transactional
    @Override
    public long saveProduct(ProductCreateDto productCreateDto) {
        logger.info("Saving product: {}", productCreateDto.getName());

        Product product = productMapper.toEntity(productCreateDto);
        Product savedProduct = productDao.save(product);

        logger.info("Product saved with ID: {}", savedProduct.getId());

        return savedProduct.getId();
    }

    @Transactional
    @Override
    public void updateProduct(long id, ProductCreateDto productCreateDto) {
        logger.info("Searching product with ID: {}", id);

        Product product = productDao.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("with id: " + id));

        logger.info("Updating product with ID: {}", id);

        productMapper.updateProductFromDto(productCreateDto, product);
        productDao.save(product);

        logger.info("Product updated and saved with ID: {}", product.getId());
    }

    @Transactional
    @Override
    public void updateProductImages(long productId, List<ImageDto> images) {
        logger.info("Updating images for product with ID: {}", productId);

        Product product = this.getProductById(productId);
        processAndSaveImages(images, product);
        productDao.save(product);

        logger.info("Images updated for product with ID: {}", productId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {
        logger.info("Fetching all products");

        List<Product> products = productDao.findAll();
        if (products.isEmpty()) {
            logger.warn("No products found");
            throw new ProductNotFoundException();
        }

        logger.info("Fetched {} products", products.size());

        return products;
    }

    @Override
    public Product getProductById(long id) {
        logger.info("Fetching product with ID: {}", id);

        return productDao.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Product with ID {} not found", id);
                    return new ProductNotFoundException("with id: " + id);
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductByName(String name) {
        logger.info("Fetching product with name: {}", name);

        return productDao.findByName(name)
                .orElseThrow(() -> {
                    logger.warn("Product with name {} not found", name);
                    return new ProductNotFoundException("with name: " + name);
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Product getHighestRatedProduct() {
        logger.info("Fetching highest rated product");

        return productDao.findTopByRatingDesc(PageRequest.of(0, 1))
                .stream().findFirst()
                .orElseThrow(() -> {
                    logger.warn("No highest rated product found");
                    return new ProductNotFoundException();
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Product getMostExpensiveProduct() {
        logger.info("Fetching most expensive product");

        return productDao.findTopByPriceDesc(PageRequest.of(0, 1))
                .stream().findFirst()
                .orElseThrow(() -> {
                    logger.warn("No most expensive product found");
                    return new ProductNotFoundException();
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Product getCheapestProduct() {
        logger.info("Fetching cheapest product");

        return productDao.findTopByPriceAsc(PageRequest.of(0, 1))
                .stream().findFirst()
                .orElseThrow(() -> {
                    logger.warn("No cheapest product found");
                    return new ProductNotFoundException();
                });
    }

    @Override
    public void deleteProduct(Long id, ProductDto productDto) {
        logger.info("Deleting product with ID: {}", id);
        Product product = productDao.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Product with ID {} not found", id);
                    return new ProductNotFoundException("with id: " + id);
                });

        ProductDto productInDB = productMapper.toDto(product);

        if (!productDto.equals(productInDB)) {
            logger.warn("Product information discrepancy: provided DTO does not match the database entry");
            throw new BadRequestException(PRODUCT_UPDATE_DISCREPANCY);
        }

        productDao.delete(product);
        logger.info("Product with ID {} deleted", id);
    }

    private void processAndSaveImages(List<ImageDto> imageDtoList, Product product) {

        if(imageDtoList == null || imageDtoList.isEmpty()) return;

        product.getImages().clear();
        for(ImageDto imageDto : imageDtoList) {
            imageService.removeBackground(imageDto);
            ProductImage pi = imageService.saveImage(imageDto, product);
            product.addImage(pi);
        }
    }

}
