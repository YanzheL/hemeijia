package com.hemeijia.apigate.persistence.dao;

import com.hemeijia.apigate.persistence.entity.Coupon;
import com.hemeijia.apigate.persistence.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

  List<Coupon> findAllByCustomer(User customer);

  List<Coupon> findAllByCustomerAndNameOrderByCreatedAt(User customer, String name);

  Optional<Coupon> findByIdAndCustomer(Long id, User customer);
}
