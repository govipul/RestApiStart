package com.govipul.springboot.rest.startup.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.govipul.springboot.rest.startup.exception.BookmarkNotFoundException;
import com.govipul.springboot.rest.startup.exception.UserNotFoundException;
import com.govipul.springboot.rest.startup.model.Bookmark;
import com.govipul.springboot.rest.startup.repository.AccountRepository;
import com.govipul.springboot.rest.startup.repository.BookmarkRepository;

@RestController
@RequestMapping("/bookmarks/{userId}")
public class BookmarkController {

	private final BookmarkRepository bookmarkRepository;
	private final AccountRepository accountRepository;

	@Autowired
	public BookmarkController(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.accountRepository = accountRepository;
	}

	@GetMapping
	public Collection<Bookmark> readBookmark(@PathVariable String userId) {
		this.validateUser(userId);
		return this.bookmarkRepository.findByAccountUserName(userId);
	}

	@PutMapping
	public ResponseEntity<?> add(@PathVariable String userId, @PathVariable Bookmark bookmark) {

		this.validateUser(userId);

		return this.accountRepository.findByUserName(userId).map(account -> {
			Bookmark results = this.bookmarkRepository.save(Bookmark.from(account, bookmark));

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(results.getId()).toUri();

			return ResponseEntity.created(location).build();
		}).orElse(ResponseEntity.noContent().build());

	}

	@GetMapping("/{bookmarkId}")
	public Bookmark readBookmark(String userId, Long bookmarkId) {
		this.validateUser(userId);
		return this.bookmarkRepository.findById(bookmarkId)
				.orElseThrow(() -> new BookmarkNotFoundException(bookmarkId));
	}

	private void validateUser(String userId) {
		this.accountRepository.findByUserName(userId).orElseThrow(() -> new UserNotFoundException(userId));

	}

}
