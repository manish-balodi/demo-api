package glaucus.api.validator;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Objects;

import glaucus.api.domain.messages.ValidationMessages;
import glaucus.api.domain.model.dto.PersonData;
import glaucus.api.domain.model.entity.Person;
import glaucus.api.domain.repository.PersonRepository;
import glaucus.api.service.constants.ErrorCodes;

@Component
public class PersonDataCreateRequestValidator implements Validator {

    @Autowired
    private ValidationMessages messages;

    @Autowired
    private PersonRepository personRepository;

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
            PersonData newPersonData = (PersonData) target;
            if (isPersonDataExist(newPersonData)) {
                errors.reject(Integer.toString(ErrorCodes.ERROR_CODE_PERSON_DATA_EXIST),
                        messages.getMessagePersonAlreadyExist());
            }


        }
    }

    private boolean isPersonDataExist(PersonData personData) {
        String fullName = personData.getFullName();
        String phoneNumber = personData.getPhoneNumber();
        String email = personData.getEmail();

        List<Person> persons = personRepository.findPersonByNamePhoneAndEmail(fullName, phoneNumber, email);
        return CollectionUtils.isNotEmpty(persons) ? true : false;
    }
}
