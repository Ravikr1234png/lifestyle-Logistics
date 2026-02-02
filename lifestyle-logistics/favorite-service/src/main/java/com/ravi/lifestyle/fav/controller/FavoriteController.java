package com.ravi.lifestyle.fav.controller;
import com.ravi.lifestyle.fav.model.FavoriteItem; import com.ravi.lifestyle.fav.service.FavoriteService; import org.springframework.web.bind.annotation.*; import java.util.*;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
  private final FavoriteService service; public FavoriteController(FavoriteService service){
    this.service=service;
  }
  @GetMapping
  public List<FavoriteItem> list(@RequestParam(required=false) String username){
    return service.list(username);
  }
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    service.delete(id);
  }
}
