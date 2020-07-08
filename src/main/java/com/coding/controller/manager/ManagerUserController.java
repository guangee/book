package com.coding.controller.manager;

import com.coding.common.Const;
import com.coding.common.NavVo;
import com.coding.domain.User;
import com.coding.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author guanweiming
 */
@ApiIgnore
@AllArgsConstructor
@RequestMapping(Const.MANAGER + "user")
@Controller
public class ManagerUserController {

    /**
     * 左侧菜单列表
     */
    public static final NavVo NAV = new NavVo("用户管理", "");

    static {
        NAV.getChild().add(new NavVo("用户列表", Const.MANAGER + "user/index"));
    }

    private final UserMapper userMapper;


    @GetMapping("index")
    public String index(Model model) {
        return "user/index";
    }


    @GetMapping("add")
    public String add() {
        return "user/add";
    }

    @GetMapping("delete")
    public String delete(Model model, Long id) {
        model.addAttribute("data", userMapper.findById(id).orElse(null));
        return "user/delete";
    }

    @GetMapping("update")
    public String update(Model model, Long id) {
        User user = userMapper.findById(id).orElse(null);
        if (user.getType() == null) {
            user.setType(0);
        }
        model.addAttribute("data", user);
        return "user/update";
    }

}
