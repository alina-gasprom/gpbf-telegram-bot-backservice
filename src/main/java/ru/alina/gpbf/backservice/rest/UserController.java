package ru.alina.gpbf.backservice.rest;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alina.gpbf.backservice.domain.User;
import ru.alina.gpbf.backservice.dto.UserRequest;

import ru.alina.gpbf.backservice.service.impl.RegisterService;

@RestController
@RequestMapping("/v2/users")
public class UserController {
    private final ModelMapper modelMapper;
    private final RegisterService registerService;

    public UserController(ModelMapper modelMapper, RegisterService registerService) {
        this.modelMapper = modelMapper;
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        registerService.createUser(user);
        return ResponseEntity.noContent().build();
    }
}
