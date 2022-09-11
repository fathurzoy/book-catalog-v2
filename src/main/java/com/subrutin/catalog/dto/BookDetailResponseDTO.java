package com.subrutin.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.subrutin.catalog.domain.CategoryListResponseDTO;

import lombok.Data;

@Data
public class BookDetailResponseDTO implements Serializable{

  private String bookId;

  private List<AuthorResponseDTO> authors;

  private List<CategoryListResponseDTO> categories;

  private PublisherReponseDTO publisher;

  private String bookTitle;

  private String bookDescription;

}
