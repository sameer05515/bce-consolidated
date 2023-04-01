package com.example.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "topic_read_history"/*, catalog = "zettacoaching"*/)
public class TopicHistory {
	
	private int id;
	private int topicId;
	private Date dateLastRead;
	private String action;
	
	public TopicHistory(int id, int topicId, Date dateLastReed,String action) {
		super();
		this.id = id;
		this.topicId = topicId;
		this.dateLastRead = dateLastReed;
		this.setAction(action);
	}
	
	public TopicHistory() {
		super();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "topic_id", nullable = true)
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_read_date", nullable = false, length = 19)
	public Date getDateLastRead() {
		return dateLastRead;
	}

	public void setDateLastRead(Date dateLastRead) {
		this.dateLastRead = dateLastRead;
	}

	@Column(name = "action", nullable = true)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	

}
