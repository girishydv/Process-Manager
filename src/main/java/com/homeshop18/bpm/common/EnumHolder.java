package com.homeshop18.bpm.common;

/**
 * @author Girish.Yadav
 *
 */
public interface EnumHolder {
	public static enum ExecutionUnitResultType {
		SUCCESS, FAILURE, ABORT
	};

	public static enum ExecutionUnitType {
		STARTNODE, TASK, RULE, PROCESS, ENDNODE, TERMINATE
	};

	public static enum ExecutionUnitStatus {
		OPEN, CLOSED
	};

	public static enum TaskType {
		USER, SCRIPT
	};
}
