package com.robihidayat.shopie.promo.services;

import com.robihidayat.shopie.promo.entity.Tax;
import com.robihidayat.shopie.promo.enums.ResponseCode;
import com.robihidayat.shopie.promo.enums.TaxCodeEnum;
import com.robihidayat.shopie.promo.exception.ValidationApiException;
import com.robihidayat.shopie.promo.model.Refundable;
import com.robihidayat.shopie.promo.model.ResponseTax;
import com.robihidayat.shopie.promo.model.TaxForm;
import com.robihidayat.shopie.promo.repository.TaxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;


@Service
@Slf4j
public class TaxServices {

    @Autowired
    private TaxRepository taxRepository;


    @Transactional(rollbackOn = ValidationApiException.class)
    public ResponseTax doGetTaxFromUser(TaxForm form)  {

        BigDecimal amount = BigDecimal.valueOf(form.getPrice());
        Refundable tax = getTax(amount, form.getTaxCode());

        ResponseTax responseTax = new ResponseTax();
        responseTax.setName(form.getName());
        responseTax.setPrice(form.getPrice().toString());
        responseTax.setAmount(amount.toString());
        responseTax.setTax(tax.getTax().toString());
        responseTax.setRefundable(tax.getRefund());
        responseTax.setType(TaxCodeEnum.findByCode(form.getTaxCode()).type());
        responseTax.setTaxCode(TaxCodeEnum.findByCode(form.getTaxCode()).value());
        responseTax.setResponseCode(ResponseCode.SUCCESS.value());
        responseTax.setResponseMessage("SUCCESS");
        saveToEntity(form.getName(), BigDecimal.valueOf(form.getPrice()), TaxCodeEnum.findByCode(form.getTaxCode()).value() );
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
        refundable.setRefund(refund);
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

    @Transactional(rollbackOn = ValidationApiException.class)
    public void saveToEntity(String name, BigDecimal price, String taxCode){
        Tax tax = new Tax();
        tax.setName(name);
        tax.setPrice(price);
        tax.setTaxCode(taxCode);
        taxRepository.save(tax);
    }
}


