package com.homeshop18.bpm.audit;

import javax.persistence.EntityManager;

import com.homeshop18.bpm.Entity.ProcessInstance;
import com.homeshop18.bpm.Entity.ProcessInstanceLog;
import com.homeshop18.bpm.Entity.TaskInstance;
import com.homeshop18.bpm.Entity.TaskInstanceLog;
import com.homeshop18.bpm.common.EnumHolder.ExecutionUnitStatus;

/**
 * @author Girish.Yadav
 *
 */
public class AuditManager {

	public void logProcessInstance(EntityManager entityManager,
			ProcessInstance processInstance, int userId) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		try {
			ProcessInstanceLog plog = new ProcessInstanceLog();
			plog.setProcessId(processInstance.getProcessId());
			plog.setProcessInstanceId(processInstance.getProcessInstanceId());
			plog.setUserId(userId);
			plog.setParentProcessInstanceId(processInstance.getParentProcessInstanceId());
			plog.setStatus(processInstance.getProcessStatus());
			plog.setStartDate(processInstance.getStartDate());
			// entityManager.getTransaction().begin();
			entityManager.persist(plog);
			// entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};

	public void logTaskInstance(EntityManager entityManager,
			TaskInstance taskInstance) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		try {
			TaskInstanceLog tlog = new TaskInstanceLog();
			tlog.setProcessInstanceId(taskInstance.getProcessInstanceId());
			tlog.setTaskId(taskInstance.getTaskId());
			tlog.setTaskInstanceId(taskInstance.getTaskInstanceId());
			tlog.setTaskStatus(ExecutionUnitStatus.OPEN);
			tlog.setTaskType(taskInstance.getTaskType());
			// entityManager.getTransaction().begin();
			entityManager.persist(tlog);
			// entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};

	public void updateProcessInstancelog(EntityManager entityManager,
			ProcessInstanceLog processInstanceLog) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		try {
			// entityManager.getTransaction().begin();
			entityManager.merge(processInstanceLog);
			// entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};

	public void updateTaskInstanceLog(EntityManager entityManager,
			TaskInstanceLog taskInstanceLog) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		try {
			// entityManager.getTransaction().begin();
			entityManager.merge(taskInstanceLog);
			// entityManager.getTransaction().commit();
			// entityManager.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public ProcessInstanceLog retreiveProcessInstanceLog(
			EntityManager entityManager, long processInstanceId) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		ProcessInstanceLog processInstanceLog = null;
		try {
			processInstanceLog = (ProcessInstanceLog) entityManager.find(
					ProcessInstanceLog.class, processInstanceId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return processInstanceLog;
	};

	public TaskInstanceLog retreiveTaskInstanceLog(EntityManager entityManager,
			long taskInstanceId) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		TaskInstanceLog taskInstanceLog = null;
		try {
			taskInstanceLog = (TaskInstanceLog) entityManager.find(
					TaskInstanceLog.class, taskInstanceId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return taskInstanceLog;
	};

	private static final class auditorHolder {
		public static final AuditManager auditor = new AuditManager();
	}

	private AuditManager() {

	}

	public static AuditManager getAuditor() {
		return auditorHolder.auditor;
	}
}
