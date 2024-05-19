//package kz.bitlab.bitlabfinalproject.controller.rest;
//
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserCreateDto;
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
//import kz.bitlab.bitlabfinalproject.entity.security.dto.UserUpdateDto;
//import kz.bitlab.bitlabfinalproject.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class UserRestController {
//    private final UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<UserDto> registerUser(@RequestBody UserCreateDto userCreateDto) {
//        UserDto createdUser = userService.create(userCreateDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
//
//    @GetMapping("/profile")
//    public ResponseEntity<UserDto> getCurrentUser() {
//        UserDto userDto = userService.getCurrentUser();
//        return ResponseEntity.ok(userDto);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
//        UserDto updatedUser = userService.update(id, userUpdateDto);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
