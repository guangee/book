package com.coding.controller.manager;

import com.coding.common.Const;
import com.coding.common.NavVo;
import com.coding.domain.Borrow;
import com.coding.mapper.BorrowMapper;
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
@RequestMapping(Const.MANAGER + "borrow")
@Controller
public class ManagerBorrowController {

    /**
     * 左侧菜单列表
     */
    public static final NavVo NAV = new NavVo("借书管理", "");

    static {
        NAV.getChild().add(new NavVo("借书列表", Const.MANAGER + "borrow/index"));
    }

    private final BorrowMapper borrowMapper;

    @GetMapping("index")
    public String index(Model model) {
        return "borrow/index";
    }


    @GetMapping("return")
    public String delete(Model model, Long id) {
        model.addAttribute("data", borrowMapper.findById(id).orElse(null));
        return "borrow/return";
    }

}
