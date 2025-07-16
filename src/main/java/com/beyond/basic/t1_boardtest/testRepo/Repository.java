package com.beyond.basic.t1_boardtest.testRepo;

import com.beyond.basic.t1_boardtest.testDomain.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Getter
public class Repository {

    private List<Author> authorList;
    public static Long id;

    public void save(Author author) {
        authorList.add(author);
        id++;
    }

    public List<Author> findAll() {
        return authorList;
    }
    public Optional<Author> findById(Long id) {
        for(Author a : authorList) {
            if(a.getId().equals(id)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

//    public ResponseEntity<?> updatePw(String email, String newPassword) {
//        for(Author a : authorList) {
//            if(a.getEmail().equals(email)) {
//                return new ResponseEntity<>(a.setPassword(newPassword), HttpStatus.OK);
//            }
//        }
//    }

    public Optional<?> deleteUser() {
        return null;
    }


}
