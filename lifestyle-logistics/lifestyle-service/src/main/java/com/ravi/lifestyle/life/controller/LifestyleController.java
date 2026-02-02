package com.ravi.lifestyle.life.controller;
import com.ravi.lifestyle.life.model.LifestyleItem; import com.ravi.lifestyle.life.service.LifestyleService;
import org.springframework.kafka.core.KafkaTemplate; import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/lifestyles")
public class LifestyleController {
  private final LifestyleService service; private final KafkaTemplate<String, Object> kafka;
  public LifestyleController(LifestyleService service, KafkaTemplate<String,Object> kafka) { this.service=service; this.kafka=kafka; }
  @GetMapping public Object all() { return service.getAll(); }
  @GetMapping("/search") public Object filter(@RequestParam String query) { return service.filter(query); }
  @PostMapping public Object create(@RequestBody LifestyleItem item) { return service.create(item); }
  @PostMapping("/favorite") public String favorite(@RequestBody LifestyleItem item) { kafka.send("favorites", item); return "Queued to favorites"; }
}
