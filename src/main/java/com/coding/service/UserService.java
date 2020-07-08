package com.coding.service;

import com.coding.common.DataRequest;
import com.coding.common.DataResponse;
import com.coding.domain.User;
import com.coding.mapper.UserMapper;
import com.guanweiming.common.utils.HttpKit;
import com.guanweiming.common.utils.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author guanweiming
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;


    public Result<User> login(String username, String password) {
        User user = getUserByUsernameAndPassword(username, password);
        if (user == null) {
            /*如果系统中的用户数量为0，则认为当前是管理员在注册*/
            long count = userMapper.count();
            if (count == 0) {
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setType(1);
                userMapper.saveAndFlush(user);
            } else {
                return Result.createByErrorMessage("登陆失败");
            }
        }
        HttpKit.getRequest().getSession().setAttribute("user", user);
        return Result.createBySuccess(user);
    }


    private User getUserByUsernameAndPassword(String username, String password) {
        User record = new User();
        record.setUsername(username);
        record.setPassword(password);
        List<User> list = userMapper.findAll(Example.of(record));
        return list.size() == 0 ? null : list.get(0);
    }

    public DataResponse<User> list(DataRequest dataRequest, String name) {
        User record = new User();
        record.setUsername(name);
        ExampleMatcher matching = ExampleMatcher.matching();
        matching.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<User> example = Example.of(record, matching);
        Page<User> all = userMapper.findAll(example, PageRequest.of(dataRequest.getPage(), dataRequest.getSize()));
        return new DataResponse<>(all.getTotalElements(), all.getContent(), dataRequest.getDraw());
    }


    public Result<User> addUser(String username, String password) {
        User record = new User();
        record.setUsername(username);
        List<User> list = userMapper.findAll(Example.of(record));
        if (!CollectionUtils.isEmpty(list)) {
            return Result.createByErrorMessage("用户已经存在，无法添加");
        }
        record.setPassword(password);
        record = userMapper.saveAndFlush(record);
        return Result.createBySuccess(record);
    }


    public Long getCurrentUserId() {
        if (getCurrentUser() == null) {
            return null;
        }
        return getCurrentUser().getId();
    }

    public User getCurrentUser() {
        return (User) HttpKit.getRequest().getSession().getAttribute("user");
    }

    public User getUserById(Long userId) {
        return userMapper.findById(userId).orElse(null);
    }


    public Result<User> addUser(User user) {
        User record = new User();
        record.setUsername(user.getUsername());
        List<User> list = userMapper.findAll(Example.of(record));
        if (!CollectionUtils.isEmpty(list)) {
            return Result.createByErrorMessage("用户已经存在，无法添加");
        }
        user.setType(0);
        userMapper.saveAndFlush(user);
        return Result.createBySuccess();
    }
}
