package com.teamtreehouse.homeautomation.dao;

import com.teamtreehouse.homeautomation.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

  @Override
  @PreAuthorize("hasRole('ROLE_ADMIN') or #room.administratorExist(authentication.name)")
  Room save(@Param("room") Room room);

  @RestResource(path = "name", rel = "name")
  Page<Room> findByNameContaining(@Param("name") String name, Pageable page);

  @RestResource(path = "area", rel = "area")
  Page<Room> findByAreaLessThan(@Param("area") int area, Pageable page);
}
