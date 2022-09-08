package com.subrutin.catalog.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorResponseDTO;
import com.subrutin.catalog.dto.AuthorUpdateRequestDTO;
import com.subrutin.catalog.exception.BadRequestException;
import com.subrutin.catalog.repository.AuthorRepository;
import com.subrutin.catalog.service.AuthorService;

import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice.Local;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{

  private final AuthorRepository authorRepository;

  @Override
  public AuthorResponseDTO findAuthorById(Long id) {
    // 1. fetch data form database
    Author author = authorRepository.findById(id).orElseThrow(()->new BadRequestException("Invalid authorId"));
    // 2. author -> authorResponseDTO
    AuthorResponseDTO dto = new AuthorResponseDTO();
    dto.setAuthorName(author.getName());
    dto.setBirhtDate(author.getBirthDate().toEpochDay());
    return dto;
  }

  @Override
  public void createNewAuthor(List<AuthorCreateRequestDTO> dtos) {

    List<Author> authors = dtos.stream().map((dto)->{
      Author author = new Author();
      author.setName(dto.getAuthorName());
      author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
      return author;
    }).collect(Collectors.toList());

    authorRepository.saveAll(authors);
    
  }

  @Override
  public void updateAuthor(Long authorId, AuthorUpdateRequestDTO dto) {
    Author author = authorRepository.findById(authorId).orElseThrow(()->new BadRequestException("Invalid authorId"));

    author.setName(dto.getAuthorName()==null?author.getName():dto.getAuthorName());
    author.setBirthDate(dto.getBirthDate()==null?author.getBirthDate():LocalDate.ofEpochDay(dto.getBirthDate()));

    authorRepository.save(author);
  }

  // soft delete 
  @Override
  public void deleteAuthor(Long authorId) {
    //1. select data
    //2. delete data
    // or
    // 1. delete (hard delete)
    authorRepository.deleteById(authorId);

    // // softdelete
    // // 1.select data deleted=false
    // Author author = authorRepository.findById(authorId).orElseThrow(() -> new BadRequestException("invalid authorId"));
    // // 2.update deleted=true
    // author.setDeleted(Boolean.TRUE);
    // authorRepository.save(author);
  }

  
}
