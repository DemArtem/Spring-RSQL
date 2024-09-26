package org.itstep.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.itstep.entity.*;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {


    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findActiveUserById(Integer id);

    @Query(value = "SELECT u FROM User u WHERE u.name = :name")
    User findActiveUserByFirstName(@Param("name") String name);

    @Query(value = "SELECT * FROM User WHERE LAST_NAME = :name", nativeQuery = true)
    User findActiveUserByLastName(@Param("name") String firstName);

}