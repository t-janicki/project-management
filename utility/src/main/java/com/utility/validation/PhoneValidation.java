package com.utility.validation;

import com.utility.exception.BadRequestException;

import java.util.regex.Pattern;

public class PhoneValidation {

    public static boolean validatePhoneNumberFormat(String phone) {
        Pattern pattern = Pattern.compile("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$");
        boolean result = pattern.matcher(phone).matches();

        if (!result) {
            throw new BadRequestException("Incorrect phone number format. ");
        }
        return true;
    }
}
