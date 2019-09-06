package com.hemeijia.apigate.web.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CouponSearchDto {

  @NotNull
  protected String name;

  protected boolean nopkg = false;
}
