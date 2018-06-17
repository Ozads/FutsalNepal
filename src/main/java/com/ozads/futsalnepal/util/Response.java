package com.ozads.futsalnepal.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(Include.NON_NULL)
public class Response {
	
	@JsonProperty("message")
	private String message;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("code")
	private Integer code;

	@JsonProperty("creationDate")
	private String creationDate;

	@JsonProperty("modificationDate")
	private String modificationDate;

	public Response(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}

	public Response(String message) {
		super();
		this.message = message;
	}

	public Response(Long id, String modificationDate) {
		super();
		this.id = id;
		this.modificationDate = modificationDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Response(String message, Long id) {
		super();
		this.message = message;
		this.id = id;
	}
	
	public Response(Long id) {
		super();
		this.id = id;
	}
	


}

