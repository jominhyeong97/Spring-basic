package com.beyond.basic.t1_boardtest.testController;

import com.beyond.basic.t1_boardtest.testDomain.Author;
import com.beyond.basic.t1_boardtest.testDto.FindAllDto;
import com.beyond.basic.t1_boardtest.testDto.FindDetailDto;
import com.beyond.basic.t1_boardtest.testRepo.Repository;
import com.beyond.basic.t1_boardtest.testService.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")


public class Controller {

    @Autowired
    final public Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@ModelAttribute Author author) {
        service.save(author);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public List<FindAllDto> findAll(@ModelAttribute FindAllDto findAllDto) {
        service.findAll(findAllDto);
        return null;
    }

    @GetMapping("/findDetail/{id}")
    public FindDetailDto findById(@PathVariable Long id) {
        service.findById(id);
        return null;
    }
    @DeleteMapping("/delete/{id}")
    public String updatePw(@PathVariable Long id, String newPw) {
        service.updatePw(id,newPw);
        return "ok";
    }

    public ResponseEntity<?> deleteUser() {
        return null;
    }

}
