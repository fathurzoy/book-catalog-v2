package com.subrutin.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
public class BookDetailDTO implements Serializable{

  private Long bookId;

  private String authorName;

  private String bookTitle;

  private String bookDescription;

}
