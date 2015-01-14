package com.girish.bpm.Entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.girish.bpm.common.EnumHolder.ExecutionUnitResultType;
import com.girish.bpm.common.EnumHolder.ExecutionUnitType;

/**
 * @author Girish.Yadav
 *
 */

public class DynamicExecutionKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 335036756678454399L;
	private int processId;
	private int currentExecutionUnit;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitType currentExecutionUnitType;
	@Enumerated(EnumType.STRING)
	private ExecutionUnitResultType currentResult;

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
}
