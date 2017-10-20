package com.emirci.envanter.validator;

import com.emirci.envanter.model.AppUser;
import com.emirci.envanter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by serdaremirci on 9/19/17.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass) {

        return AppUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        AppUser user = (AppUser) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "form.validate.NotEmpty");

        if (user.getName().length() < 2 || user.getName().length() > 15) {
            errors.rejectValue("name", "form.validate.size.firstname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "form.validate.NotEmpty");
        if (user.getName().length() < 2 || user.getName().length() > 15) {
            errors.rejectValue("lastName", "form.validate.size.lastname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.validate.NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "form.validate.size.username");
        }

        if (userService.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "form.validate.duplicate.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.validate.NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "form.validate.size.password");
        }

        if (!user.getPassword().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "form.validate.passwordConfirm");
        }

    }
}
