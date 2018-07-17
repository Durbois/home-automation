package com.teamtreehouse.homeautomation.event;

import com.teamtreehouse.homeautomation.dao.UserRepository;
import com.teamtreehouse.homeautomation.domain.Control;
import com.teamtreehouse.homeautomation.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Control.class)
public class ControlEventHandler {
  private final UserRepository users;

  @Autowired
  public ControlEventHandler(UserRepository users) {
    this.users = users;
  }

  @HandleAfterSave
  public void setLastModifiedAfterSave(Control control) {
    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = users.findByName(name);
    control.setLastModifiedBy(user);
  }
}
