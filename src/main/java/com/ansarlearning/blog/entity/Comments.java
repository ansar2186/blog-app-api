package com.ansarlearning.blog.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_comments")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cmntId;

	private String content;
	
	
	//@JoinColumn(name = "post_id").
	@ManyToOne
	private Post post;

	public Comments() {
	}

	public Comments(Integer cmntId, String content, Post post) {
		super();
		this.cmntId = cmntId;
		this.content = content;
		this.post = post;
	}

	public Integer getCmntId() {
		return cmntId;
	}

	public void setCmntId(Integer cmntId) {
		this.cmntId = cmntId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
