package com.lilin.cms.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: Settings 
 * @Description: 系统配置表
 * @author: asus
 * @date: 2020年3月3日 下午3:48:31
 */
public class Settings implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String siteDomain;
	private String siteName;
	private String article_list_size;//文章尾页显示条数
	private Integer slide_size;//显示多个广告
	private String adminUsername;
	private String adminPassword;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getArticle_list_size() {
		return article_list_size;
	}
	public void setArticle_list_size(String article_list_size) {
		this.article_list_size = article_list_size;
	}
	public Integer getSlide_size() {
		return slide_size;
	}
	public void setSlide_size(Integer slide_size) {
		this.slide_size = slide_size;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	
}
