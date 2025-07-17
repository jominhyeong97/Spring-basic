package com.beyond.basic.t1_boardtest.testService;

import com.beyond.basic.t1_boardtest.testDomain.User;
import com.beyond.basic.t1_boardtest.testDto.FindAllDto;
import com.beyond.basic.t1_boardtest.testDto.FindDetailDto;
import com.beyond.basic.t1_boardtest.testDto.SaveDto;
import com.beyond.basic.t1_boardtest.testRepo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional

public class Service {


    final public Repository repository;

    @Autowired
    public Service(Repository repository) {
        this.repository = repository;
    }

    public void save(SaveDto saveDto) {
//        이메일검증, 비밀번호 검증 필요함.
        repository.save(saveDto.saveToEntity());
    }

    public List<FindAllDto> findAll() {

//       List<FindAllDto> findAllDtoList = new ArrayList<>();
//        for(User a : repository.findAll()) {
//            findAllDtoList.add(new FindAllDto().fromEntity(a));
//        }
//        return findAllDtoList;
        return repository.findAll().stream().map(a->new FindAllDto().fromEntity(a)).collect(Collectors.toList());

    }

    public FindDetailDto findById(Long id) {
        User user = repository.findById(id).orElseThrow(()-> new NoSuchElementException("값이없습니다."));
        return new FindDetailDto().fromEntity(user);
    }

//    public void updatePw(Long id, String newPw) {
//        Author author = new Author();
//        for(author a : )
//    }

    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(()-> new NoSuchElementException("값이없습니다."));
        repository.delete(user);
    }




}
