package ru.yegorr.ms.dto.response;

public class TrackDescriptionDto {
  private Long id;

  private String name;

  private int playsNumber;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPlaysNumber() {
    return playsNumber;
  }

  public void setPlaysNumber(int playsNumber) {
    this.playsNumber = playsNumber;
  }
}
