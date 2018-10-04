package com.robihidayat.shopie.promo.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Setter
@Getter
public class TaxForm {

    @NotNull
    private String name;
    @NotNull
    private String taxCode;
    @NotNull
    private Integer price;
    @NotNull
    private String words;
}
