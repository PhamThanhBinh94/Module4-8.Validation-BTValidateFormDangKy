package com.codegym.model;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class Email implements Validator {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String number) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Email.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Email email1 = (Email) target;
        String email = email1.getEmail();
        ValidationUtils.rejectIfEmpty(errors,"email", "email.empty");
//        if (email.length()>11 || email.length()<10){
//            errors.rejectValue("email", "email.length");
//        }
//        if (!email.startsWith("0")){
//            errors.rejectValue("email", "email.startsWith");
//        }
        if (!email.matches("/^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$/")){
            errors.rejectValue("email", "email.matches");
        }
    }
}
