package top.panghai.multiavatar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: panghai
 * @Date: 2022/06/21/23:26
 * @Description:
 */
@Controller
public class WebController {

    @GetMapping("/*")
    public String index() {
        return "index";
    }

}
