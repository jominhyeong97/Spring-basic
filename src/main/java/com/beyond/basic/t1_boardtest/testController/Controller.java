package com.beyond.basic.t1_boardtest.testController;

import com.beyond.basic.t1_boardtest.testDomain.User;
import com.beyond.basic.t1_boardtest.testDto.FindAllDto;
import com.beyond.basic.t1_boardtest.testDto.FindDetailDto;
import com.beyond.basic.t1_boardtest.testDto.SaveDto;
import com.beyond.basic.t1_boardtest.testService.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/test")


public class Controller {

    @Autowired
    final public Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@ModelAttribute SaveDto saveDto) {
        service.save(saveDto);
        System.out.println(saveDto);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }

    @GetMapping("/findDetail/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }
//    @DeleteMapping("/delete/{id}")
//    public String updatePw(@PathVariable Long id, String newPw) {
//        service.updatePw(id,newPw);
//        return "ok";
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
}
