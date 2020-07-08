package com.coding.service;

import com.coding.domain.Register;
import com.coding.mapper.RegisterMapper;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author guanweiming
 */
@Slf4j
@AllArgsConstructor
@Service
public class FixService {

    private final RegisterMapper registerMapper;


    public Result<List<Register>> list() {
        return Result.createBySuccess(registerMapper.findAll());
    }

    public Result<String> register(String code, String machineNo) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(machineNo)) {
            return Result.createByErrorMessage("找不到该记录");
        }
        Register register = new Register();
        register.setCode(code);
        register = registerMapper.findOne(Example.of(register)).orElse(null);
        if (register == null) {
            return Result.createByErrorMessage("找不到该记录");
        }
        if (StringUtils.isBlank(register.getMachineCode())) {
            register.setMachineCode(machineNo);
            register.setRegisterTime(LocalDateTime.now());
            registerMapper.saveAndFlush(register);
        } else if (!Objects.equals(register.getMachineCode(), machineNo)) {
            return Result.createBySuccess("机器码校验失败");
        }
        return Result.createBySuccess();
    }

}
