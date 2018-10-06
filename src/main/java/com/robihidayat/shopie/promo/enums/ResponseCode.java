package com.robihidayat.shopie.promo.enums;

public enum ResponseCode {

    SUCCESS("0000"),
    INSUFFICIENT_PARAMS("0001"),
    INVALID_WORDS("0002"),
    ;

    private String value;

    ResponseCode(String value) {
        this.value=value;
    }

    /**
     *
     * @return
     */
    public String value() {
        return value;
    }
}
