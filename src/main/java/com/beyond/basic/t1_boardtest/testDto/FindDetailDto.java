package com.beyond.basic.t1_boardtest.testDto;

import com.beyond.basic.t1_boardtest.testDomain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FindDetailDto {
    private String name;
    private String email;

    public FindDetailDto fromEntity(User user) {
        return new FindDetailDto(user.getName(), user.getEmail());
    }
}
