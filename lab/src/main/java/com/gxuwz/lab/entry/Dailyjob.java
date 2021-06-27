package com.gxuwz.lab.entry;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/

public class Dailyjob implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer weekly;
	private String time;
	private String userId;
	private String userName;
	private String content;
	private String systemTime;

	// Constructors

	/** default constructor */
	public Dailyjob() {
	}



	// Property accessors

	public Dailyjob(Integer id, Integer weekly, String time, String userId, String userName, String content, String systemTime) {
		this.id = id;
		this.weekly = weekly;
		this.time = time;
		this.userId = userId;
		this.userName = userName;
		this.content = content;
		this.systemTime = systemTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeekly() {
		return weekly;
	}

	public void setWeekly(Integer weekly) {
		this.weekly = weekly;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}