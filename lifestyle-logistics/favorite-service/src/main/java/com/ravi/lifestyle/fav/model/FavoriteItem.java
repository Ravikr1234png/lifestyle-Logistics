package com.ravi.lifestyle.fav.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity @Table(name = "favorites")
public class FavoriteItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  @JsonProperty("Country")
  private String country;
  @JsonProperty("Rank")
  private Integer rank;
  private Double costOfLivingIndex;
  private Double rentIndex;
  private Double groceriesIndex;
  private Double restaurantPriceIndex;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  public Integer getRank() {
    return rank;
  } public void setRank(Integer rank) {
    this.rank = rank;
  }
  public Double getCostOfLivingIndex() {
    return costOfLivingIndex;
  }
  public void setCostOfLivingIndex(Double v) {
    this.costOfLivingIndex = v;
  }
  public Double getRentIndex() {
    return rentIndex;
  }
  public void setRentIndex(Double v) {
    this.rentIndex = v;
  }
  public Double getGroceriesIndex() {
    return groceriesIndex;
  }
  public void setGroceriesIndex(Double v) {
    this.groceriesIndex = v;
  }
  public Double getRestaurantPriceIndex() {
    return restaurantPriceIndex;
  }
  public void setRestaurantPriceIndex(Double v) {
    this.restaurantPriceIndex = v;
  }
}
