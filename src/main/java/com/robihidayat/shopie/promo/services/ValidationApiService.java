package com.robihidayat.shopie.promo.services;

import com.robihidayat.shopie.promo.enums.ResponseCode;
import com.robihidayat.shopie.promo.exception.ValidationApiException;
import com.robihidayat.shopie.promo.model.TaxForm;
import com.robihidayat.shopie.promo.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Slf4j
@Service
public class ValidationApiService {

    @Autowired
    CommonUtils commonUtils;

    @Value("${shopie.api.tax.shared.key}")
    private String shareKey;


    private void defaultValidation(BindingResult bindingResult) throws ValidationApiException {
        log.debug("Default validation ...");
        if (bindingResult.hasErrors()) {
            log.error("Error Default Validation");
            throw new ValidationApiException(commonUtils.getSingleErrorMessage(bindingResult.getFieldErrors()), ResponseCode.INSUFFICIENT_PARAMS);
        }
    }

    public void taskValidation(TaxForm form, BindingResult bindingResult) throws ValidationApiException {
        log.debug(" :: DO VALIDATION TAX ::");
        defaultValidation(bindingResult);
        StringBuilder element = new StringBuilder(shareKey);
        element.append(form.getName());
        element.append(form.getPrice());
        element.append(form.getTaxCode());
        validateWordsSHA1(element.toString(), form.getWords());
    }


    private void validateWordsSHA1(String element, String clientWords) throws ValidationApiException {
        log.debug("Validate words SHA1 ...");
        String words = commonUtils.sha1(element);
        if (!words.equalsIgnoreCase(clientWords)) {
            log.error("Invalid words");
            log.info("wordsElements   [{}]", element);
            log.info("words           [{}]", words);
            log.info("clientWords     [{}]", clientWords);
            throw new ValidationApiException("Words not Match", ResponseCode.INVALID_WORDS);
        }
    }
}
