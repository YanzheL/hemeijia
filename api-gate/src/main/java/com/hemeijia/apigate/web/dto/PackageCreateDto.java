package com.hemeijia.apigate.web.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class PackageCreateDto {

  @NotNull
  protected String name;
  protected float price;
  protected List<CoupPack> coupPacks;

  @Data
  public static class CoupPack {

    @NotNull
    protected String name;

    @Positive
    protected int quantity;
  }
}
