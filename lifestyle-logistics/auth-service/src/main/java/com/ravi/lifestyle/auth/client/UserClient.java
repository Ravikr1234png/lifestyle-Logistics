package com.ravi.lifestyle.auth.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userprofile", url = "${userprofile.url:http://localhost:8082}")
public interface UserClient {
  @GetMapping("/users/{username}")
  UserDTO getByUsername(@PathVariable("username") String username);
  @PostMapping("/users")
  UserDTO register(@RequestBody UserDTO dto);
}
