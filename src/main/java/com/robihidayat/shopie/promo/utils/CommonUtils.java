package com.robihidayat.shopie.promo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@Component
public class CommonUtils {

    public String getSingleErrorMessage(List<FieldError> errorFields) {
        if (errorFields != null && !errorFields.isEmpty()) {
            for (FieldError fieldError : errorFields) {
                return fieldError.getDefaultMessage();
            }
        }
        return null;
    }

    public String sha1(String text) {
        try {
            java.security.MessageDigest md;
            md = java.security.MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            byte[] sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            log.error("Hash SHA1 : {}", ex);
        }
        return "";
    }

    public String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int twoHalfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (twoHalfs++ < 1);
        }
        return buf.toString();
    }
}
