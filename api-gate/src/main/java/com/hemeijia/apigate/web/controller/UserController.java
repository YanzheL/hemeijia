package com.hemeijia.apigate.web.controller;

import com.hemeijia.apigate.message.RestResponseEntityFactory;
import com.hemeijia.apigate.message.error.RestApiException;
import com.hemeijia.apigate.persistence.entity.User;
import com.hemeijia.apigate.service.UserService;
import com.hemeijia.apigate.web.dto.UserDto;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Validated
@Controller
@ResponseBody
@RequestMapping(value = "${spring.data.rest.base-path}/users")
@Transactional
public class UserController {

  private final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @Setter(onMethod_ = @Autowired)
  private UserService userService;

  @PreAuthorize("hasRole('CUSTOMER')")
  @GetMapping
  public ResponseEntity get(@AuthenticationPrincipal User currentUser) throws RestApiException {
    return RestResponseEntityFactory.success((User) (currentUser));
  }

  @PostMapping(value = "/register")
  public ResponseEntity register(@RequestBody UserDto userDto) throws RestApiException {
    LOGGER.debug("Registering user account with information: {}", userDto);
    return RestResponseEntityFactory.success(
        userService.register(userDto),
        HttpStatus.CREATED
    );
  }

}
