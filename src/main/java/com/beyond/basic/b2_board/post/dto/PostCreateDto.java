package com.beyond.basic.b2_board.post.dto;

import com.beyond.basic.b2_board.author.domain.Author;
import com.beyond.basic.b2_board.post.domain.Post;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class PostCreateDto {
    @NotEmpty //숫자는 NotNull
    private String title;
    private String contents;
    private Long authorId;

    public Post toEntity(Author author) {
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
//                .authorId(this.authorId)
                .author(author)
                .build();
    }
}
