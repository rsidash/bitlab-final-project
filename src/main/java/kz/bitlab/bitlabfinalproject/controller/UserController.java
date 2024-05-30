package kz.bitlab.bitlabfinalproject.controller;

import kz.bitlab.bitlabfinalproject.entity.security.dto.UserCreateDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdateDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdatePasswordDto;
import kz.bitlab.bitlabfinalproject.exception.InvalidPasswordException;
import kz.bitlab.bitlabfinalproject.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String getProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());

        return "user/profile";
    }

    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String updateProfile(Model model, @NonNull final UserUpdateDto userUpdateDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("user", userUpdateDto);
            return "user/profile";
        }

        final var user = userService.getCurrentUser();
        model.addAttribute("user", userService.getCurrentUser());

        userService.update(user.getId(), userUpdateDto);

        return "redirect:/profile";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String createUser(@ModelAttribute("userCreateDto") UserCreateDto userCreateDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "user/sign-up";
        }

        userService.create(userCreateDto);
        return "redirect:/";
    }

    @GetMapping("/changePassword")
    @PreAuthorize("isAuthenticated()")
    public String changePasswordPage() {
        return "user/change-password";
    }

    @PostMapping("/changePassword")
    @PreAuthorize("isAuthenticated()")
    public String changePassword(@NonNull final UserUpdatePasswordDto userUpdatePasswordDto, Model model) {
        try {
            final var user = userService.getCurrentUser();
            userService.updatePassword(user.getId(), userUpdatePasswordDto);
        } catch (InvalidPasswordException exception) {
            List<String> errors = List.of(exception.getMessage());
            model.addAttribute("errors", errors);
            return "user/change-password";
        }

        return "redirect:/";
    }
}
