package com.coding.controller.manager;

import com.coding.common.Const;
import com.coding.common.NavVo;
import com.coding.domain.Book;
import com.coding.domain.Borrow;
import com.coding.mapper.BookMapper;
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
@RequestMapping(Const.MANAGER + "book")
@Controller
public class ManagerBookController {

    /**
     * 左侧菜单列表
     */
    public static final NavVo NAV = new NavVo("图书管理", "");

    static {
        NAV.getChild().add(new NavVo("图书列表", Const.MANAGER + "book/index"));
    }

    private final BookMapper bookMapper;

    @GetMapping("index")
    public String index(Model model) {
        return "book/index";
    }


    @GetMapping("add")
    public String add() {
        return "book/add";
    }

    @GetMapping("borrow")
    public String borrow(Model model, Long id) {
        model.addAttribute("data", bookMapper.findById(id).orElse(null));
        return "book/borrow";
    }


    @GetMapping("delete")
    public String delete(Model model, Long id) {
        model.addAttribute("data", bookMapper.findById(id).orElse(null));
        return "book/delete";
    }

    @GetMapping("update")
    public String update(Model model, Long id) {
        Book book = bookMapper.findById(id).orElse(null);
        model.addAttribute("data", book);
        return "book/update";
    }

}
