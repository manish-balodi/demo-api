package glaucus.api.web.response;

import org.springframework.stereotype.Component;

@Component
public class PersonResponseMapper {

    public FriendshipCreateResponse getFriendshipCreateResponse(Long friendshipId) {
        FriendshipCreateResponse response = new FriendshipCreateResponse();
        response.setId(friendshipId);

        return response;
    }

    public PersonCreateResponse getCreateResponse(Long personId) {
        PersonCreateResponse response = new PersonCreateResponse();
        response.setId(personId);

        return response;
    }

    public PersonUpdateResponse getUpdateResponse() {
        PersonUpdateResponse response = new PersonUpdateResponse();
        response.setUpdated(true);

        return response;
    }

    public PersonDeleteResponse getDeleteResponse() {
        PersonDeleteResponse response = new PersonDeleteResponse();
        response.setDeleted(true);
        return response;
    }

}
