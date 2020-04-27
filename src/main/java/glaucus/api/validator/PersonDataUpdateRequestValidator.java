package glaucus.api.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

import glaucus.api.domain.messages.ValidationMessages;
import glaucus.api.domain.model.dto.PersonData;
import glaucus.api.service.constants.ErrorCodes;
import glaucus.api.service.util.ValidationUtil;

/**
 * Validator for checking person node update request.
 * @author manish.balodi
 */
@Component
public class PersonDataUpdateRequestValidator implements Validator {

    @Autowired
    private ValidationMessages messages;

    /**
     * Can this {@link Validator} {@link #validate(Object, Errors) validate}
     * instances of the supplied {@code clazz}?
     * <p>This method is <i>typically</i> implemented like so:
     * <pre class="code">return Foo.class.isAssignableFrom(clazz);</pre>
     * (Where {@code Foo} is the class (or superclass) of the actual
     * object instance that is to be {@link #validate(Object, Errors) validated}.)
     *
     * @param clazz the {@link Class} that this {@link Validator} is
     *              being asked if it can {@link #validate(Object, Errors) validate}
     * @return {@code true} if this {@link Validator} can indeed
     * {@link #validate(Object, Errors) validate} instances of the
     * supplied {@code clazz}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return PersonData.class.isAssignableFrom(clazz);
    }

    /**
     * Validate the supplied {@code target} object, which must be
     * of a {@link Class} for which the {@link #supports(Class)} method
     * typically has (or would) return {@code true}.
     * <p>The supplied {@link Errors errors} instance can be used to report
     * any resulting validation errors.
     *
     * @param target the object that is to be validated
     * @param errors contextual state about the validation process
     * @see org.springframework.validation.ValidationUtils
     */
    @Override
    public void validate(Object target, Errors errors) {

        if (Objects.nonNull(target) && target instanceof PersonData) {
            PersonData updatedPersonData = (PersonData) target;
            String fullName = updatedPersonData.getFullName();
            String phoneNumber = updatedPersonData.getPhoneNumber();
            String email = updatedPersonData.getEmail();

            if (isFullNameBlank(fullName)) {
                errors.reject(Integer.toString(ErrorCodes.ERROR_CODE_PERSON_FULL_NAME_NOT_PROVIDE),
                        messages.getMessageFullNameRequired());
            }
            if (isPhoneNumberBlank(phoneNumber)) {
                errors.reject(Integer.toString(ErrorCodes.ERROR_CODE_PERSON_PHONE_NUMBER_NOT_PROVIDED),
                        messages.getMessagePhoneNumberRequired());
            }
            if (isEmailBlank(email)) {
                errors.reject(Integer.toString(ErrorCodes.ERROR_CODE_PERSON_EMAIL_NOT_PROVIDED),
                        messages.getMessageEmailRequired());
            }
            if (!isEmailValid(email)) {
                errors.reject(Integer.toString(ErrorCodes.ERROR_CODE_PERSON_EMAIL_INVALID),
                        messages.getMessageEmailInvalid());
            }

        }
    }

    private boolean isFullNameBlank(String fullName) {
        return Objects.isNull(fullName) ? false : StringUtils.isBlank(fullName);
    }

    private boolean isPhoneNumberBlank(String phoneNumber) {
        return Objects.isNull(phoneNumber) ? false : StringUtils.isBlank(phoneNumber);
    }

    private boolean isEmailBlank(String email) {
        return Objects.isNull(email) ? false : StringUtils.isBlank(email);
    }

    private boolean isEmailValid(String email) {
        return Objects.isNull(email) ? true : ValidationUtil.isValidEmail(email);
    }
}
