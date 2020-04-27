package glaucus.api.domain.model.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import lombok.Builder;
import lombok.Data;

@Data
@RelationshipEntity(type = "IS_FRIEND_OF")
@Builder
public class Friendship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person p1;

    @EndNode
    private Person p2;
}
