package com.teamtreehouse.homeautomation.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Device {

  @Id
  @GeneratedValue
  private Long id;

  @Version
  private Long version;

  @Column
  private String name;

  @ManyToOne
  private Room room;

  @OneToMany(mappedBy = "device")
  private List<Control> controls = new ArrayList<>();

  public List<Control> getControls () {
    return controls;
  }

  public void addControl(Control control) {
    controls.add(control);
    control.setDevice(this);
  }

  public void removeControl (Control control) {
    controls.remove(control);
    control.setDevice(null);
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
