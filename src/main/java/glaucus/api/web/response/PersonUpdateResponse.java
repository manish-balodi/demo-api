package glaucus.api.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonUpdateResponse {

    @JsonProperty(value = "isUpdateSuccessful")
    private boolean isUpdated;

}
