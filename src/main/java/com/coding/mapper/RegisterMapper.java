package com.coding.mapper;

import com.coding.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author guanweiming
 */
@Repository
public interface RegisterMapper extends JpaRepository<Register,Long> {
}
