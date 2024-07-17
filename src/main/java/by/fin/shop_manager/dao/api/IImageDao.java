package by.fin.shop_manager.dao.api;

import by.fin.shop_manager.dao.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageDao extends JpaRepository<ProductImage, Long> {

    @Query("SELECT pi FROM ProductImage pi WHERE pi.product = :productId")
    List<ProductImage> findAllImagesByProductId(@Param("productId") long productId);
}
