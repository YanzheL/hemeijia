package com.hemeijia.apigate.web.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class OrderCreateDto {

  protected Long couponId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  protected Date startAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  protected Date endAt;

  protected String name;

  protected String mark;

  @NotNull
  protected String address;
}
