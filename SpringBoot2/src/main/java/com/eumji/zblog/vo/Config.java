package com.eumji.zblog.vo;

import java.io.Serializable;
import java.util.Date;

public class Config implements Serializable{
	private long id;
	private String name;
	private String version;
	private String content;
	private String list;
	private Date starTime;
	private Date updateTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public Date getStarTime() {
		return starTime;
	}
	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Config(long id, String name, String version, String content,
			String list, Date starTime, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
		this.content = content;
		this.list = list;
		this.starTime = starTime;
		this.updateTime = updateTime;
	}
	public Config() {
		// TODO 自动生成的构造函数存根
	}
	

}
