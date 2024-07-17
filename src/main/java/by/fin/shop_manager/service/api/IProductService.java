package by.fin.shop_manager.service.api;

import by.fin.shop_manager.core.dto.ImageDto;
import by.fin.shop_manager.core.dto.ProductCreateDto;
import by.fin.shop_manager.core.dto.ProductDto;
import by.fin.shop_manager.dao.entity.Product;

import java.util.List;

public interface IProductService {

    long saveProduct(ProductCreateDto productCreateDto);

    void updateProduct(long id, ProductCreateDto productCreateDto);

    void updateProductImages(long productId, List<ImageDto> images);

    List<Product> getAllProducts();

    Product getProductById(long id);

    Product getProductByName(String name);

    Product getHighestRatedProduct();

    Product getMostExpensiveProduct();

    Product getCheapestProduct();

    void deleteProduct(Long id, ProductDto productDto);

}
