package glaucus.api.domain.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import glaucus.api.domain.model.entity.Friendship;

@Repository
public interface FriendshipRepository extends Neo4jRepository<Friendship, Long> {

}
