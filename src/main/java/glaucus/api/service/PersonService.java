package glaucus.api.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import glaucus.api.common.utils.DateTimeUtil;
import glaucus.api.domain.messages.Messages;
import glaucus.api.domain.model.dto.PersonData;
import glaucus.api.domain.model.entity.Friendship;
import glaucus.api.domain.model.entity.Person;
import glaucus.api.domain.repository.FriendshipRepository;
import glaucus.api.domain.repository.PersonRepository;
import glaucus.api.service.constants.ErrorCodes;
import glaucus.api.service.exceptions.FriendshipException;
import glaucus.api.service.exceptions.PersonCustomException;

import static glaucus.api.service.util.ModelMapperUtil.getModelMapper;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private Messages messages;

    @Autowired
    private Driver neo4jDriver;


    public Long createPerson(PersonData personData) throws PersonCustomException {
        final Person newPerson = getModelMapper().map(personData, Person.class);
        try {
            personRepository.save(newPerson);
        } catch (Exception ex) {
            throw new PersonCustomException(ErrorCodes.ERROR_CODE_PERSON_CREATE_FAILED,
                    messages.getPersonCreateFailed());
        }
        return newPerson.getId();
    }

    public void updatePerson(PersonData personData) throws PersonCustomException {
        Session session = null;
        try {
            String cypher = createCypherUpdateQuery(personData);
            Map<String, Object> updateParams = updateParams(personData);
            session = neo4jDriver.session();
            session.writeTransaction(tx -> {
                return tx.run(cypher, updateParams);
            });
        } catch (Exception ex) {
            throw new PersonCustomException(ErrorCodes.ERROR_CODE_PERSON_UPDATE_FAILED,
                    messages.getPersonUpdateFailed());
        } finally {
            if (Objects.nonNull(session)) {
                session.close();
            }
        }
        return;
    }

    public Long createFriendship(Long p1, Long p2) throws FriendshipException {
        Person person1 = personRepository.findById(p1).orElse(null);
        Person person2 = personRepository.findById(p2).orElse(null);

        if (Objects.nonNull(person1) && Objects.nonNull(person2)) {
            Friendship friendship = Friendship.builder().p1(person1).p2(person2).build();
            friendshipRepository.save(friendship);
            return friendship.getId();
        } else {
            throw new FriendshipException("Unable to create friendship");
        }
    }

    private Map<String, Object> updateParams(PersonData personData) {
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("id", personData.getId());
        if (Objects.nonNull(personData.getFullName())) {
            updateParams.put("fullName", personData.getFullName());
        }
        if (Objects.nonNull(personData.getPhoneNumber())) {
            updateParams.put("phoneNumber", personData.getPhoneNumber());
        }
        if (Objects.nonNull(personData.getEmail())) {
            updateParams.put("email", personData.getEmail());
        }
        updateParams.put("updatedAt", DateTimeUtil.getNowInIST());
        return updateParams;
    }

    private String createCypherUpdateQuery(PersonData personData) {
        List<String> setClauseParts = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("MATCH (p:Person) WHERE id(p) = ").append("$id").append(" ");
        if (Objects.nonNull(personData.getFullName())) {
            setClauseParts.add("p.name = $fullName");
        }
        if (Objects.nonNull(personData.getPhoneNumber())) {
            setClauseParts.add("p.phoneNumber = $phoneNumber");
        }
        if (Objects.nonNull(personData.getEmail())) {
            setClauseParts.add("p.email = $email");
        }
        if (CollectionUtils.isNotEmpty(setClauseParts)) {
            setClauseParts.add("p.updatedAt = $updatedAt");
        }
        if (CollectionUtils.isNotEmpty(setClauseParts)) {
            queryBuilder.append("SET ");
            if (setClauseParts.size() == 1) {
                queryBuilder.append(setClauseParts.get(0));
            } else {
                int index = 0;
                for (String setPart : setClauseParts) {
                    if (index == setClauseParts.size() - 1) {
                        queryBuilder.append(setPart);
                        break;
                    }
                    queryBuilder.append(setPart).append(",");
                    index++;
                }
            }
        } else {
            return StringUtils.EMPTY;
        }
        return queryBuilder.toString();
    }

    public int deletePersonById(Long id) throws PersonCustomException {
        try {
            personRepository.deleteById(id);
        } catch (Exception ex) {
            throw new PersonCustomException(ErrorCodes.ERROR_CODE_PERSON_DELETE_FAILED,
                    messages.getPersonDeleteFailed());
        }
        return 1;
    }

    public PersonData getPersonById(Long id) throws PersonCustomException {
        return personRepository.findById(id)
                .map(person -> getModelMapper().map(person, PersonData.class))
                .orElseThrow(() -> new PersonCustomException(ErrorCodes.ERROR_CODE_PERSON_NOT_FOUND,
                        messages.getPersonNotFound()));

    }

}
