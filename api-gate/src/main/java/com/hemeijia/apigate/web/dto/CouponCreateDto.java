package com.hemeijia.apigate.web.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CouponCreateDto {

  @NotNull
  protected String name;

  protected float price;
}
