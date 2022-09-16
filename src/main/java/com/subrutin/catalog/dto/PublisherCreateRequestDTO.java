package com.subrutin.catalog.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.subrutin.catalog.annotation.LogThisArgs;

import lombok.Data;

@LogThisArgs
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherCreateRequestDTO implements Serializable{
  
  @NotBlank
  private String publisherName;

  @NotBlank
  private String companyName;

  private String address;
  
}
