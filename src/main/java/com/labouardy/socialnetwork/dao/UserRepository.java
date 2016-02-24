package com.labouardy.socialnetwork.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labouardy.socialnetwork.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
