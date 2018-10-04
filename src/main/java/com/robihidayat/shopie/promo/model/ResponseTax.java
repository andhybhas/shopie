package com.robihidayat.shopie.promo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseTax {
    private String name;
    private String taxCode;
    private String type;
    private String refundable;
    private String price;
    private String tax;
    private String amount;
}
