package by.fin.shop_manager.dao.api;

import by.fin.shop_manager.dao.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductDao extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    List<Product> findTopByRatingDesc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> findTopByPriceDesc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findTopByPriceAsc(Pageable pageable);

    Optional<Product> findByName(String name);
}
