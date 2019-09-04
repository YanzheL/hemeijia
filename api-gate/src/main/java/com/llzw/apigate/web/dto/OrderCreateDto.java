package com.llzw.apigate.web.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCreateDto {

  protected Long couponId;

  protected String name;

  protected String mark;

  @NotNull
  protected String address;
}
