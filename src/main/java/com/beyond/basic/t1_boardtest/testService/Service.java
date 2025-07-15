package com.beyond.basic.t1_boardtest.testService;

import com.beyond.basic.t1_boardtest.testDomain.Author;
import com.beyond.basic.t1_boardtest.testDto.FindAllDto;
import com.beyond.basic.t1_boardtest.testDto.FindDetailDto;
import com.beyond.basic.t1_boardtest.testRepo.Repository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional

public class Service {

    @Autowired
    final public Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> save(@ModelAttribute Author author) {
        return null;
    }

    public List<FindAllDto> findAll(FindAllDto findAllDto) {
        return null;
    }
    public FindDetailDto findById(Long id) {
        return null;
    }
    public void updatePw(Long id, String newPw) {
        Author author = new Author();
        for(author a : )
    }
    public Optional<?> deleteUser() {
        return null;
    }




}
