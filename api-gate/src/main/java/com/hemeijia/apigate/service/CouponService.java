package com.hemeijia.apigate.service;

import com.hemeijia.apigate.message.error.RestEntityNotFoundException;
import com.hemeijia.apigate.persistence.dao.CouponRepository;
import com.hemeijia.apigate.persistence.entity.Coupon;
import com.hemeijia.apigate.persistence.entity.Package;
import com.hemeijia.apigate.persistence.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {

  @Setter(onMethod_ = @Autowired)
  private CouponRepository couponRepository;

  @Transactional
  public List<Coupon> create(User customer, String name, float price, int quantity, Package pkg) {
    List<Coupon> coupons = new ArrayList<>(quantity);
    for (int i = 0; i < quantity; ++i) {
      Coupon coupon = new Coupon();
      coupon.setCustomer(customer);
      coupon.setName(name);
      coupon.setPrice(price);
      coupon.setPkg(pkg);
      coupons.add(coupon);
    }
    List<Coupon> couponsSaved = new ArrayList<>();
    couponRepository.saveAll(coupons).forEach(couponsSaved::add);
    return couponsSaved;
  }

  public List<Coupon> search(User customer, String name, boolean nopkg) {
    Stream<Coupon> all = couponRepository.findAllByCustomer(customer).stream();
    if (name != null && !name.isEmpty()) {
      all = all.filter((cp) -> cp.getName().equals(name));
    }
    if (nopkg) {
      all = all.filter((cp) -> cp.getPkg() == null);
    }
    return all.collect(Collectors.toList());
  }

  public Coupon get(User customer, Long id) throws RestEntityNotFoundException {
    return couponRepository.findByIdAndCustomer(id, customer)
        .orElseThrow(() -> new RestEntityNotFoundException(
            String
                .format("Coupon <%s> does not exist or you do not have access to this entity", id))
        );
  }
}
