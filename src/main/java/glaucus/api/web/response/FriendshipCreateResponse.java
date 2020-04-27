package glaucus.api.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represent the response model to be returned to client when a person is created  successfully.
 * @author manish.balodi
 */
@Data
public class FriendshipCreateResponse {

    @JsonProperty(value = "friendshipId")
    private Long id;
}
