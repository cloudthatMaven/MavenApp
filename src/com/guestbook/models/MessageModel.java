package com.guestbook.models;

import java.io.File;

public class MessageModel {

	private String sender;
	private String message;
	private File imagefile;
	private String imagefileContentType;
	private String imagefileFileName;
	
	private String objecturl;
	
	
	
	public String getObjecturl() {
		return objecturl;
	}
	public void setObjecturl(String objecturl) {
		this.objecturl = objecturl;
	}
	
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getImagefile() {
		return imagefile;
	}

	public void setImagefile(File imagefile) {
		this.imagefile = imagefile;
	}

	public String getImagefileContentType() {
		return imagefileContentType;
	}

	public void setImagefileContentType(String imagefileContentType) {
		this.imagefileContentType = imagefileContentType;
	}

	public String getImagefileFileName() {
		return imagefileFileName;
	}

	public void setImagefileFileName(String imagefileFileName) {
		this.imagefileFileName = imagefileFileName;
	}
	
}
