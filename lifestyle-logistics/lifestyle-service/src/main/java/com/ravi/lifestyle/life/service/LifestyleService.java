package com.ravi.lifestyle.life.service;
import com.ravi.lifestyle.life.model.LifestyleItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LifestyleService {
  @Value("${lifestyle.api.url:http://localhost:3232}") private String apiBase; private final RestTemplate rest = new RestTemplate();
  public Object getAll() { return rest.getForObject(apiBase + "/lifestyles", Object.class); }
  public Object filter(String query) { return rest.getForObject(apiBase + "/lifestyles?" + query, Object.class); }
  public Object create(LifestyleItem item) { return rest.postForObject(apiBase + "/lifestyles", item, Object.class); }
}
