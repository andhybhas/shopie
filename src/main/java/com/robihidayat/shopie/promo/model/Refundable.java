package com.robihidayat.shopie.promo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Refundable {
    private BigDecimal tax;
    private String refundable;

}
