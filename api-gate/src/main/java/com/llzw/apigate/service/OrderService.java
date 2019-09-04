package com.llzw.apigate.service;

import com.llzw.apigate.message.error.RestApiException;
import com.llzw.apigate.message.error.RestEntityNotFoundException;
import com.llzw.apigate.persistence.dao.BundleRepository;
import com.llzw.apigate.persistence.dao.OrderRepository;
import com.llzw.apigate.persistence.entity.Bundle;
import com.llzw.apigate.persistence.entity.Order;
import com.llzw.apigate.persistence.entity.Product;
import com.llzw.apigate.persistence.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
  private ProductService productService;

  @Setter(onMethod_ = @Autowired)
  private BundleRepository bundleRepository;

  public Order createBundleOrder(User customer, String name, int quantity, float price)
      throws RestApiException {
    Order order = new Order();
    Bundle bundle = new Bundle();
    bundle.setName(name + "套餐");
    bundleRepository.save(bundle);
    for (int i = 0; i < quantity; ++i) {
      Product product = new Product();
      product.setName(name);
      product.setCustomer(customer);
      product.setBundle(bundle);
      productService.save(product);
    }
    order.setBundle(bundle);
    order.setPrice(price);
    return orderRepository.save(order);
  }

  public Order createProductOrder(User customer, String name, float price)
      throws RestApiException {
    Order order = new Order();
    Product product = new Product();
    product.setName(name);
    product.setCustomer(customer);
    productService.save(product);
    order.setProduct(product);
    order.setPrice(price);
    return orderRepository.save(order);
  }

  public List<Order> search(User user) {
    return orderRepository.findAllByCustomer(user);
  }

  public Order get(String id, User relatedUser) throws RestApiException {
    return orderRepository.findByIdAndUser(UUID.fromString(id), relatedUser)
        .orElseThrow(() -> new RestEntityNotFoundException(
            String.format("Order <%s> does not exist or you do not have access to this entity", id))
        );
  }
}
