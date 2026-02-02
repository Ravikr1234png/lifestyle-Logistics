package com.ravi.lifestyle.fav.kafka;
import com.ravi.lifestyle.fav.model.FavoriteItem; import com.ravi.lifestyle.fav.service.FavoriteService; import org.springframework.kafka.annotation.KafkaListener; import org.springframework.stereotype.Component;

@Component
public class FavoriteConsumer {
  private final FavoriteService service;
  public FavoriteConsumer(FavoriteService service){
    this.service=service;
  }
  @KafkaListener(topics = "favorites", groupId = "favorite-service")
  public void consume(FavoriteItem item){ service.save(item);
  }
}
