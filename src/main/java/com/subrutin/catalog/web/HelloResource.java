package com.subrutin.catalog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.HelloMessageResponseDTO;
import com.subrutin.catalog.service.GreetingService;

@RestController
public class HelloResource {

  Logger log = LoggerFactory.getLogger(HelloResource.class);

  private GreetingService greetingService;
  
  public HelloResource(GreetingService greetingService) {
    super();
    this.greetingService = greetingService;
  }
  
  @GetMapping("/hello")
  public ResponseEntity<HelloMessageResponseDTO> helloWOrd(){
    log.info("this is log info");
    log.warn("this is log warn");
    log.debug("this is log debug");
    log.error("this is log error");
    log.trace("this is log trace");
    HelloMessageResponseDTO dto = new HelloMessageResponseDTO();
    dto.setMessage(greetingService.sayGreeting());
    return ResponseEntity.ok().body(dto);
  }
}