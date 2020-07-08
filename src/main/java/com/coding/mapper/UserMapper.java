package com.coding.mapper;

import com.coding.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author guanweiming
 */
@Repository
public interface UserMapper extends JpaRepository<User, Long> {
}
