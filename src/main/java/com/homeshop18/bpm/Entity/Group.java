package com.homeshop18.bpm.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Girish.Yadav
 *
 */
@Entity
@Table(name = "USERGROUP")
public class Group {
	@Id
	private int groupId;

	private String name;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
