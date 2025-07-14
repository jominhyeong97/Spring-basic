package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Author;
import com.beyond.basic.b2_board.dto.AuthorCreateDto;
import com.beyond.basic.b2_board.dto.AuthorDetailDto;
import com.beyond.basic.b2_board.dto.AuthorListDto;
import com.beyond.basic.b2_board.dto.AuthorUpdatePwDto;
import com.beyond.basic.b2_board.repository.AuthorMemoryRepository;
import com.beyond.basic.b2_board.repository.AuthorRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.apache.coyote.http11.Constants.a;

@Service //Component로 대체 가능(트랜잭션처리가 없는 경우)
@RequiredArgsConstructor
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

private final AuthorMemoryRepository authorMemoryRepository;

    public void save(AuthorCreateDto authorCreateDto) {
//        이메일 중복검증, 비밀번호 길이 검증
        Author author = new Author(authorCreateDto.getName(), authorCreateDto.getEmail(), authorCreateDto.getPassword());
        if(!authorMemoryRepository.findByEmail(authorCreateDto.getEmail()).isPresent()) {
            this.authorMemoryRepository.save(author);
        } else {
            throw new NoSuchElementException("존재하는 이메일입니다.");
        }

    }

    public List<AuthorListDto> findAll() {

        List<AuthorListDto> authorListDto = new ArrayList<>();
        for(Author a : this.authorMemoryRepository.findAll()) {
            AuthorListDto authorDto = new AuthorListDto(a.getId(),a.getName(),a.getEmail());
            authorListDto.add(authorDto);
        }
        return authorListDto;
    }

    public void updatePassword(String email, String newPassword) {

        for(Author a : this.authorMemoryRepository.getAuthorList()) {
            if(a.getEmail().equals(email)) {
                a.updatePw(newPassword);
                return;
            }
        }

    }

    public AuthorDetailDto findById(Long id) throws NoSuchElementException { //Optional객체에서 꺼내는것도 service의 역할
        Author author = this.authorMemoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 id 없습니다."));
        AuthorDetailDto dto = new AuthorDetailDto(author.getId(), author.getName(), author.getEmail());
        return dto;
    }

    public void delete(Long id) {
        this.authorMemoryRepository.delete(id);
    }



}
