package com.subrutin.catalog.service.impl;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.dto.UserDetailReponseDTO;
import com.subrutin.catalog.repository.AppUserRepository;
import com.subrutin.catalog.service.AppUserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService{

  private final AppUserRepository appUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    return appUserRepository.findByUsername(username).orElseThrow((() -> new UsernameNotFoundException("invalid username")));
  }

  @Override
  public UserDetailReponseDTO findUserDetail() {
    SecurityContext ctx = SecurityContextHolder.getContext();
    UserDetailReponseDTO dto = new UserDetailReponseDTO();
    String username = ctx.getAuthentication().getName();
    dto.setUsername(username);

    return dto;
  }
  
}
