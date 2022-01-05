package webmvc.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MemberSignupRequestValidator implements Validator {
    private static final String emailRegExp =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern;
    
    public MemberSignupRequestValidator() {
        pattern = Pattern.compile(emailRegExp);
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return MemberSignupRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberSignupRequest req = (MemberSignupRequest) target;
        if (req.getEmail() == null || req.getEmail().isBlank()) {
            errors.rejectValue("email", "required");
        }
        else {
            Matcher matcher = pattern.matcher(req.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "bad");
            }
        }

        ValidationUtils.rejectIfEmpty(errors, "password", "required");
    }
}
