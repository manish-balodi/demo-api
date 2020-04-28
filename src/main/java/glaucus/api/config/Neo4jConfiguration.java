package glaucus.api.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfiguration {

    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic( "neo4j", "test" ));
    }
}
