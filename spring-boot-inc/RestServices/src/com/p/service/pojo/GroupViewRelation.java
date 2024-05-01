
package com.p.service.pojo;

// Generated Jul 16, 2015 5:04:23 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Employeelobs generated by hbm2java
 */
@Entity
@Table(name = "groupviews"/*, catalog = "zettaprofile"*/, uniqueConstraints = @UniqueConstraint(columnNames = {
		"viewid", "groupid" }))
public class GroupViewRelation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5429095023265480024L;
	private Integer id;
	private View views;
	private Group groups;

	public GroupViewRelation() {
	}

	public GroupViewRelation(Group groups,View views) {
		this.views = views;
		this.groups = groups;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "viewid", nullable = false)
	public View getViews() {
		return this.views;
	}

	public void setViews(View views) {
		this.views = views;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupid", nullable = false)
	public Group getGroups() {
		return this.groups;
	}

	public void setGroups(Group groups) {
		this.groups = groups;
	}

}
