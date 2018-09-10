package com.teachme.teach.controller;

import com.teachme.teach.config.WebMvcConfig;
import com.teachme.teach.model.Users;
import com.teachme.teach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;



    @RequestMapping("/rest/hello")
    @RestController
    public class UserController {

        @GetMapping("/all")
        public String hello() {
            return "Hello Youtube";
        }

        @PreAuthorize("hasAnyRole('ADMIN')")
        @GetMapping("/secured/all")
        public String securedHello() {
            return "Secured Hello";
        }

        @GetMapping("/secured/alternate")
        public String alternate() {
            return "alternate";
        }

    }
