package com.example.simple_crud_with_hateoas.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
            select u from User as u where u.id=?1 and u.deletedAt is null
            """)
    Optional<User> findUserById(Integer id);

    @Query("""
            select u from User as u
            """)
    List<User> findAllUsers();
}
