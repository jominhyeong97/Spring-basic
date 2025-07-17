package com.beyond.basic.b2_board.post.service;

import com.beyond.basic.b2_board.author.domain.Author;
import com.beyond.basic.b2_board.author.repository.AuthorRepository;
import com.beyond.basic.b2_board.post.domain.Post;
import com.beyond.basic.b2_board.post.dto.PostCreateDto;
import com.beyond.basic.b2_board.post.dto.PostDetailDto;
import com.beyond.basic.b2_board.post.dto.PostListDto;
import com.beyond.basic.b2_board.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service

public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    public void save(PostCreateDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow(()->new EntityNotFoundException("없는 ID입니다."));
        postRepository.save(dto.toEntity(author));
    }

    public List<PostListDto> findAll() {
//        List<PostListDto> postList = new ArrayList<>();
//         for(Post a : postRepository.findAll()) {
//            postList.add(PostListDto.fromEntity(a));
//         }
//         return postList;
         return postRepository.findAll().stream().map(PostListDto::fromEntity).collect(Collectors.toList());


    }

    public PostDetailDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 ID입니다."));
//        엔티티간의 관계성 설정을 하지 않았을 때
//        Author author = authorRepository.findById(post.getId()).orElseThrow(()-> new EntityNotFoundException("없는회원입니다."));
//        return PostDetailDto.fromEntity(post, author);
        return PostDetailDto.fromEntity(post);

//        엔티티간의 관계성 설정을 통해 Author객체를 쉽게 조회하는 경우


    }

}
