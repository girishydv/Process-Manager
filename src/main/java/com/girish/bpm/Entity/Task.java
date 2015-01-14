package com.girish.bpm.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.girish.bpm.common.EnumHolder.TaskType;

/**
 * @author Girish.Yadav
 *
 */
@Entity
public class Task {
	@Id
	private int taskId;
	private String taskDataInputType;
	private String taskDataOutputType;
	private String taskDescription;
	private String taskHandler;
	@Enumerated(EnumType.STRING)
	private TaskType taskType;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskDataInputType() {
		return taskDataInputType;
	}

	public void setTaskDataInputType(String taskDataInputType) {
		this.taskDataInputType = taskDataInputType;
	}

	public String getTaskDataOutputType() {
		return taskDataOutputType;
	}

	public void setTaskDataOutputType(String taskDataOutputType) {
		this.taskDataOutputType = taskDataOutputType;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskHandler() {
		return taskHandler;
	}

	public void setTaskHandler(String taskHandler) {
		this.taskHandler = taskHandler;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

}
