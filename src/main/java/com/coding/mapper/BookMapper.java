package com.coding.mapper;

import com.coding.domain.Book;
import com.coding.domain.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author guanweiming
 */
@Repository
public interface BookMapper extends JpaRepository<Book, Long> {
}
