package com.girish.bpm.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.girish.bpm.common.EnumHolder.ExecutionUnitResultType;
import com.girish.bpm.common.EnumHolder.ExecutionUnitType;

/**
 * @author Girish.Yadav
 *
 */
@Entity
@Table(name = "DYNAMIC_PROCESSES_TASK_MAPPING")
public class DynamicExecution {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dynamicInstanceId;
	private int groupId;
	private int nextExecutionUnit;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitType nextExecutionUnitType;
	private int processId;
	private int currentExecutionUnit;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitType currentExecutionUnitType;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitResultType currentResult;
	private String executionUnitVersion="1.0";

	public ExecutionUnitResultType getCurrentResult() {
		return currentResult;
	}

	public void setCurrentResult(ExecutionUnitResultType currentResult) {
		this.currentResult = currentResult;
	}

	public int getCurrentExecutionUnit() {
		return currentExecutionUnit;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public void setCurrentExecutionUnit(int currentExecutionUnit) {
		this.currentExecutionUnit = currentExecutionUnit;
	}

	public ExecutionUnitType getCurrentExecutionUnitType() {
		return currentExecutionUnitType;
	}

	public void setCurrentExecutionUnitType(
			ExecutionUnitType currentExecutionUnitType) {
		this.currentExecutionUnitType = currentExecutionUnitType;
	}

	public int getNextExecutionUnit() {
		return nextExecutionUnit;
	}

	public void setNextExecutionUnit(int nextExecutionUnit) {
		this.nextExecutionUnit = nextExecutionUnit;
	}

	public ExecutionUnitType getNextExecutionUnitType() {
		return nextExecutionUnitType;
	}

	public void setNextExecutionUnitType(ExecutionUnitType nextExecutionUnitType) {
		this.nextExecutionUnitType = nextExecutionUnitType;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public long getDynamicInstanceId() {
		return dynamicInstanceId;
	}

	public void setDynamicInstanceId(long dynamicInstanceId) {
		this.dynamicInstanceId = dynamicInstanceId;
	}

	public String getExecutionUnitVersion() {
		return executionUnitVersion;
	}

	public void setExecutionUnitVersion(String executionUnitVersion) {
		this.executionUnitVersion = executionUnitVersion;
	}

}
