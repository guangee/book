package com.coding.controller.admin;

import com.coding.common.Const;
import com.coding.common.DataRequest;
import com.coding.common.DataResponse;
import com.coding.domain.Book;
import com.coding.domain.Borrow;
import com.coding.domain.User;
import com.coding.mapper.BookMapper;
import com.coding.mapper.BorrowMapper;
import com.coding.mapper.UserMapper;
import com.guanweiming.common.utils.JsonUtil;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * @author guanweiming
 */
@Slf4j
@ApiIgnore
@AllArgsConstructor
@RequestMapping(Const.ADMIN + "borrow")
@RestController
public class AdminBorrowController {

    private final BorrowMapper borrowMapper;
    private final BookMapper bookMapper;
    private final UserMapper userMapper;

    @PostMapping("add")
    public Result<String> add(Long bookId, HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return Result.createByErrorMessage("登录之后才可以");
        }
        User currentUser = (User) user;
        Optional<Book> bookOptional = bookMapper.findById(bookId);
        if (!bookOptional.isPresent()) {
            return Result.createByErrorMessage("书籍查询失败");
        }
        Book book = bookOptional.get();
        if (Objects.equals(book.getStatus(), 0)) {
            return Result.createByErrorMessage("书已经被借走了，不可再次借阅");
        }
        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setStatus(0);
        borrow.setUser(currentUser);
        borrowMapper.save(borrow);

        book.setStatus(0);
        bookMapper.saveAndFlush(book);
        return Result.createBySuccess();
    }


    @PostMapping("return")
    public Result<String> returnBook(Borrow borrow) {
        Borrow record = borrowMapper.findById(borrow.getId()).orElse(null);
        if (record == null) {
            return Result.createByErrorMessage("还书失败");
        }
        Book book = record.getBook();
        book.setStatus(1);
        bookMapper.saveAndFlush(book);
        record.setStatus(1);
        record.setReturnTime(LocalDateTime.now());
        borrowMapper.saveAndFlush(record);
        return Result.createBySuccess();
    }

    @GetMapping("")
    public DataResponse<Borrow> list(DataRequest dataRequest, String keyword, HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return DataResponse.error("用户必须登录");
        }
        User currentUser = (User) user;
        User selectUser = new User();
        selectUser.setId(currentUser.getId());

        Borrow record = new Borrow();
        record.setUser(selectUser);
        Example<Borrow> example = Example.of(record);
        Page<Borrow> all = borrowMapper.findAll(example, PageRequest.of(dataRequest.getPage() - 1, dataRequest.getSize(), Sort.Direction.DESC, "id"));

        log.info("userInfo:{}", JsonUtil.toJson(all));
        return new DataResponse<>(all.getTotalElements(), all.getContent(), dataRequest.getDraw());
    }


}
