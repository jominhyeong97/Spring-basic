package com.beyond.basic.t1_boardtest.testDomain;

import com.beyond.basic.t1_boardtest.testRepo.Repository;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter

public class Author {
    private Long id;
    private String name;
    private String email;
    private String password;

    public  Author(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.id = Repository.id;
    }

}
