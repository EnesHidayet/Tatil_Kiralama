package org.enes.controller;

import org.enes.dto.request.AuthActivationRequestDto;
import org.enes.dto.request.AuthLoginRequestDto;
import org.enes.dto.request.AuthRegisterRequestDto;
import org.enes.entity.Auth;
import org.enes.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/activation")
    public ResponseEntity<Boolean> activation(AuthActivationRequestDto dto) {
        return ResponseEntity.ok(authService.activation(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthLoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(String token) {
        return ResponseEntity.ok(authService.delete(token));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Auth> getById(String id) {
        return ResponseEntity.ok(authService.findById(id).get());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Auth>> getAll() {
        return ResponseEntity.ok(authService.getAll());
    }
}
