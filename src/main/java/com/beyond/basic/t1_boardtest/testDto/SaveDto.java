package com.beyond.basic.t1_boardtest.testDto;

import com.beyond.basic.t1_boardtest.testDomain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SaveDto {
    private String name;
    private String email;
    private String password;

    public SaveDto saveFromEntity(User user) {
        return new SaveDto(user.getName(), user.getEmail(), user.getPassword());
    }

    public User saveToEntity() {
        return new User(this.name,this.email,this.password);
    }

}
