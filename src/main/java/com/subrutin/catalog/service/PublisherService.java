package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.PublisherListResponseDTO;
import com.subrutin.catalog.dto.PublisherReponseDTO;
import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;

public interface PublisherService {

  public void createPublisher(PublisherCreateRequestDTO dto);

  public Publisher findPublisher(String publisherId);

  public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto);

  public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit, String sortBy, String direction, String publisherName);

  public PublisherReponseDTO constructDTO(Publisher publisher);
  
}
