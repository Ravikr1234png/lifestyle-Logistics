package com.ravi.lifestyle.fav.kafka;
import com.ravi.lifestyle.fav.model.FavoriteItem; import org.apache.kafka.clients.consumer.ConsumerConfig; import org.apache.kafka.common.serialization.StringDeserializer; import org.springframework.beans.factory.annotation.Value; import org.springframework.context.annotation.*; import org.springframework.kafka.annotation.EnableKafka; import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory; import org.springframework.kafka.core.DefaultKafkaConsumerFactory; import org.springframework.kafka.support.serializer.JsonDeserializer; import java.util.*;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
  @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
  private String bootstrap;
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, FavoriteItem> kafkaListenerContainerFactory(){
    Map<String,Object> props=new HashMap<>(); props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "favorite-service");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    DefaultKafkaConsumerFactory<String, FavoriteItem> f=new DefaultKafkaConsumerFactory<>(props);
    ConcurrentKafkaListenerContainerFactory<String,
            FavoriteItem> cf=new ConcurrentKafkaListenerContainerFactory<>(); cf.setConsumerFactory(f); return cf;
  }
}
