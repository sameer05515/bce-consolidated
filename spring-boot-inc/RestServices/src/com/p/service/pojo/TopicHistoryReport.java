package com.p.service.pojo;

import java.math.BigInteger;
import java.util.Date;

public class TopicHistoryReport {
	
	private BigInteger Count;
	private String ActionDate;
	private String Action;
	public TopicHistoryReport(BigInteger count, String actionDate, String action) {
		super();
		Count = count;
		ActionDate = actionDate;
		Action = action;
	}
	public BigInteger getCount() {
		return Count;
	}
	public void setCount(BigInteger count) {
		Count = count;
	}
	public String getActionDate() {
		return ActionDate;
	}
	public void setActionDate(String actionDate) {
		ActionDate = actionDate;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}

}
