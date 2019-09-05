package com.llzw.apigate.web.controller;

import com.llzw.apigate.message.RestResponseEntityFactory;
import com.llzw.apigate.message.error.RestApiException;
import com.llzw.apigate.service.UserService;
import com.llzw.apigate.web.dto.UserDto;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  @GetMapping
  public ResponseEntity get(String username) throws RestApiException {
    return RestResponseEntityFactory.success(userService.loadUserByUsername(username));
  }

  @PostMapping(value = "/register")
  public ResponseEntity register(@RequestBody UserDto userDto) throws RestApiException {
    LOGGER.debug("Registering user account with information: {}", userDto);
    return RestResponseEntityFactory.success(userService.register(userDto));
  }

}
