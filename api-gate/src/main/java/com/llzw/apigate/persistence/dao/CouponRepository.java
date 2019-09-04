package com.llzw.apigate.persistence.dao;

import com.llzw.apigate.persistence.entity.Coupon;
import com.llzw.apigate.persistence.entity.User;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

  Iterable<Coupon> findAllByCustomer(User customer);

  Iterable<Coupon> findAllByCustomerAndName(User customer, String name);

  Optional<Coupon> findByIdAndCustomer(Long id, User customer);
}
