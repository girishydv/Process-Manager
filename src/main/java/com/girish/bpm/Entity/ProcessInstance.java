package com.girish.bpm.Entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.girish.bpm.common.EnumHolder.ExecutionUnitResultType;
import com.girish.bpm.common.EnumHolder.ExecutionUnitStatus;

/**
 * @author Girish.Yadav
 *
 */
@Entity
public class ProcessInstance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long processInstanceId;
	private long parentProcessInstanceId;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitStatus processStatus;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitResultType processResult;
	private long businessId;
	private int sequenceId;
	private long taskInstanceId;
	private Timestamp startDate;
	private Timestamp lastModificationDate;
	private int processId;
	private String processVersion="1.0";
	public String getProcessVersion() {
		return processVersion;
	}

	public void setProcessVersion(String processVersion) {
		this.processVersion = processVersion;
	}

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

	public Timestamp getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Timestamp lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public ExecutionUnitStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(ExecutionUnitStatus processStatus) {
		this.processStatus = processStatus;
	}

	public ExecutionUnitResultType getProcessResult() {
		return processResult;
	}

	public void setProcessResult(ExecutionUnitResultType processResult) {
		this.processResult = processResult;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

}
