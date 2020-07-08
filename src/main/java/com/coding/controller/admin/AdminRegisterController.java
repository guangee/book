package com.coding.controller.admin;

import com.coding.common.Const;
import com.coding.common.DataRequest;
import com.coding.common.DataResponse;
import com.coding.domain.Register;
import com.coding.mapper.RegisterMapper;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

/**
 * @author guanweiming
 */
@Slf4j
@ApiIgnore
@AllArgsConstructor
@RequestMapping(Const.ADMIN + "register")
@RestController
public class AdminRegisterController {

    private final RegisterMapper registerMapper;

    @PostMapping("add")
    public Result<String> add(@RequestParam int num) {
        for (int i = 0; i < num; i++) {
            Register register = new Register();
            register.setCode(UUID.randomUUID().toString());
            registerMapper.saveAndFlush(register);
        }
        return Result.createBySuccess();
    }


    @PostMapping("update")
    public Result<String> update(Register food) {
        registerMapper.saveAndFlush(food);
        return Result.createBySuccess();
    }

    @PostMapping("delete/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        registerMapper.deleteById(id);
        return Result.createBySuccess();
    }

    @GetMapping("")
    public DataResponse<Register> list(DataRequest dataRequest, String keyword) {
        Register record = new Register();
        record.setCode(keyword);
        ExampleMatcher matching = ExampleMatcher.matching();
        matching.withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Register> example = Example.of(record, matching);
        Page<Register> all = registerMapper.findAll(example, PageRequest.of(dataRequest.getPage(), dataRequest.getSize()));
        return new DataResponse<>(all.getTotalElements(), all.getContent(), dataRequest.getDraw());
    }


}
