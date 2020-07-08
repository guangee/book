package com.coding.controller.api;

import com.coding.common.Const;
import com.coding.service.FixService;
import com.guanweiming.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author guanweiming
 */
@Slf4j
@Api(tags = "注册码接口")
@AllArgsConstructor
@RequestMapping(Const.API + "register")
@RestController
public class RegisterController {

    private final FixService fixService;


    @ApiOperation("注册码接口")
    @GetMapping("check")
    public Result<String> register(@RequestParam String register, @RequestParam String machineNo) {
        log.info("register:{}", register);
        return fixService.register(register,machineNo);
    }

}
