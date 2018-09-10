package com.teachme.teach.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    long id;

    @Column(name = "email")
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "password")
    @Transient
    String password;

    @Column(name = "active") //set default value to 1
            int active = 1;

    @Column(name="point")
    int point=0;

    @ManyToMany(cascade = CascadeType.ALL)
    // @JsonIgnore
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    public Users(Users users) {
        this.active = users.getActive();
        this.email = users.getEmail();
        this.roles = users.getRoles();
        this.firstName = users.getFirstName();
        this.lastName =users.getLastName();
        this.id = users.getId();
        this.password = users.getPassword();
    }
}





