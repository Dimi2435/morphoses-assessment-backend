package com.morphoses.assessment.repository;

import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.util.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUserType(UserType userType);
}