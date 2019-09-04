package com.llzw.apigate.web.controller;

import com.llzw.apigate.message.RestResponseEntityFactory;
import com.llzw.apigate.message.error.RestApiException;
import com.llzw.apigate.persistence.entity.User;
import com.llzw.apigate.service.PackageService;
import com.llzw.apigate.web.dto.PackageCreateDto;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Validated
@Controller
@ResponseBody
@RequestMapping(value = "${spring.data.rest.base-path}/packages")
public class PackageController {

  @Setter(onMethod_ = @Autowired)
  private PackageService packageService;

  @PreAuthorize("hasRole('CUSTOMER')")
  @Transactional
  @PostMapping
  public ResponseEntity create(@RequestBody PackageCreateDto dto) {
    User currentUser =
        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return RestResponseEntityFactory.success(
        packageService.create(currentUser, dto.getName(), dto.getPrice(), dto.getCoupPacks())
    );
  }

  @PreAuthorize("hasRole('CUSTOMER')")
  @GetMapping
  public ResponseEntity search(String name) {
    User currentUser =
        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return RestResponseEntityFactory.success(
        packageService.search(currentUser, name)
    );
  }

  @PreAuthorize("hasRole('CUSTOMER')")
  @GetMapping(value = "/{id}")
  public ResponseEntity get(@PathVariable("id") Long id) throws RestApiException {
    User currentUser =
        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return RestResponseEntityFactory.success(
        packageService.get(currentUser, id)
    );
  }
}
