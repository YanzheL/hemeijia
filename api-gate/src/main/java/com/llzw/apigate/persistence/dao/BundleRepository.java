package com.llzw.apigate.persistence.dao;

import com.llzw.apigate.persistence.entity.Product;
import com.llzw.apigate.persistence.entity.Bundle;
import com.llzw.apigate.persistence.entity.User;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BundleRepository
    extends PagingAndSortingRepository<Bundle, Long>, JpaSpecificationExecutor<Bundle> {

  @Query("SELECT SUM(s.currentQuantity) FROM Bundle s WHERE s.product = ?1 AND s.inboundedAt IS NOT NULL AND s.valid = true")
  Integer countByProductAndInboundedAtNotNullAndValidTrue(Product product);

  // SELECT * FROM stocks
  // WHERE product == ?1 AND inbounded_at IS NOT NULL AND valid = true AND current_quantity > ?2
  // ORDER BY inbounded_at
  Stream<Bundle> findByProductAndInboundedAtNotNullAndValidTrueOrderByInboundedAt(Product product);

  Optional<Bundle> findByIdAndProductSeller(Long id, User user);
}
