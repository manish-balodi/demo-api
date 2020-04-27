package glaucus.api.domain.model.dto;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

import glaucus.api.validator.ValidEmail;
import lombok.Data;
import lombok.ToString;

import static glaucus.api.common.constants.AppConstants.EMAIL_REGEX_PATTERN;

@Data
@ToString
public class PersonData {

    private Long id;

    @NotBlank(message = "Person full name is mandatory")
    private String fullName;

    @NotBlank(message = "Person email is mandatory")
    @ValidEmail(message = "Person email is invalid", regexp = EMAIL_REGEX_PATTERN)
    private String email;

    @NotBlank(message = "Person phone number is mandatory")
    private String phoneNumber;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
