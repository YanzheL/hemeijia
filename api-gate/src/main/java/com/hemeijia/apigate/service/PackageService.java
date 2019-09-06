package com.hemeijia.apigate.service;

import com.hemeijia.apigate.message.error.RestEntityNotFoundException;
import com.hemeijia.apigate.persistence.dao.PackageRepository;
import com.hemeijia.apigate.persistence.entity.Coupon;
import com.hemeijia.apigate.persistence.entity.Package;
import com.hemeijia.apigate.persistence.entity.User;
import com.hemeijia.apigate.web.dto.PackageCreateDto.CoupPack;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PackageService {

  @Setter(onMethod_ = @Autowired)
  private PackageRepository packageRepository;

  @Setter(onMethod_ = @Autowired)
  private CouponService couponService;

  @Transactional
  public Package create(
      User customer, String name, float price,
      List<CoupPack> coupPacks
  ) {
    Package pkg = new Package();
    pkg.setName(name);
    pkg.setPrice(price);
    pkg.setCustomer(customer);
    List<Coupon> coupons = new ArrayList<>();

    for (CoupPack pack : coupPacks) {
      couponService.create(customer, pack.getName(), 0, pack.getQuantity(), pkg)
          .forEach(coupons::add);
    }
    pkg.setCoupons(coupons);
    return packageRepository.save(pkg);
  }

  public Iterable<Package> search(User customer, String name) {
    if (name == null || name.isEmpty()) {
      return packageRepository.findAllByCustomer(customer);
    } else {
      return packageRepository.findAllByCustomerAndName(customer, name);
    }
  }

  public Package get(User customer, Long id) throws RestEntityNotFoundException {
    return packageRepository.findByIdAndCustomer(id, customer)
        .orElseThrow(() -> new RestEntityNotFoundException(
            String
                .format("Package <%s> does not exist or you do not have access to this entity", id))
        );
  }
}
