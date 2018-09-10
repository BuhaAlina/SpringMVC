package com.teachme.teach.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role", schema = "public")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    int id;

    @Column(name="role")
    String role;
}

