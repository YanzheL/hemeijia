package com.hemeijia.apigate.web.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

  protected String username;

  @NotNull
  protected String name;

  protected String phoneNumber;

}
