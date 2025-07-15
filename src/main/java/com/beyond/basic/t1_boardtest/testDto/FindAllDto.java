package com.beyond.basic.t1_boardtest.testDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FindAllDto {
    private String name;
    private String email;
    private Long id;
}
