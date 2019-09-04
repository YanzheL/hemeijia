package com.llzw.apigate.web.controller;

import com.llzw.apigate.message.RestResponseEntityFactory;
import com.llzw.apigate.persistence.entity.User;
import com.llzw.apigate.service.CouponService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Validated
@Controller
@ResponseBody
@RequestMapping(value = "${spring.data.rest.base-path}/coupons")
public class CouponController {

  @Setter(onMethod_ = @Autowired)
  private CouponService couponService;

  @PreAuthorize("hasRole('CUSTOMER')")
  @Transactional
  public ResponseEntity create(String name, float price) {
    User currentUser =
        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return RestResponseEntityFactory.success(
        couponService.create(currentUser, name, price, 1)
    );
  }

//  @GetMapping
//  public ResponseEntity search() throws RestApiException {
//    User currentUser =
//        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//    return RestResponseEntityFactory.success(
//        couponRepository.findAllByCustomer(currentUser)
//    );
//  }
}
