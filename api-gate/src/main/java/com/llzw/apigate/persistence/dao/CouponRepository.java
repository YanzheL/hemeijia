package com.llzw.apigate.persistence.dao;

import com.llzw.apigate.persistence.entity.Coupon;
import com.llzw.apigate.persistence.entity.User;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

  Stream<Coupon> findAllByCustomer(User customer);

  Stream<Coupon> findAllByCustomerAndNameOrderByCreatedAt(User customer, String name);

  Optional<Coupon> findByIdAndCustomer(Long id, User customer);
}
