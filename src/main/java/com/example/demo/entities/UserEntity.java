package com.example.demo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column (nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean enabled;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;
}
