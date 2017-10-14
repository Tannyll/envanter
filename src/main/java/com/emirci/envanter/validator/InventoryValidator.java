package com.emirci.envanter.validator;

import com.emirci.envanter.model.Inventory;
import com.emirci.envanter.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class InventoryValidator implements Validator {


    //private final Validator validatorInvoiceDate;

    @Autowired
    private InventoryService inventoryService;


    @Override
    public boolean supports(Class<?> aClass) {
        return Inventory.class.equals(aClass);
    }

    //@Qualifier("inventoryValidator")

/*    public InventoryValidator(Validator validatorInvoiceDate) {
        if (validatorInvoiceDate == null) {
            throw new IllegalArgumentException("the supplied [Validator] is required and must not be null.");
        }
        if (!validatorInvoiceDate.supports(Inventory.class)) {
            throw new IllegalArgumentException("the supplied [Validator] is required and must not be instance.");
        }
        this.validatorInvoiceDate = validatorInvoiceDate;

    }*/

    @Override
    public void validate(Object o, Errors errors) {

        Inventory inventory = (Inventory) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "form.validate.NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usesUser", "form.validate.NotEmpty");
        //ValidationUtils.invokeValidator(this.validatorInvoiceDate, inventory.getInvoiceDate(), errors);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invtyp.inventoryTypeId", "form.validate.NotEmpty");
        if (inventory.getInvtyp().getInventoryTypeId() == null) {
            errors.rejectValue("invtyp", "inventory.validate.inventoryTypes");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "trade.trademarkId", "form.validate.NotEmpty");
        if (inventory.getTrade().getTrademarkId() == null) {
            errors.rejectValue("trade", "inventory.validate.trademarks");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "depar.departmentId", "form.validate.NotEmpty");
        if (inventory.getDepar().getDepartmentId() == null) {
            errors.rejectValue("depar", "inventory.validate.department");
        }

//user.getEmail().length() < 6 || user.getEmail().length() > 32

    }
}
