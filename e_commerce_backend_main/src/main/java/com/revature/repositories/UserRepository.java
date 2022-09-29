package com.revature.repositories;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {


    //Update user Profile method.
    @Modifying
    @Query(value = "UPDATE users SET address = ?1, email = ?2, first_name = ?3, last_name = ?4, password = ?5 WHERE id =?6", nativeQuery = true)
    int profileUpdate(String address, String email, String firstName, String lastName, String password, int id);

    //Used to update a user to admin
    @Modifying
    @Query(value = "UPDATE users SET admin_status = ?1 WHERE id = ?2",nativeQuery = true)
    int updateToAdmin(boolean admin, int id);

    //Might need a method to get all users to find what user we want to give admin status.


    Optional<User> findByEmailAndPassword(String email, String password);

    //Used to reset password
    @Modifying
    @Query(value = "UPDATE users SET password = ?1 WHERE email = ?2",nativeQuery = true)
    int resetPassword(String password, String email);
}
