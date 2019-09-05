package com.hemeijia.apigate.service;

import com.hemeijia.apigate.message.error.RestApiException;
import com.hemeijia.apigate.message.error.RestEntityNotFoundException;
import com.hemeijia.apigate.message.error.RestInvalidParameterException;
import com.hemeijia.apigate.persistence.dao.OrderRepository;
import com.hemeijia.apigate.persistence.entity.Coupon;
import com.hemeijia.apigate.persistence.entity.Order;
import com.hemeijia.apigate.persistence.entity.User;
import java.util.List;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  @Setter(onMethod_ = @Autowired)
  private OrderRepository orderRepository;

  @Setter(onMethod_ = @Autowired)
  private CouponService couponService;

  @Transactional
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

  public Order get(User customer, Long id) throws RestEntityNotFoundException {
    return orderRepository.findByIdAndCustomer(id, customer)
        .orElseThrow(() -> new RestEntityNotFoundException(
            String
                .format("Coupon <%s> does not exist or you do not have access to this entity", id))
        );
  }
}
