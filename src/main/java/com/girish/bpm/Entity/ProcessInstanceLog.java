package com.girish.bpm.Entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.girish.bpm.common.EnumHolder.ExecutionUnitStatus;

/**
 * @author Girish.Yadav
 *
 */
@Entity
public class ProcessInstanceLog {
	@Id
	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 */
	private long processInstanceId;
	private long parentProcessInstanceId;
	private int processId;
	private int sequenceId;
	private long taskInstanceId;
	private Timestamp startDate;
	private Timestamp endDate;
	private int errorCode;
	private String processName;
	private String processVersion="1.0";
	@Enumerated(EnumType.STRING)
	private ExecutionUnitStatus status;
	private int userId;
	

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public long getParentProcessInstanceId() {
		return parentProcessInstanceId;
	}

	public void setParentProcessInstanceId(long parentProcessInstanceId) {
		this.parentProcessInstanceId = parentProcessInstanceId;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public long getTaskInstanceId() {
		return taskInstanceId;
	}

	public void setTaskInstanceId(long taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessVersion() {
		return processVersion;
	}

	public void setProcessVersion(String processVersion) {
		this.processVersion = processVersion;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ExecutionUnitStatus getStatus() {
		return status;
	}

	public void setStatus(ExecutionUnitStatus status) {
		this.status = status;
	}

}
