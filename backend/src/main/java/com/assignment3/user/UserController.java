package com.assignment3.user;

import com.assignment3.user.dto.UserDTO;
import com.assignment3.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.assignment3.UrlMapping.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public void create(@RequestBody UserDTO user) {
        userService.create(user);
    }

    @PatchMapping
    public void edit(@RequestBody UserDTO user){
        userService.edit(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
