package com.p.service.pojo;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RolepermissionsId generated by hbm2java
 */
@Embeddable
public class RolepermissionsId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5095904578316590265L;
	private int accountRoleId;
	private int permissionId;

	public RolepermissionsId() {
	}

	public RolepermissionsId(int accountRoleId, int permissionId) {
		this.accountRoleId = accountRoleId;
		this.permissionId = permissionId;
	}

	@Column(name = "AccountRoleID", nullable = false)
	public int getAccountRoleId() {
		return this.accountRoleId;
	}

	public void setAccountRoleId(int accountRoleId) {
		this.accountRoleId = accountRoleId;
	}

	@Column(name = "PermissionID", nullable = false)
	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolepermissionsId))
			return false;
		RolepermissionsId castOther = (RolepermissionsId) other;

		return (this.getAccountRoleId() == castOther.getAccountRoleId())
				&& (this.getPermissionId() == castOther.getPermissionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAccountRoleId();
		result = 37 * result + this.getPermissionId();
		return result;
	}

}