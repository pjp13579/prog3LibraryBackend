package com.example.demo.domain.Entities;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="magazine")
public class Magazine extends Publication{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer issn;
	protected Integer volume;
	protected Integer issue;
	//Integer is the ad id key(primary key), String is the ad content or link or whatever, that's the idea
	//protected HashMap<Integer, String> ads = new HashMap<Integer, String>();
	
	public Magazine() {
		super();
	}
	
	public Magazine(Integer nPages, String title, PublicationCategoryEnums category,  Date publicationDate, String author, Integer issn, Integer volume, Integer issue) {
		super(nPages, title, category, publicationDate, author);
		this.issn = issn;
		this.volume = volume;
		this.issue = issue;
	}

	public Integer getIssn() {
		return issn;
	}
	public void setIssn(Integer issn) {
		this.issn = issn;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public Integer getIssue() {
		return issue;
	}
	public void setIssue(Integer issue) {
		this.issue = issue;
	}
	/*
	public void addAd(String description) {
		ads.put(ads.size(), description);
	}
	public void removeAd(Integer adKey) {
		if(ads.containsKey(adKey)) {
			ads.remove(adKey);
		}
	}
	*/
}


	















