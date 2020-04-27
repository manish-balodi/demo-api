package glaucus.api.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import glaucus.api.domain.common.ApiResult;
import glaucus.api.service.PersonService;
import glaucus.api.service.exceptions.FriendshipException;
import glaucus.api.web.response.FriendshipCreateResponse;
import glaucus.api.web.response.PersonResponseMapper;
import glaucus.api.web.utils.ResponseEntityUtils;

@RestController
@RequestMapping("/api/v1/friendship")
public class FriendshipController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonResponseMapper personResponseMapper;

    @PostMapping("/{p1}/{p2}")
    public ResponseEntity<ApiResult<FriendshipCreateResponse>> createFriendship(@PathVariable Long p1,
                                                                                @PathVariable Long p2) throws FriendshipException {

        return ResponseEntityUtils.responseEntity(personResponseMapper.getFriendshipCreateResponse(personService.createFriendship(p1, p2)),
                HttpStatus.CREATED);

    }

}
