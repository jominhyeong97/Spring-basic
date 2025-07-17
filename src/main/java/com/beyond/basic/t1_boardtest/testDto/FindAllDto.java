package com.beyond.basic.t1_boardtest.testDto;

import com.beyond.basic.t1_boardtest.testDomain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FindAllDto {
    private Long id;
    private String name;
    private String email;

    public FindAllDto fromEntity(User user) {
        return new FindAllDto(user.getId(),user.getName(),user.getEmail());
    }
}
