package by.fin.shop_manager.service.api;

import by.fin.shop_manager.core.dto.ImageDto;
import by.fin.shop_manager.dao.entity.Product;
import by.fin.shop_manager.dao.entity.ProductImage;

import java.util.List;

public interface IImageService {

    ProductImage saveImage(ImageDto image, Product product);

    List<ImageDto> getImagesForProduct(long productId);

    void deleteImage(ImageDto imageDto, Product product);

    void removeBackground(ImageDto image);
}
