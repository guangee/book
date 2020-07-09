package com.coding.controller;

import com.coding.common.Const;
import com.coding.common.NavVo;
import com.coding.controller.manager.ManagerBookController;
import com.coding.controller.manager.ManagerBorrowController;
import com.coding.controller.manager.ManagerUserController;
import com.coding.domain.User;
import com.coding.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.guanweiming.common.utils.HttpKit;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chezhu.xin
 */
@Slf4j
@AllArgsConstructor
@Controller
public class IndexController {

    private static final Map<String, Object> siteInfo = Maps.newHashMap();

    static {
        siteInfo.put("name", "图书馆");
        siteInfo.put("year", LocalDate.now().getYear());
    }

    private final UserService userService;

    @GetMapping("")
    public String index(HttpSession session, Model model) {
        session.setAttribute(Const.NAV, nav());
        /*如果用户没有登录，返回登录页面*/
        if (session.getAttribute("user") == null) {
            model.addAttribute("siteInfo", siteInfo);
            return "login";
        }
        /*用户已经登录，把网站信息输出到页面上*/
        model.addAttribute("siteInfo", siteInfo);
        return "index";
    }

    @ApiOperation(value = "导航数据", hidden = true)
    @ResponseBody
    @GetMapping("nav")
    public List<NavVo> nav() {
        HttpSession session = HttpKit.getRequest().getSession();
        Object obj = session.getAttribute("user");
        if (!(obj instanceof User)) {
            return Lists.newArrayList();
        }
        User user = (User) obj;
        List<NavVo> list = Lists.newArrayList();
        list.add(new NavVo("首页", "desktop"));
        log.info("用户身份:{}", user.getType());
        if (Objects.equals(user.getType(), 1)) {
            list.add(ManagerUserController.NAV);
            list.add(ManagerBorrowController.NAV);
            list.add(ManagerBookController.NAV);
        } else {
            list.add(ManagerBookController.NAV);
            list.add(ManagerBorrowController.NAV);

        }
        return list;
    }

    @ApiOperation(value = "登陆页面", hidden = true)
    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("siteInfo", siteInfo);
        return "login";
    }

    @ApiOperation(value = "注册页面", hidden = true)
    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("siteInfo", siteInfo);
        return "register";
    }


    @ApiOperation(value = "退出登陆", hidden = true)
    @GetMapping("logout")
    public String logout() {
        HttpKit.getRequest().getSession().removeAttribute("user");
        return "redirect:/";
    }

    @ApiOperation(value = "桌面", hidden = true)
    @GetMapping("desktop")
    public String desktop(Model model) {
        model.addAttribute("siteInfo", siteInfo);
        model.addAttribute("user", userService.getUserById(userService.getCurrentUserId()));
        System.out.println(userService.getCurrentUser());
        return "desktop";
    }
}
