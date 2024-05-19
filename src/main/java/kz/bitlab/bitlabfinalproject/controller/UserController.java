//package kz.bitlab.bitlabfinalproject.controller;
//
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserCreateDto;
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdateDto;
//import kz.bitlab.bitlabfinalproject.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping("/profile")
//    public String getProfile(Model model) {
//        model.addAttribute("user", userService.getCurrentUser());
//
//        return "profile";
//    }
//
//    @GetMapping("/sign-up")
//    public String signUpPage() {
//        return "sign-up";
//    }
//
//    @PostMapping("/sign-up")
//    public String createUser(@ModelAttribute("userCreateDto") UserCreateDto userCreateDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "sign-up";
//        }
//
//        userService.create(userCreateDto);
//        return "redirect:/";
//    }
//}
