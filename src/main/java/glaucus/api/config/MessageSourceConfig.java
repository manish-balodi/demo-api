package glaucus.api.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import static glaucus.api.config.constants.MessageSourceConstants.*;

@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(MSG_SRC_VALIDATION_CLASSPATH_STR, MSG_SRC_BUSINESS_CLASSPATH_STR);
        messageSource.setDefaultEncoding(MSG_SRC_ENCODING_UTF_8);
        return messageSource;
    }
}
