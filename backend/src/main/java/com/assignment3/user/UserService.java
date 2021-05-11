package com.assignment3.user;

import com.assignment3.user.mapper.UserMapper;
import com.assignment3.user.dto.UserDTO;
import com.assignment3.user.dto.UserListDTO;
import com.assignment3.user.dto.UserMinimalDTO;
import com.assignment3.user.model.ERole;
import com.assignment3.user.model.Role;
import com.assignment3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }


    /*public UserDetailsImpl create(UserDetailsImpl user) {
        userRepository.save(userMapper.fromUserDetailsImpl(user));
        return null;
    }*/

    public User findDoctorByName(String name){
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new RuntimeException("Not found"));

        for (Role role : user.getRoles())
            if (role.getName() == ERole.DOCTOR)
                return user;

        throw new RuntimeException("No doctor found");
    }

    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }


    public void edit(UserDTO user) {
        User newUser = User.builder()
                .id(user.getId()) //no id for a create operation!!
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build();

        Set<Role> roles = new HashSet<>();

        if (user.getRole() == null){
            Role defaultRole = roleRepository.findByName(ERole.SECRETARY)
                    .orElseThrow(() -> new RuntimeException("Cannot find SECRETARY role"));
            roles.add(defaultRole);
        }
        else{
            Role role = roleRepository.findByName(ERole.valueOf(user.getRole()))
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + user.getRole()));
            roles.add(role);
        }

        newUser.setRoles(roles);
        userRepository.save(newUser);
    }

    public void create(UserDTO user) {
        User newUser = User.builder()
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build();

        Set<Role> roles = new HashSet<>();

        if (user.getRole() == null){
            Role defaultRole = roleRepository.findByName(ERole.SECRETARY)
                    .orElseThrow(() -> new RuntimeException("Cannot find SECRETARY role"));
            roles.add(defaultRole);
        }
        else{
            Role role = roleRepository.findByName(ERole.valueOf(user.getRole()))
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + user.getRole()));
            roles.add(role);
        }

        newUser.setRoles(roles);
        userRepository.save(newUser);
    }
}
