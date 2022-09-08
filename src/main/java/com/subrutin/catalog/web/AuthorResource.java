package com.subrutin.catalog.web;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorResponseDTO;
import com.subrutin.catalog.dto.AuthorUpdateRequestDTO;
import com.subrutin.catalog.service.AuthorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AuthorResource {

  private final AuthorService authorService;

  @GetMapping("/author/{id}/detial")
  public ResponseEntity<AuthorResponseDTO> findAuthorById(@PathVariable Long id){
    // AuthorResponseDTO result = authorService.findAuthorById(id);
    // return ResponseEntity.ok().body(result);
    return ResponseEntity.ok().body(authorService.findAuthorById(id));
  }

  @PostMapping("/authors")
  public ResponseEntity<Void> createNewAuthor(@RequestBody @Valid List<AuthorCreateRequestDTO> dto){
    authorService.createNewAuthor(dto);
    return ResponseEntity.created(URI.create("/author")).build();
  }
  
  @PutMapping("/author/{authorId}")
  public ResponseEntity<Void> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorUpdateRequestDTO dto){
    authorService.updateAuthor(authorId, dto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/author/{authorId}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId){
    authorService.deleteAuthor(authorId);
    return ResponseEntity.ok().build();
  }
}
