package com.beyond.basic.t1_boardtest.testRepo;

import com.beyond.basic.t1_boardtest.testDomain.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Getter
public class Repository {

    private List<Author> authorList;

    public static Long id;

    public Optional<?> save() {
        Author author = new Author()
        authorList.add()
        return null;
    }

    public Optional<?> findAll() {
        return null;
    }
    public Optional<?> findById() {
        return null;
    }
//    public Optional<?> updatePw() {
//        return null;
//    }
    public Optional<?> deleteUser() {
        return null;
    }


}
