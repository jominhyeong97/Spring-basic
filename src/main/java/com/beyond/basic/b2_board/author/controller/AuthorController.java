package com.beyond.basic.b2_board.author.controller;

import com.beyond.basic.b2_board.author.dto.AuthorCreateDto;
import com.beyond.basic.b2_board.author.dto.AuthorListDto;
import com.beyond.basic.b2_board.author.dto.CommonDto;
import com.beyond.basic.b2_board.author.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
//     회원 가입
    @PostMapping("/create")
//    dto에 있는 validation어노테이션과 controller @vaild 한 쌍
    public ResponseEntity<String> save(@Valid @ModelAttribute AuthorCreateDto authorCreateDto) {
//        try {
//
//
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//
////      생성자 매개면수 body부분의 객체와 header부에 상태코드
//            ResponseEntity<String> response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//            return response;
//        }
        this.authorService.save(authorCreateDto);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
    
//    회원목록 조회 : /author/list
    @GetMapping("/list")
    public List<AuthorListDto> findAll() {
        return this.authorService.findAll();
    }
    
//    회원상세조회 : id로 조회 /author/detail/1
//    서버에서 별도의 try catch를 하지 않으면 에러 발생시 500에러 + 스프링의 포맷으로 에러를 리턴.
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) { /// ???
//        try {
////            ResponseEntity<AuthorDetailDto> response = new ResponseEntity<>(this.authorService.findById(id),HttpStatus.OK);
////            return response;
//            return new ResponseEntity<>(new CommonDto(this.authorService.findById(id),"Data has found",HttpStatus.OK.value()),HttpStatus.OK);
//        }
//        catch (NoSuchElementException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(new CommonErrorDto(HttpStatus.NOT_FOUND.value(),e.getMessage()),HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(new CommonDto(this.authorService.findById(id),"Data has found",HttpStatus.OK.value()),HttpStatus.OK);


    }

    
////    비밀번호 수정 : email, password > json
////    get:조회,post:등록,patch:부분수정, put:대체, delete
//    @PatchMapping("/updatepw")
//    public String updatePassword(@RequestBody AuthorUpdatePwDto authorUpdatePwDto) {
//        this.authorService.updatePassword(authorUpdatePwDto.getEmail(), authorUpdatePwDto.getPassword());
//        return "ok";
//    }

//    회원 탈퇴(삭제) : /author/1
    @DeleteMapping("/delete/{id}")
    public String delete (@PathVariable Long id) {
        this.authorService.delete(id);
        return "ok";
    }
}
