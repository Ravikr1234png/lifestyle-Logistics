package com.ravi.lifestyle.auth.controller;
import com.ravi.lifestyle.auth.client.UserClient;
import com.ravi.lifestyle.auth.client.UserDTO;
import com.ravi.lifestyle.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserClient userClient;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  @Value("${jwt.secret:supersecretjwt}")
  private String secret;
  public AuthController(UserClient userClient) {
    this.userClient = userClient;
  }
  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@RequestBody UserDTO dto) {
    return ResponseEntity.ok(userClient.register(dto));
  }
  @PostMapping("/login")
  public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> req) {
    String username = req.get("username"), password = req.get("password");
    UserDTO user = userClient.getByUsername(username);
    if (user == null || user.password == null || !encoder.matches(password, user.password)) {
      return ResponseEntity.status(401).body(Map.of("error","Invalid credentials")); }
    String token = JwtUtil.generateToken(username, secret, 3600_000);
    return ResponseEntity.ok(Map.of("token", token));
  }
}
