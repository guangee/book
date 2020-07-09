package com.coding.controller.admin;

import com.coding.common.Const;
import com.coding.common.DataRequest;
import com.coding.common.DataResponse;
import com.coding.domain.Borrow;
import com.coding.domain.User;
import com.coding.mapper.BorrowMapper;
import com.coding.mapper.UserMapper;
import com.coding.service.UserService;
import com.guanweiming.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author guanweiming
 */
@ApiIgnore
@AllArgsConstructor
@RequestMapping(Const.ADMIN + "user")
@RestController
public class AdminUserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final BorrowMapper borrowMapper;


    @ApiOperation("登陆接口，返回用户数据")
    @PostMapping("login")
    public Result<User> login(
            @RequestParam String username,
            @RequestParam String password) {
        Result<User> login = userService.login(username, password);
        User user = login.getData();
        if (user == null || user.getType() == null) {
            return Result.createByErrorMessage("登录失败");
        }
        return login;
    }

    @ApiOperation("添加用户接口")
    @PostMapping("add")
    public Result<User> addUser(User user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return Result.createByErrorMessage("添加失败");
        }
        return userService.addUser(user);
    }


    @PostMapping("delete/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        User user = userMapper.findById(id).orElse(null);
        if (user == null) {
            return Result.createByErrorMessage("用户不存在");
        }
        if (user.getType() == 1) {
            return Result.createByErrorMessage("管理员不能删除");
        }
        Borrow borrow = new Borrow();
        borrow.setUser(user);
        List<Borrow> all = borrowMapper.findAll(Example.of(borrow));
        for (Borrow item : all) {
            if (item.getStatus() == 0) {
                return Result.createByErrorMessage("您有书没还，不可以删除");
            }
        }
        borrowMapper.deleteAll(all);
        userMapper.deleteById(id);
        return Result.createBySuccess();
    }

    @PostMapping("update")
    public Result<String> update(User user) {
        userMapper.saveAndFlush(user);
        return Result.createBySuccess();
    }

    @GetMapping("")
    public DataResponse<User> list(DataRequest dataRequest, String name) {
        return userService.list(dataRequest, name);
    }
}
