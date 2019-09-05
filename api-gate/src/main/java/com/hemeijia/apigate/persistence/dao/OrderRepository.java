package com.hemeijia.apigate.persistence.dao;

import com.hemeijia.apigate.persistence.entity.Order;
import com.hemeijia.apigate.persistence.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository
    extends PagingAndSortingRepository<Order, UUID> {

  List<Order> findAllByCustomer(User user);

  Optional<Order> findByIdAndCustomer(Long id, User customer);
}
