package com.llzw.apigate.web.controller;

import com.llzw.apigate.message.RestResponseEntityFactory;
import com.llzw.apigate.message.error.RestApiException;
import com.llzw.apigate.message.error.RestInvalidParameterException;
import com.llzw.apigate.persistence.dao.ProductRepository;
import com.llzw.apigate.persistence.entity.User;
import com.llzw.apigate.service.OrderService;
import com.llzw.apigate.web.dto.OrderCreateDto;
import javax.validation.Valid;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

// @RepositoryRestController is fucking not working as expected, referenced issue: https://jira.spring.io/browse/DATAREST-972
// @RestController creates duplicate endpoints with and without base-path. The same issue as described above.
@Validated
@Controller
@ResponseBody
@RequestMapping(value = "${spring.data.rest.base-path}/orders")
@Transactional
public class ProductController {

  @Setter(onMethod_ = @Autowired)
  private ProductRepository productRepository;

  @GetMapping
  public ResponseEntity search() throws RestApiException {
    User currentUser =
        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return RestResponseEntityFactory.success(
        productRepository.findAllByCustomer(currentUser)
    );
  }

//  @GetMapping(value = "/{id}")
//  public ResponseEntity get(@PathVariable(value = "id") String id)
//      throws RestApiException {
//    User currentUser =
//        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//    return RestResponseEntityFactory.success(orderService.get(id, currentUser));
//  }

//  @PostMapping
//  public ResponseEntity use(@Valid @RequestBody OrderCreateDto orderCreateDto)
//      throws RestApiException {
//    User currentUser =
//        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//    switch (orderCreateDto.getType()) {
//      case "PRODUCT": {
//        return RestResponseEntityFactory.success(
//            orderService.createProductOrder(
//                currentUser,
//                orderCreateDto.getName(),
//                orderCreateDto.getPrice()
//            ),
//            HttpStatus.CREATED
//        );
//      }
//      case "BUNDLE": {
//        return RestResponseEntityFactory.success(
//            orderService.createBundleOrder(
//                currentUser,
//                orderCreateDto.getName(),
//                orderCreateDto.getQuantity(),
//                orderCreateDto.getPrice()
//            ),
//            HttpStatus.CREATED
//        );
//      }
//      default:
//        throw new RestInvalidParameterException("Unknown order type");
//    }
  }
}
