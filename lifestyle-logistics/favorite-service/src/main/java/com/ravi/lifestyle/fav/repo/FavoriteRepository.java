package com.ravi.lifestyle.fav.repo;
import com.ravi.lifestyle.fav.model.FavoriteItem; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface FavoriteRepository extends JpaRepository<FavoriteItem, Long> {
    List<FavoriteItem> findByUsername(String username);
}
