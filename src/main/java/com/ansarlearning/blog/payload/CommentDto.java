package com.ansarlearning.blog.payload;

public class CommentDto {

	private Integer cmntId;

	private String content;

	public CommentDto() {
	}

	public CommentDto(Integer cmntId, String content) {
		super();
		this.cmntId = cmntId;
		this.content = content;
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

}
