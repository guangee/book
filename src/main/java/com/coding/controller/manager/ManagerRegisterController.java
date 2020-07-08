package com.coding.controller.manager;

import com.coding.common.Const;
import com.coding.common.NavVo;
import com.coding.domain.Register;
import com.coding.mapper.RegisterMapper;
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
@RequestMapping(Const.MANAGER + "register")
@Controller
public class ManagerRegisterController {

    /**
     * 左侧菜单列表
     */
    public static final NavVo NAV = new NavVo("注册码管理", "");

    static {
        NAV.getChild().add(new NavVo("注册码列表", Const.MANAGER + "register/index"));
    }

    private final RegisterMapper registerMapper;

    @GetMapping("index")
    public String index(Model model) {
        return "register/index";
    }


    @GetMapping("add")
    public String add() {
        return "register/add";
    }

    @GetMapping("pic")
    public String pic(Model model, String url) {
        model.addAttribute("url", url);
        return "fix/pic";
    }


    @GetMapping("delete")
    public String delete(Model model, Long id) {
        model.addAttribute("data", registerMapper.findById(id).orElse(null));
        return "register/delete";
    }

    @GetMapping("update")
    public String update(Model model, Long id) {
        Register register = registerMapper.findById(id).orElse(null);
        model.addAttribute("data", register);
        return "register/update";
    }

}
