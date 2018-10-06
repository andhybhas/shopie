package com.robihidayat.shopie.promo.enums;

public enum TaxCodeEnum {

    FOOD("1","FOOD"),
    TOBACO("2", "TOBACO"),
    ENTERTAIMENT("3", "ENTERTAIMENT");

    private String value;
    private String type;

    TaxCodeEnum(String code, String type) {
        this.value = code;
        this.type = type;
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

    public String type(){
        return type;
    }
}
