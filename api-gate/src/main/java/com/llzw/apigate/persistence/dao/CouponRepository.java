package com.llzw.apigate.persistence.dao;

import com.llzw.apigate.persistence.entity.Coupon;
import com.llzw.apigate.persistence.entity.User;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CouponRepository extends
    PagingAndSortingRepository<Coupon, Long> {

  List<Coupon> findAllByCustomer(User customer);
}
