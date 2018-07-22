package com.govipul.springboot.rest.startup.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govipul.springboot.rest.startup.model.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	Collection<Bookmark> findByAccountUserName(String userName);
}
