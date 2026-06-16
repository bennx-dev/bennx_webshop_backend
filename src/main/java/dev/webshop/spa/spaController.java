package dev.webshop.spa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class spaController {

    @RequestMapping(value = {
            "/{path:[^\\.]*}",
            "/**/{path:[^\\.]*}"
    })
    public String forward() {
        return "forward:/index.html";
    }
}