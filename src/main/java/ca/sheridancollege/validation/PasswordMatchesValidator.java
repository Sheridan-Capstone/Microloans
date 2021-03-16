package ca.sheridancollege.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ca.sheridancollege.beans.User;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    
    public void initialize(final PasswordMatches constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final User user = (User) obj;
        return user.getPassword().equals(user.getcPassword());
    }

}