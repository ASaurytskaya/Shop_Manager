package by.fin.shop_manager.service.implementation;

import by.fin.shop_manager.core.dto.ImageDto;
import by.fin.shop_manager.core.dto.ImageProcessingRequestDto;
import by.fin.shop_manager.core.dto.ImageProcessingResponseDto;
import by.fin.shop_manager.dao.api.IImageDao;
import by.fin.shop_manager.dao.entity.Product;
import by.fin.shop_manager.dao.entity.ProductImage;
import by.fin.shop_manager.service.api.IImageService;
import by.fin.shop_manager.service.feign.IImageRedactorApi;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService implements IImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    private final IImageDao imageDao;
    private final IImageRedactorApi imageRedactor;

    @Value("${image.api_key}")
    private String apiKey;

    public ImageService(IImageDao imageDao, IImageRedactorApi imageRedactor) {
        this.imageDao = imageDao;
        this.imageRedactor = imageRedactor;
    }

    @Transactional
    @Override
    public ProductImage saveImage(ImageDto image, Product product) {
        logger.info("Saving image for product with ID: {}", product.getId());

        ProductImage imageEntity = new ProductImage(image.getName(), image.getData(), product);
        product.addImage(imageEntity);

        logger.info("Saved image for product with ID: {}", product.getId());

        return imageDao.save(imageEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ImageDto> getImagesForProduct(long productId) {
        logger.info("Fetching images for product ID: {}", productId);

        List<ProductImage> list = imageDao.findAllImagesByProductId(productId);
        if (list == null || list.isEmpty()) {
            logger.warn("No images found for product ID: {}", productId);
            return null;
        }

        logger.info("Fetched {} images for product ID: {}", list.size(), productId);

        return list.stream()
                .map(productImage -> new ImageDto(productImage.getName(), productImage.getImageData()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteImage(ImageDto imageDto, Product product) {
        logger.info("Deleting image for product with ID: {}", product.getId());

        ProductImage pi = new ProductImage(imageDto.getName(), imageDto.getData(), product);
        product.removeImage(pi);
        imageDao.delete(pi);

        logger.info("Delete image for product with ID: {}", product.getId());
    }

    @Override
    public void removeBackground(ImageDto image) {
        logger.info("Removing background from image: {}", image.getName());

        ImageProcessingRequestDto imageRequest = new ImageProcessingRequestDto();
        imageRequest.setImageFileB64(image.getData());

        try {
            ImageProcessingResponseDto responseDto = imageRedactor.processImage(apiKey, imageRequest);
            image.setData(responseDto.getImageData());
        } catch (FeignException e) {
            logger.error("Error processing image: {}", e.getMessage());
            throw new RuntimeException("Image processing failed", e);
        }

        logger.info("Background removed for image: {}", image.getName());
    }
}
