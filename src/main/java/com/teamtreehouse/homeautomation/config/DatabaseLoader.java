package com.teamtreehouse.homeautomation.config;

import com.teamtreehouse.homeautomation.dao.ControlRepository;
import com.teamtreehouse.homeautomation.dao.DeviceRepository;
import com.teamtreehouse.homeautomation.dao.RoomRepository;
import com.teamtreehouse.homeautomation.dao.UserRepository;
import com.teamtreehouse.homeautomation.domain.Control;
import com.teamtreehouse.homeautomation.domain.Device;
import com.teamtreehouse.homeautomation.domain.Room;
import com.teamtreehouse.homeautomation.domain.User;
import com.teamtreehouse.homeautomation.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseLoader implements ApplicationRunner {

  private RoomRepository roomRepository;
  private UserRepository userRepository;
  private DeviceRepository deviceRepository;
  private ControlRepository controlRepository;
  private final DetailsService detailsService;

  @Autowired
  public DatabaseLoader(RoomRepository roomRepository, UserRepository userRepository,
                        DeviceRepository deviceRepository, ControlRepository controlRepository,
                        DetailsService detailsService) {
    this.roomRepository = roomRepository;
    this.userRepository = userRepository;
    this.deviceRepository = deviceRepository;
    this.controlRepository = controlRepository;
    this.detailsService = detailsService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    User user1 = new User();
    user1.setName("user1");
    user1.setPassword("1234");
    user1.setRoles(new String[]{"ROLE_ADMIN, ROLE_USER"});
    userRepository.save(user1);

    User user2 = new User();
    user2.setName("user2");
    user2.setPassword("1234");
    user2.setRoles(new String[]{"ROLE_ADMIN, ROLE_USER"});
    userRepository.save(user2);

    User user3 = new User();
    user3.setName("user3");
    user3.setPassword("1234");
    user3.setRoles(new String[]{"ROLE_USER"});
    userRepository.save(user3);

    User user4 = new User();
    user4.setName("user4");
    user4.setPassword("1234");
    user4.setRoles(new String[]{"ROLE_USER"});
    userRepository.save(user4);

    UserDetails userDetails = detailsService.loadUserByUsername("user1");

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    Room room1 = new Room();
    room1.setName("room1");
    room1.setArea(500);
    room1.setAdministrators(Arrays.asList(user1));
    roomRepository.save(room1);

    Room room2 = new Room();
    room2.setName("room2");
    room2.setArea(500);
    room2.setAdministrators(Arrays.asList(user1));
    roomRepository.save(room2);

    Room room3 = new Room();
    room3.setName("room3");
    room3.setArea(500);
    room3.setAdministrators(Arrays.asList(user1));
    roomRepository.save(room3);

    Device device1 = new Device();
    device1.setName("device1");
    device1.setRoom(room1);
    deviceRepository.save(device1);

    Device device2 = new Device();
    device2.setName("device2");
    device2.setRoom(room2);
    deviceRepository.save(device2);

    Device device3 = new Device();
    device3.setName("device3");
    device3.setRoom(room3);
    deviceRepository.save(device3);

    Control control1 = new Control();
    control1.setName("control1");
    control1.setValue(12);
    control1.setLastModifiedBy(user1);
    control1.setDevice(device1);
    controlRepository.save(control1);

    Control control2 = new Control();
    control2.setName("control2");
    control2.setValue(12);
    control2.setLastModifiedBy(user1);
    control2.setDevice(device2);
    controlRepository.save(control2);

    Control control3 = new Control();
    control3.setName("control3");
    control3.setValue(12);
    control3.setLastModifiedBy(user1);
    control3.setDevice(device3);
    controlRepository.save(control3);
  }
}