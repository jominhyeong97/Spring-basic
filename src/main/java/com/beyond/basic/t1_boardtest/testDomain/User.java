package com.beyond.basic.t1_boardtest.testDomain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Entity

public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    private String password;

    public User(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;

    }

}
