package com.govipul.springboot.rest.startup.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	@GeneratedValue
	private long id;

	private String userName;

	@JsonIgnore
	private String userPassword;

	@OneToMany(mappedBy = "account")
	private Set<Bookmark> bookmarks = new HashSet<>();

	private Account() {
		// JPA only
	}

	public Account(final String userName, final String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public Set<Bookmark> getBookmarks() {
		return bookmarks;
	}

}
