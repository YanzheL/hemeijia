package com.hemeijia.apigate.persistence.dao;

import com.hemeijia.apigate.persistence.entity.Package;
import com.hemeijia.apigate.persistence.entity.User;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PackageRepository extends PagingAndSortingRepository<Package, Long> {

  Iterable<Package> findAllByCustomer(User customer);

  Iterable<Package> findAllByCustomerAndName(User customer, String name);

  Optional<Package> findByIdAndCustomer(Long id, User customer);
}
