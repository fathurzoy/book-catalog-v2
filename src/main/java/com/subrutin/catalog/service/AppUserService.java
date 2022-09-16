package com.subrutin.catalog.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.subrutin.catalog.domain.AppUser;
import com.subrutin.catalog.dto.UserDetailReponseDTO;

public interface AppUserService extends UserDetailsService{

  public UserDetailReponseDTO findUserDetail();
}
