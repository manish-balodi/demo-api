package glaucus.api.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonDeleteResponse {

    @JsonProperty(value = "isDeleteSuccessful")
    private boolean isDeleted;
}
