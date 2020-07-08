package com.coding.service;

import com.coding.domain.Borrow;
import com.coding.mapper.BorrowMapper;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guanweiming
 */
@Slf4j
@AllArgsConstructor
@Service
public class FixService {

    private final BorrowMapper borrowMapper;


    public Result<List<Borrow>> list() {
        return Result.createBySuccess(borrowMapper.findAll());
    }

    public Result<String> register(String code, String machineNo) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(machineNo)) {
            return Result.createByErrorMessage("找不到该记录");
        }
        Borrow borrow = new Borrow();
        borrow = borrowMapper.findOne(Example.of(borrow)).orElse(null);
        if (borrow == null) {
            return Result.createByErrorMessage("找不到该记录");
        }
        return Result.createBySuccess();
    }

}
