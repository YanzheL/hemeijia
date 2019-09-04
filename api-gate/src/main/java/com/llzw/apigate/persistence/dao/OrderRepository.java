package com.llzw.apigate.persistence.dao;

import com.llzw.apigate.persistence.entity.Order;
import com.llzw.apigate.persistence.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository
    extends PagingAndSortingRepository<Order, UUID> {

  List<Order> findAllByCustomer(User user);

  Optional<Order> findByIdAndCustomer(Long id, User customer);
}
