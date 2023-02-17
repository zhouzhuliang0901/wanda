package com.wondersgroup.msg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping(value = {"/", "/test"})
    public String loginPage() {
        return "cometd";
    }

}
