package glaucus.api.domain.query;

public class QueryConstants {

    public static final String PERSON_BY_NAME_PHONE_AND_EMAIL_QUERY = "MATCH (p:Person) WHERE p.name = $fullName AND " +
            "p.phoneNumber" +
            " = $phoneNumber AND p.email = $email RETURN p";
}
