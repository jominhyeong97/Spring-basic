package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Author;
import com.beyond.basic.b2_board.dto.AuthorCreateDto;
import com.beyond.basic.b2_board.dto.AuthorDetailDto;
import com.beyond.basic.b2_board.dto.AuthorListDto;
import com.beyond.basic.b2_board.dto.AuthorUpdatePwDto;
import com.beyond.basic.b2_board.repository.AuthorMemoryRepository;
import com.beyond.basic.b2_board.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
//     회원 가입
    @PostMapping("/create")
    public String save(@ModelAttribute AuthorCreateDto authorCreateDto) {
        try {
            this.authorService.save(authorCreateDto);
            return "ok";

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    
//    회원목록 조회 : /author/list
    @GetMapping("/list")
    public List<AuthorListDto> findAll() {
        return this.authorService.findAll();
    }
    
//    회원상세조회 : id로 조회 /author/detail/1
//    서버에서 별도의 try catch를 하지 않으면 에러 발생시 500에러 + 스프링의 포맷으로 에러를 리턴.
    @GetMapping("/detail/{id}")
    public AuthorDetailDto findById(@PathVariable Long id) {
        try {
            return this.authorService.findById(id);
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            e.getMessage();
            return null; // ?????
        }


    }

    
//    비밀번호 수정 : email, password > json
//    get:조회,post:등록,patch:부분수정, put:대체, delete
    @PatchMapping("/updatepw")
    public String updatePassword(@RequestBody AuthorUpdatePwDto authorUpdatePwDto) {
        this.authorService.updatePassword(authorUpdatePwDto.getEmail(), authorUpdatePwDto.getPassword());
        return "ok";
    }

//    회원 탈퇴(삭제) : /author/1
    @DeleteMapping("/delete/{id}")
    public String delete (@PathVariable Long id) {
        this.authorService.delete(id);
        return "ok";
    }
}
