package com.m3.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.m3.dao.UserDao;

@Entity
public class Post {

	@Autowired
	private static UserDao ud;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String content;
	@Column
	private String photo;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User author;
	@Column(nullable = false)
	private LocalDateTime dateCreated;

	public Post(String content, String photo, User author, LocalDateTime dateCreated) {
		super();
		this.content = content;
		this.photo = photo;
		this.author = author;
		this.dateCreated = dateCreated;
	}

	public Post(int id, String content, String photo, User author, LocalDateTime dateCreated) {
		super();
		this.id = id;
		this.content = content;
		this.photo = photo;
		this.author = author;
		this.dateCreated = dateCreated;
	}

	public Post(int id, String content, String photo, User author) {
        super();
        this.id = id;
        this.content = content;
        this.photo = photo;
        this.author = author;
        this.dateCreated = LocalDateTime.now();
    }
	
	public Post(PostBuilt pb) {
		super();
		this.id = pb.getId();
		try {
			this.content = pb.getContent();
		} catch (Exception e) {
			this.content = "";
		}
		try {
			this.photo = pb.getPhoto();
		} catch (Exception e) {
			this.photo = "";
		}
		try {
			this.author = ud.findById(pb.getAuthorId());
		} catch (Exception e) {
			this.author = null;
		}
		try {
			this.dateCreated = pb.getDateCreated();
		} catch (Exception e) {
			this.dateCreated = LocalDateTime.now();
		}
	}
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		try {
			this.content = content;
		} catch (Exception e) {
			
		}
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		try {
			this.photo = photo;
		} catch (Exception e) {
			
		}
		
	}

//	Changed from author= author-object to authorId = author.getId()
	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", authorId=" + author.getId() + ", dateCreated="
				+ dateCreated + "]";
	}

}
