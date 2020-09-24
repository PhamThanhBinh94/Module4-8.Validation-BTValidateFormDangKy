package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserValidator.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String numberPhone = user.getNumberPhone();
        ValidationUtils.rejectIfEmpty(errors,"numberPhone", "numberPhone.empty");
        if (numberPhone.length()>11 || numberPhone.length()<10){
            errors.rejectValue("numberPhone", "numberPhone.length");
        }
        if (!numberPhone.startsWith("0")){
            errors.rejectValue("numberPhone", "numberPhone.startsWith");
        }
        if (!numberPhone.matches("(^$|[0-9]*$)")){
            errors.rejectValue("numberPhone", "numberPhone.matches");
        }
        String email = user.getEmail();
        ValidationUtils.rejectIfEmpty(errors,"email", "email.empty");
        if (!email.matches("/^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$/")){
            errors.rejectValue("email", "email.matches");
        }
    }

}
