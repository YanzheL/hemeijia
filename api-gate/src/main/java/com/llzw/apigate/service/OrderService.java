package com.llzw.apigate.service;

import com.llzw.apigate.message.error.RestApiException;
import com.llzw.apigate.message.error.RestEntityNotFoundException;
import com.llzw.apigate.message.error.RestInvalidParameterException;
import com.llzw.apigate.persistence.dao.OrderRepository;
import com.llzw.apigate.persistence.entity.Coupon;
import com.llzw.apigate.persistence.entity.Order;
import com.llzw.apigate.persistence.entity.User;
import java.util.List;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

  @Setter(onMethod_ = @Autowired)
  private OrderRepository orderRepository;

  @Setter(onMethod_ = @Autowired)
  private CouponService couponService;

  public Order create(User customer, String name, Long couponId, String mark, String address)
      throws RestApiException {
    Coupon coupon;
    if (name == null && couponId == null) {
      throw new RestInvalidParameterException("'name' and 'couponId' cannot both be null");
    } else {
      if (couponId != null) {
        coupon = couponService.get(customer, couponId);
      } else {
        coupon = couponService.streamSearch(customer, name).findFirst().get();
      }
    }
    if (coupon == null) {
      throw new RestEntityNotFoundException("No coupon found");
    }
    Order order = new Order();
    order.setCustomer(customer);
    order.setAddress(address);
    order.setMark(mark);
    order.setCoupon(coupon);
    order.setStatus("已下单");
    return orderRepository.save(order);
  }

  public List<Order> search(User customer) {
    return orderRepository.findAllByCustomer(customer);
  }
}
