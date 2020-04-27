package glaucus.api.domain.messages;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "message")
public class Messages {

    private String personCreateFailed;

    private String personUpdateFailed;

    private String personDeleteFailed;

    private String personNotFound;

}