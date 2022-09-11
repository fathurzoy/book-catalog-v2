package com.subrutin.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookDetailResponseDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;
import com.subrutin.catalog.exception.BadRequestException;
import com.subrutin.catalog.repository.AuthorRepository;
import com.subrutin.catalog.repository.BookRepository;
import com.subrutin.catalog.service.AuthorService;
import com.subrutin.catalog.service.BookService;
import com.subrutin.catalog.service.CategoryService;
import com.subrutin.catalog.service.PublisherService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements	BookService{

	private final BookRepository bookRepository;

	private final AuthorService authorService;

	private final CategoryService categoryService;

	private final PublisherService publisherService;

	@Override
	public BookDetailResponseDTO finBookDetailById(String bookId) {
		Book book = bookRepository.findBySecureId(bookId).orElseThrow(() -> new BadRequestException("book_id.invalid"));
		BookDetailResponseDTO dto = new BookDetailResponseDTO();
		dto.setBookId(book.getSecureId());
		dto.setCategories(categoryService.constructDTO(book.getCategories()));
		dto.setAuthors(authorService.constructDTO(book.getAuthors()));
		dto.setPublisher(publisherService.constructDTO(book.getPublisher()));
		dto.setBookTitle(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}

	@Override
	public List<BookDetailResponseDTO> finBookListDetail() {
		List<Book> books= bookRepository.findAll();
		return books.stream().map((b) -> {
			BookDetailResponseDTO dto = new BookDetailResponseDTO();
			// dto.setAuthorName(b.getAuthor().getName());
			dto.setBookDescription(b.getDescription());
			// dto.setBookId(b.getId());
			dto.setBookTitle(b.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewBook(BookCreateRequestDTO dto) {
		List<Author> authors = authorService.findAuthors(dto.getAuthorIdList()); 
		List<Category> categories = categoryService.findCategories(dto.getCategoryList());
		Publisher publisher = publisherService.findPublisher(dto.getPublisherId());

		Book book = new Book();
		book.setAuthors(authors);
		book.setCategories(categories);
		book.setPublisher(publisher);
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		bookRepository.save(book);
	}

	@Override
	public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
		// get book from repository
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new BadRequestException("book_id.invalid"));
		// update
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		// save
		bookRepository.save(book);
		
	}

	@Override
	public void deleteBook(Long bookId) {
		bookRepository.deleteById(bookId);
	}


	// public BookRepository getBookRepository() {
	// 	return bookRepository;
	// }

	// public void setBookRepository(BookRepository bookRepository) {
	// 	this.bookRepository = bookRepository;
	// }

	
}
