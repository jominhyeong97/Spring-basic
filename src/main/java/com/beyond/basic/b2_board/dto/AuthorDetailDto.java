package com.beyond.basic.b2_board.dto;

import com.beyond.basic.b2_board.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AuthorDetailDto {
    private Long id;
    private String name;
    private String password;

//    1개의 entity로만 dto가 조립되는것이 아니기에 dto계층에서 fromentity를 설계

    public static AuthorDetailDto fromEntity(Author author) {
        return new AuthorDetailDto(author.getId(),author.getName(),author.getEmail());
    }
}
