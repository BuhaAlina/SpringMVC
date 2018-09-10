package com.teachme.teach.controller;

import com.teachme.teach.config.WebMvcConfig;
import com.teachme.teach.model.Users;
import com.teachme.teach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private WebMvcConfig webMvcConfig;
    @Autowired
    private UserService userService;

	/*@RequestMapping(value="/login", method = RequestMethod.GET)//{"/", "/login"}
	public ModelAndView login(){//(@RequestBody Users user){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}*/

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
	/*@RequestMapping(value="/login", method = RequestMethod.POST)//{"/", "/login"}
	public ModelAndView postLogin(@RequestBody Users user){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView modelAndView = new ModelAndView();
		auth.getAuthorities().equals("ADMIN");
		modelAndView.setViewName("admin/home");
		return modelAndView;*//*else{
		//ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("user/home");
	return modelAndView;}*//*
	}*/


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Users user = new Users();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Users user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Users userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Users());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUserByEmail(auth.getName());
        //System.out.print(auth.getAuthorities()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //if(user)
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public ModelAndView userHome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("userMessage","Content Available Only for Users with User Role");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }
    @RequestMapping(value="/denied", method = RequestMethod.GET)
    public ModelAndView accessDenied(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findUserByEmail(auth.getName());


        modelAndView.setViewName("access-denied");
        return modelAndView;
    }


}