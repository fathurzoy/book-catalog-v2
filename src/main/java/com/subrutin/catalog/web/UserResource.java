package com.subrutin.catalog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.UserDetailReponseDTO;
import com.subrutin.catalog.service.AppUserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserResource {

  private final AppUserService appUserService;

  @GetMapping("v1/user")
  public ResponseEntity<UserDetailReponseDTO> findUserDetail(){
    return ResponseEntity.ok(appUserService.findUserDetail());
  }
  
}
