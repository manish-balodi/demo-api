package glaucus.api.domain.model.entity;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.time.ZonedDateTime;

@NodeEntity(label = "Person")
public class Person {

    @org.neo4j.ogm.annotation.Id
    @GeneratedValue
    private Long Id;

    @Property(name = "name")
    private String fullName;

    private String email;

    private String phoneNumber;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public Long getId() {
        return Id;
    }

    public Person setId(Long id) {
        Id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Person setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Person setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Person setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
