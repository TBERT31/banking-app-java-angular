package com.tbert31.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tbert31.banking.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    //select * from User where firstname = 'ali';
    List<User> findAllByFirstname(String firstname);

    //select * from User where firstname like '%ali%';
    List<User> findAllByFirstnameContaining(String firstname);

    //select * from User where firstname ilike '%ali%' (en ignorant la casse);
    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    @Query(value = "select * from _user u inner  join account a on u.id = a.id_user where a.iban = :iban", nativeQuery = true)
    List<User> searchByIbanNative(String iban);
}
