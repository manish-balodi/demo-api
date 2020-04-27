package glaucus.api.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import glaucus.api.common.utils.DateTimeUtil;
import glaucus.api.domain.common.ApiResult;
import glaucus.api.domain.model.dto.PersonData;
import glaucus.api.service.PersonService;
import glaucus.api.service.exceptions.InvalidPersonUpdateRequestedException;
import glaucus.api.service.exceptions.PersonCustomException;
import glaucus.api.service.exceptions.PersonDuplicateException;
import glaucus.api.validator.PersonDataCreateRequestValidator;
import glaucus.api.validator.PersonDataUpdateRequestValidator;
import glaucus.api.web.response.PersonCreateResponse;
import glaucus.api.web.response.PersonDeleteResponse;
import glaucus.api.web.response.PersonResponseMapper;
import glaucus.api.web.response.PersonUpdateResponse;
import glaucus.api.web.utils.ResponseEntityUtils;

/**
 * Controller class for handling CRUD operations for person entity node.
 * @author manish.balodi
 */
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonResponseMapper personResponseMapper;

    @Autowired
    private PersonDataUpdateRequestValidator personDataUpdateRequestValidator;

    @Autowired
    private PersonDataCreateRequestValidator personDataCreateRequestValidator;

    /**
     * REST Endpoint for creating person node in neo4j
     * @param personData
     * @param errors
     * @return
     * @throws PersonCustomException
     * @throws PersonDuplicateException
     */
    @PostMapping
    public ResponseEntity<ApiResult<PersonCreateResponse>> createPerson(@RequestBody @Valid PersonData personData,
                                                                        Errors errors) throws PersonCustomException,
            PersonDuplicateException {
        personData.setCreatedAt(DateTimeUtil.getNowInIST());
        personData.setUpdatedAt(DateTimeUtil.getNowInIST());
        personDataCreateRequestValidator.validate(personData, errors);
        if (errors.hasErrors()) {
            throw new PersonDuplicateException(errors);
        }
        return ResponseEntityUtils.responseEntity(personResponseMapper.getCreateResponse(personService.createPerson(personData)),
                HttpStatus.CREATED);

    }


    /**
     * REST Endpoint for updating person node in neo4j
     * @param personData
     * @param id
     * @param errors
     * @return
     * @throws PersonCustomException
     * @throws InvalidPersonUpdateRequestedException
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResult<PersonUpdateResponse>> updatePerson(@RequestBody PersonData personData,
                                                                        @PathVariable Long id, Errors errors) throws PersonCustomException, InvalidPersonUpdateRequestedException {
        personData.setId(id);
        personDataUpdateRequestValidator.validate(personData, errors);
        if (errors.hasErrors()) {
            throw new InvalidPersonUpdateRequestedException(errors);
        }
        personService.updatePerson(personData);
        return ResponseEntityUtils.responseEntity(personResponseMapper.getUpdateResponse());
    }

    /**
     * REST Endpoint for deleting person node in neo4j
     * @param id
     * @return
     * @throws PersonCustomException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<PersonDeleteResponse>> deletePerson(@PathVariable Long id) throws PersonCustomException {
        personService.deletePersonById(id);
        return ResponseEntityUtils.responseEntity(personResponseMapper.getDeleteResponse());
    }

    /**
     * REST Endpoint for feting person node in neo4j
     * @param id
     * @return
     * @throws PersonCustomException
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<PersonData>> getPerson(@PathVariable Long id) throws PersonCustomException {
        return ResponseEntityUtils.responseEntity(personService.getPersonById(id), HttpStatus.FOUND);
    }
}
