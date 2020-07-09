package com.coding.controller.admin;

import com.coding.common.Const;
import com.coding.common.DataRequest;
import com.coding.common.DataResponse;
import com.coding.domain.Book;
import com.coding.mapper.BookMapper;
import com.guanweiming.common.utils.JsonUtil;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author guanweiming
 */
@Slf4j
@ApiIgnore
@AllArgsConstructor
@RequestMapping(Const.ADMIN + "book")
@RestController
public class AdminBookController {

    private final BookMapper bookMapper;

    @PostMapping("add")
    public Result<String> add(Book food) {
        bookMapper.saveAndFlush(food);
        return Result.createBySuccess();
    }

    @PostMapping("update")
    public Result<String> update(Book food) {
        bookMapper.saveAndFlush(food);
        return Result.createBySuccess();
    }

    @PostMapping("delete/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        bookMapper.deleteById(id);
        return Result.createBySuccess();
    }

    @GetMapping("")
    public DataResponse<Book> list(DataRequest dataRequest, String keyword) {
        Book record = new Book();
        record.setName(keyword);
        ExampleMatcher matching = ExampleMatcher.matching()
                .withMatcher("name", item -> item.contains());
        log.info("keyword:{}", keyword);
        Example<Book> example = Example.of(record, matching);
        Page<Book> all = bookMapper.findAll(example, PageRequest.of(dataRequest.getPage(), dataRequest.getSize(), Sort.Direction.DESC, "id"));
        return new DataResponse<>(all, dataRequest.getDraw());
    }


}
