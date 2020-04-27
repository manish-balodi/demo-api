package glaucus.api.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import glaucus.api.domain.model.entity.Person;
import glaucus.api.domain.query.QueryConstants;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    @Query(QueryConstants.PERSON_BY_NAME_PHONE_AND_EMAIL_QUERY)
    List<Person> findPersonByNamePhoneAndEmail(@Param("fullName") String fullName,
                                               @Param("phoneNumber") String phoneNumber, @Param("email") String email);
}
