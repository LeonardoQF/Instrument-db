package com.uajj.instrumentDB.service.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardHttpError implements Serializable {

	private static final long serialVersionUID = 809725859387881575L;

	private Instant timestamp;
	private int status;
	private String message;
	private String path;

	public StandardHttpError(Instant timestamp, int status, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
