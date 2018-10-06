package com.robihidayat.shopie.promo.services;

import com.robihidayat.shopie.promo.enums.BaseResponse;
import com.robihidayat.shopie.promo.enums.ResponseCode;
import com.robihidayat.shopie.promo.enums.TaxCodeEnum;
import com.robihidayat.shopie.promo.model.Refundable;
import com.robihidayat.shopie.promo.model.ResponseTax;
import com.robihidayat.shopie.promo.model.TaxForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Slf4j
public class TaxServices {


    public ResponseTax doGetTaxFromUser(TaxForm form) {
        BigDecimal amount = BigDecimal.valueOf(form.getPrice());
        Refundable tax = getTax(amount, form.getTaxCode());
        ResponseTax responseTax = new ResponseTax();
        responseTax.setName(form.getName());
        responseTax.setPrice(form.getPrice().toString());
        responseTax.setAmount(amount.toString());
        responseTax.setTax(tax.getTax().toString());
        responseTax.setRefundable(tax.getRefundable());
        responseTax.setType(TaxCodeEnum.findByCode(form.getTaxCode()).type());
        responseTax.setTaxCode(TaxCodeEnum.findByCode(form.getTaxCode()).value());
        responseTax.setResponseCode(ResponseCode.SUCCESS.value());
        responseTax.setResponseMessage("SUCCESS");
        return responseTax;
    }


    private Refundable getTax(BigDecimal amount, String type) {
        Refundable refundable = new Refundable();
        BigDecimal percentage = BigDecimal.ZERO;
        String refund = null;
        switch (TaxCodeEnum.findByCode(type)){
            case FOOD:
                percentage = calculateTaxFood(amount);
                refund = "YES";
                break;
            case TOBACO:
                percentage = calculateTaxTobaco(amount);
                refund = "NO";
                break;
            case ENTERTAIMENT:
                percentage = calculateTaxEntertain(amount);
                refund = "NO";

                break;
        }
        refundable.setRefundable(refund);
        refundable.setTax(percentage);
        return refundable;
    }

    private BigDecimal calculateTaxFood(BigDecimal amount){
        log.info("Calculate Tax Food");
        return amount.multiply(BigDecimal.valueOf(0.01));
    }

    private BigDecimal calculateTaxTobaco(BigDecimal amount){
        log.info("Calculate Tax Tobaco");
        return amount.multiply(BigDecimal.valueOf(0.02)).add(BigDecimal.valueOf(10));
    }

    private BigDecimal calculateTaxEntertain(BigDecimal amount){
        log.info("Calculate Tax Entertain");
        BigDecimal tax = BigDecimal.ZERO;
        int compareDown = amount.compareTo(BigDecimal.valueOf(0));
        int compareUp = amount.compareTo(BigDecimal.valueOf(100));
        if (compareDown < 0 && compareUp < 0){
            return tax;
        }
        return amount.subtract(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(0.01));
    }
}


