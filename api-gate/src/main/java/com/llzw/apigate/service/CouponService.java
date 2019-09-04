package com.llzw.apigate.service;

import com.llzw.apigate.message.error.RestEntityNotFoundException;
import com.llzw.apigate.persistence.dao.CouponRepository;
import com.llzw.apigate.persistence.entity.Coupon;
import com.llzw.apigate.persistence.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

public class CouponService {

  @Setter(onMethod_ = @Autowired)
  private CouponRepository couponRepository;

  public Iterable<Coupon> create(User customer, String name, float price, int quantity) {
    List<Coupon> coupons = new ArrayList<>(quantity);
    for (int i = 0; i < quantity; ++i) {
      Coupon coupon = new Coupon();
      coupon.setCustomer(customer);
      coupon.setName(name);
      coupon.setPrice(price);
      coupons.add(coupon);
    }
    return couponRepository.saveAll(coupons);
  }

  public Iterable<Coupon> search(User customer, String name) {
    if (name == null || name.isEmpty()) {
      return couponRepository.findAllByCustomer(customer);
    } else {
      return couponRepository.findAllByCustomerAndName(customer, name);
    }
  }

  public Coupon get(User customer, Long id) throws RestEntityNotFoundException {
    return couponRepository.findByIdAndCustomer(id, customer)
        .orElseThrow(() -> new RestEntityNotFoundException(
            String
                .format("Coupon <%s> does not exist or you do not have access to this entity", id))
        );
  }
}
