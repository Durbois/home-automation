package com.teamtreehouse.homeautomation.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version
  private Long version;

  @Column
  private String name;

  @Column
  @Range(min = 0, max = 1000)
  private Integer area;

  @OneToMany(mappedBy = "room")
  private List<Device> devices = new ArrayList<>();

  @ManyToMany
  private List<User> administrators = new ArrayList<>();

  public List<Device> getDevices () {
    return devices;
  }

  public void addDevice (Device device) {
    devices.add(device);
    device.setRoom(this);
  }

  public void removeDevice(Device device) {
    devices.remove(device);
    device.setRoom(null);
  }

  public List<User> getAdministrators() {
    return administrators;
  }

  public boolean administratorExist(String name){
    return getAdministrators().stream()
                 .filter(admin ->
                   admin.getName().equals(name)
                 ).findFirst().isPresent();
  }

  public void setAdministrators(
      List<User> administrators) {
    this.administrators = administrators;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getArea() {
    return area;
  }

  public void setArea(Integer area) {
    this.area = area;
  }
}
