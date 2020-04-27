package glaucus.api.domain.messages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "message.validation")
public class ValidationMessages {

    @Value("${message.validation.person-full-name-required:person full name is mandatory}")
    private String messageFullNameRequired;

    @Value("${message.validation.person-phone-number-required:person phone number is mandatory}")
    private String messagePhoneNumberRequired;

    @Value("${message.validation.person-email-required:person email is mandatory}")
    private String messageEmailRequired;

    @Value("${message.validation.person-email-invalid:person email is invalid}")
    private String messageEmailInvalid;

    @Value("${message.validation.person-already-exists:person exists for given details}")
    private String messagePersonAlreadyExist;
}
