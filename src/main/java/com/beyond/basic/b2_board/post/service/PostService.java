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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PostListDto> findAll(Pageable pageable) {
//         List<PostListDto> postList = new ArrayList<>();
//         for(Post a : postRepository.findAll()) {
//            postList.add(PostListDto.fromEntity(a));
//         }
//         return postList;

//        postList를 조회할 때 참조관계에 있는 author까지 조회하게 되므로, N(author쿼리)+1(post쿼리)문제 발생
//        jpa는 기본방샹성이 fetch lazy이므로 참조하는 시점에 쿼리를 내보내게 되어 join을 하지 않고 n+1문제 발생

//        List<Post> postList = postRepository.findAllJoin(); //일반 inner join
//        List<Post> postList = postRepository.findAllFetchJoin(); //fetch inner join
            Page<Post> postList = postRepository.findAllByDelYn(pageable,"N");
         return postList.map(PostListDto::fromEntity);


    }

    public PostDetailDto findById(Long id) {
//        엔티티간의 관계성 설정을 하지 않았을 때
//        Author author = authorRepository.findById(post.getId()).orElseThrow(()-> new EntityNotFoundException("없는회원입니다."));
//        return PostDetailDto.fromEntity(post, author);


//        페이지처리 findALL호출
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 ID입니다."));

        return PostDetailDto.fromEntity(post);

//        엔티티간의 관계성 설정을 통해 Author객체를 쉽게 조회하는 경우


    }

}
