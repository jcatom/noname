package cc.jml1024.noname.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = {"/", "", "index", "home", "main"})
    public String index() {
        return "index";
    }
}
