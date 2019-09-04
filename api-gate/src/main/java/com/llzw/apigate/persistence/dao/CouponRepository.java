package com.llzw.apigate.persistence.dao;

import com.llzw.apigate.persistence.entity.Coupon;
import com.llzw.apigate.persistence.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

  Iterable<Coupon> findAllByCustomer(User customer);

  Iterable<Coupon> findAllByCustomerAndName(User customer, String name);

}
