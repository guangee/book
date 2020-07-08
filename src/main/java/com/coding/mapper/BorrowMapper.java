package com.coding.mapper;

import com.coding.domain.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author guanweiming
 */
@Repository
public interface BorrowMapper extends JpaRepository<Borrow,Long> {
}
