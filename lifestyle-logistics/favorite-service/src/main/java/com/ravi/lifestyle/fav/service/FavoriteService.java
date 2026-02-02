package com.ravi.lifestyle.fav.service;
import com.ravi.lifestyle.fav.model.FavoriteItem; import com.ravi.lifestyle.fav.repo.FavoriteRepository; import org.springframework.stereotype.Service; import java.util.*;

@Service
public class FavoriteService {
  private final FavoriteRepository repo;
  public FavoriteService(FavoriteRepository repo){
    this.repo = repo;
  }
  public FavoriteItem save(FavoriteItem item){
    return repo.save(item);
  }
  public List<FavoriteItem> list(String username){
    return username==null?
            repo.findAll() :
            repo.findByUsername(username);
  }
  public void delete(Long id){
    repo.deleteById(id);
  }
}
