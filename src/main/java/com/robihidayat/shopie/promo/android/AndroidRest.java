package com.robihidayat.shopie.promo.android;


import com.robihidayat.shopie.promo.exception.ValidationApiException;
import com.robihidayat.shopie.promo.model.RequestAndroid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("android")
public class AndroidRest {


    @PostMapping
    public RequestAndroid doPost(@RequestBody @Validated  RequestAndroid requestAndroid) throws ValidationApiException {
        return requestAndroid;
    }

}
