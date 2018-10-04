package com.robihidayat.shopie.promo.api;


import com.robihidayat.shopie.promo.exception.ValidationApiException;
import com.robihidayat.shopie.promo.model.ResponseTax;
import com.robihidayat.shopie.promo.model.TaxForm;
import com.robihidayat.shopie.promo.services.TaxServices;
import com.robihidayat.shopie.promo.services.ValidationApiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("tax")
@Api("API SHOPIE TAX")
public class TaxApi {

    @Autowired
    ValidationApiService validation;

    @Autowired
    TaxServices taxServices;

    @PostMapping
    private ResponseEntity<ResponseTax> doTax(@RequestBody @Validated TaxForm form, BindingResult bindingResult) throws ValidationApiException {
        validation.taskValidation(form, bindingResult);
        ResponseTax responseTax = taxServices.doGetTaxFromUser(form);
        return ResponseEntity.ok(responseTax);
    }
}
