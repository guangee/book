package com.coding.controller.admin;

import com.coding.common.Const;
import com.coding.common.DataRequest;
import com.coding.common.DataResponse;
import com.coding.domain.Borrow;
import com.coding.mapper.BorrowMapper;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @PostMapping("add")
    public Result<String> add(@RequestParam int num) {
        for (int i = 0; i < num; i++) {
            Borrow borrow = new Borrow();
            borrowMapper.saveAndFlush(borrow);
        }
        return Result.createBySuccess();
    }


    @PostMapping("update")
    public Result<String> update(Borrow food) {
        borrowMapper.saveAndFlush(food);
        return Result.createBySuccess();
    }

    @PostMapping("delete/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        borrowMapper.deleteById(id);
        return Result.createBySuccess();
    }

    @GetMapping("")
    public DataResponse<Borrow> list(DataRequest dataRequest, String keyword) {
        Borrow record = new Borrow();
        ExampleMatcher matching = ExampleMatcher.matching();
        matching.withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Borrow> example = Example.of(record, matching);
        Page<Borrow> all = borrowMapper.findAll(example, PageRequest.of(dataRequest.getPage(), dataRequest.getSize()));
        return new DataResponse<>(all.getTotalElements(), all.getContent(), dataRequest.getDraw());
    }


}
