package com.teamtreehouse.homeautomation.dao;

import com.teamtreehouse.homeautomation.domain.Control;
import com.teamtreehouse.homeautomation.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepository extends PagingAndSortingRepository<Control, Long> {

  @Override
  @PreAuthorize("hasRole('ROLE_ADMIN') or #control.device.room.administratorExist(authentication.name)")
  Control save(@Param("control") Control entity);
}
