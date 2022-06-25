package top.panghai.multiavatar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: panghai
 * @Date: 2022/06/25/15:17
 * @Description:
 */
@Controller
@RequestMapping("/error")
public class ErrorPage implements ErrorController {

    Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @Override
    public String getErrorPath() {
        logger.info("进入自定义错误页面");
        return "error";
    }

    @RequestMapping
    public String error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("========="+request.getRequestURI());
//        request.getRequestDispatcher("/svg").forward(request, response);
        return getErrorPath();
    }
}
