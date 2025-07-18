package com.beyond.basic.b2_board.author.service;

import com.beyond.basic.b2_board.author.domain.Author;
import com.beyond.basic.b2_board.author.dto.AuthorCreateDto;
import com.beyond.basic.b2_board.author.dto.AuthorDetailDto;
import com.beyond.basic.b2_board.author.dto.AuthorListDto;

import com.beyond.basic.b2_board.author.repository.AuthorRepository;
import com.beyond.basic.b2_board.post.domain.Post;
import com.beyond.basic.b2_board.post.dto.PostDetailDto;
import com.beyond.basic.b2_board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


//스프링에서 메서드 단위로 트랜잭션처리를 하고 만약 예외(unchecked) 발생시 자동 롤백처리 지원.
@Transactional
@Service //Component로 대체 가능(트랜잭션처리가 없는 경우)

public class AuthorService {

//    의존성 주입(DI) 방법1. Autowired 어노테이션 사용 -> 필드주입

//    @Autowired
//    private AuthorRepository authorRepository;

//    의존성 주입(DI) 방법2. 생성자 주입방식(가장 많이 쓰임)
//    장점 1. final을 통해 상수로 사용가능(안정성향상) 2. 다형성구현가능 3. 순환참조방지(컴파일 타임에 체크) ????

//    private final AuthorRepositoryInterface authorMemoryRepository;
////    객체로 만들어지는 시점에 Spring 에서 authorRepository 객체를 매개변수로 주입
//    @Autowired // 생성자가 하나밖에 없을 때에는 auto wired 생략가능
//    public AuthorService(AuthorMemoryRepository authorMemoryRepository) {
//        this.authorMemoryRepository = authorMemoryRepository;
//    }


//    의존성 주입(DI) 방법3. RequiredArgs 어노테이션 사용 -> 반드시 초기화되어야 하는 필드(final 등)을 대상으로 생성자를 자동생성
//    다형성 설계는 불가

private final AuthorRepository authorRepository;
private final PostRepository postRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, PostRepository postRepository) {
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
    }

    public void save(AuthorCreateDto authorCreateDto) {
//        이메일 중복검증, 비밀번호 길이 검증

//        Author author = new Author(authorCreateDto.getName(), authorCreateDto.getEmail(), authorCreateDto.getPassword());
        if(!authorRepository.findByEmail(authorCreateDto.getEmail()).isPresent()) {
////            toEntity패턴을 통해 author 객체 조립을 공통화
            Author author = authorCreateDto.authorToEntity();
            this.authorRepository.save(author);
        } else {
            throw new IllegalArgumentException("존재하는 이메일입니다.");
        }

    }

//    트랜잭션이 필요 없는 경우, 아래와 같이 명시적으로 제외
    @Transactional(readOnly = true)
    public List<AuthorListDto> findAll() {

//        List<AuthorListDto> authorListDto = new ArrayList<>();
//        for(Author a : this.authorMemoryRepository.findAll()) {
//            AuthorListDto authorDto = a.listFromEntity();
//            authorListDto.add(authorDto);
//        }

//        트랜잭션이 필요없는 경우 위 코드를 간결하게 줄임.

        return this.authorRepository.findAll().stream().
        map(a->a.listFromEntity()).collect(Collectors.toList());
    }

//    public void updatePassword(String email, String newPassword) {
//
//        AuthorUpdatePwDto authorUpdatePwDto = new AuthorUpdatePwDto();
//        for(Author a : this.authorRepository.getAuthorList()) {
//            if(a.getEmail().equals(email)) {
//                a.updatePw(newPassword);
//                return;
//            }
//        }
////        dirty체킹 : 객체를 수정한 후 별도의 update 쿼리 발생시키지 않아도 영ㅎ속성 컨텍스트에 의해 객체변경사항 자동 DB반영
//        Author author = authorRepository.findByEmail(authorUpdatePwDto.getEmail().o
//
//
//    }

    public AuthorDetailDto findById(Long id) throws NoSuchElementException { //Optional객체에서 꺼내는것도 service의 역할
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 id 없습니다."));
//        List<Post> postList = postRepository.findByAuthorId(id); //이것도 가능

//          연관관계설정없이 직접 조회해서 count값 찾는 경우
//        List<Post> postList = postRepository.findByAuthor(author);
//        AuthorDetailDto dto = AuthorDetailDto.fromEntity(author,postList.size());

//        oneToMany 연관관계 설정을 통해 count값 찾는 경우
        AuthorDetailDto dto = AuthorDetailDto.fromEntity(author);

        return dto;
    }

    public void delete(Long id) {

        Author author = authorRepository.findById(id).orElseThrow(()->new NoSuchElementException());
        this.authorRepository.delete(author);
    }



}
