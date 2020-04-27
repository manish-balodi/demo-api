package glaucus.api.service.util;

import java.util.regex.Pattern;

import glaucus.api.common.constants.AppConstants;

public class ValidationUtil {

    public static boolean isValidEmail(String email) {
        return Pattern.compile(AppConstants.EMAIL_REGEX_PATTERN).matcher(email).matches();
    }
}
