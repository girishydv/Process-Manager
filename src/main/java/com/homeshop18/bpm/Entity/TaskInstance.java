package com.homeshop18.bpm.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.homeshop18.bpm.common.EnumHolder.ExecutionUnitStatus;
import com.homeshop18.bpm.common.EnumHolder.TaskType;

/**
 * @author Girish.Yadav
 *
 */
@Entity
public class TaskInstance {

	private int taskId;
	private long processInstanceId;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitStatus taskStatus;
	private int taskOwnerGroupId;
	private int taskOwnerId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskInstanceId;
	private int processId;
	@Enumerated(EnumType.STRING)
	private TaskType taskType;
	private String processVersion="1.0";
	public String getProcessVersion() {
		return processVersion;
	}

	public void setProcessVersion(String processVersion) {
		this.processVersion = processVersion;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public int getTaskOwnerGroupId() {
		return taskOwnerGroupId;
	}

	public int getTaskOwnerId() {
		return taskOwnerId;
	}

	public void setTaskOwnerGroupId(int taskOwnerGroupId) {
		this.taskOwnerGroupId = taskOwnerGroupId;
	}

	public void setTaskOwnerId(int taskOwnerId) {
		this.taskOwnerId = taskOwnerId;
	}

	public long getTaskInstanceId() {
		return taskInstanceId;
	}

	public void setTaskInstanceId(long taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public ExecutionUnitStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(ExecutionUnitStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

}
