package com.llzw.apigate.persistence.dao;

import com.llzw.apigate.persistence.entity.Package;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PackageRepository extends PagingAndSortingRepository<Package, Long> {

}
