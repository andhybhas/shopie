package com.robihidayat.shopie.promo.enums;

public enum TaxCodeEnum {

    FOOD("1"),
    TOBACO("2"),
    ENTERTAIMENT("3");

    private String value;

    TaxCodeEnum(String code) {
        this.value = code;
    }

    public String value() {
        return value;
    }

    public static TaxCodeEnum findByCode(String code){
        for(TaxCodeEnum v : values()){
            if( v.value.equals(code)){
                return v;
            }
        }
        return null;
    }
}
