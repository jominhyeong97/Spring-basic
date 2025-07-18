package com.beyond.basic.b2_board.post.controller;

import com.beyond.basic.b2_board.author.dto.CommonDto;
import com.beyond.basic.b2_board.post.dto.PostCreateDto;
import com.beyond.basic.b2_board.post.dto.PostDetailDto;
import com.beyond.basic.b2_board.post.dto.PostListDto;
import com.beyond.basic.b2_board.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PostCreateDto dto) {
        postService.save(dto);
        return new ResponseEntity<>(new CommonDto(dto,"post is found",HttpStatus.CREATED.value()),HttpStatus.CREATED);

    }
//    페이징처리를 위한 데이터 요청형식 : 8080/post/list?page=0&size=20&sort=title,desc
    @GetMapping("/list")
    public ResponseEntity<?> postList(@PageableDefault(size=10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        Page<PostListDto> dto = postService.findAll(pageable);
        return new ResponseEntity<>(new CommonDto(dto,"here you are",HttpStatus.OK.value()),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        PostDetailDto dto = postService.findById(id);
        return new ResponseEntity<>(new CommonDto(dto,"post is found",HttpStatus.OK.value()),HttpStatus.OK);
    }



}
