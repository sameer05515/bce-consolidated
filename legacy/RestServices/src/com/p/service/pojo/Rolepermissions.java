package com.p.service.pojo;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Rolepermissions generated by hbm2java
 */
@Entity
@Table(name = "rolepermissions")
public class Rolepermissions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8083553347328843649L;
	private RolepermissionsId id;

	public Rolepermissions() {
	}

	public Rolepermissions(RolepermissionsId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "topicid", column = @Column(name = "AccountRoleID", nullable = false)),
			@AttributeOverride(name = "groupid", column = @Column(name = "PermissionID", nullable = false)) })
	public RolepermissionsId getId() {
		return this.id;
	}

	public void setId(RolepermissionsId id) {
		this.id = id;
	}

}