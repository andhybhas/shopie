package com.robihidayat.shopie.promo.exception;

import com.robihidayat.shopie.promo.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ValidationApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    private ResponseCode responseCode;

}
