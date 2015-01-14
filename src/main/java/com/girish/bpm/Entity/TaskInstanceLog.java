package com.girish.bpm.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.girish.bpm.common.EnumHolder.ExecutionUnitStatus;
import com.girish.bpm.common.EnumHolder.TaskType;

/**
 * @author Girish.Yadav
 *
 */
@Entity
public class TaskInstanceLog {
	private long taskId;
	private long processInstanceId;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitStatus taskStatus;
	@Id
	private long taskInstanceId;
	@Enumerated(EnumType.STRING)
	private TaskType taskType;
	private String taskName;
	

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}


	public long getTaskInstanceId() {
		return taskInstanceId;
	}

	public void setTaskInstanceId(long taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
