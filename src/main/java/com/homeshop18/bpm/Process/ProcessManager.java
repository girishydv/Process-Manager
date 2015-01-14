package com.homeshop18.bpm.Process;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import com.homeshop18.bpm.DAO.DataLoader;
import com.homeshop18.bpm.Entity.DynamicExecution;
import com.homeshop18.bpm.Entity.DynamicExecutionKey;
import com.homeshop18.bpm.Entity.Process;
import com.homeshop18.bpm.Entity.ProcessInstance;
import com.homeshop18.bpm.Entity.ProcessInstanceLog;
import com.homeshop18.bpm.Entity.ProcessKey;
import com.homeshop18.bpm.Entity.Task;
import com.homeshop18.bpm.Entity.TaskInstance;
import com.homeshop18.bpm.Entity.TaskInstanceLog;
import com.homeshop18.bpm.Entity.User;
import com.homeshop18.bpm.audit.AuditManager;
import com.homeshop18.bpm.common.EnumHolder.ExecutionUnitResultType;
import com.homeshop18.bpm.common.EnumHolder.ExecutionUnitStatus;
import com.homeshop18.bpm.common.EnumHolder.ExecutionUnitType;
import com.homeshop18.bpm.common.EnumHolder.TaskType;

/**
 * @author Girish.Yadav
 * 
 */
public class ProcessManager {
	@PersistenceContext(type = PersistenceContextType.EXTENDED, name = "com.homeshop18.casepanel")
	EntityManager entityManager;

	private static boolean loaded = false;
	static {
		if (!loaded)
			// DataLoader.loadData(entityManager);
			loaded = true;
		System.out.println("Loading data");

	}

	private Task getTaskHandler(int taskId) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		Task task = (Task) entityManager.find(Task.class, taskId);
		// entityManager.getTransaction().commit();
		task.getTaskHandler();

		return task;
	}

	private void signalTaskCompletetion(String string) {
		// TODO Auto-generated method stub

	}

	public List<TaskInstance> getInstances(String email) {

		// Session session = null;
		TaskInstance inst = null;
		List taskInstanceresult = null;
		// EntityManager entityManager = null;
		try {
			// entityManager = PersistenceHelper.getEntityManager();
			Query query = (Query) entityManager
					.createQuery("Select u from User u where u.emailId= :email");
			// entityManager.getTransaction().commit();
			query.setParameter("email", email);
			java.util.List result = query.getResultList();
			int userId = ((User) result.get(0)).getUserId();
			query = (Query) entityManager
					.createQuery("Select t from TaskInstance t where t.taskOwnerId= :userId and t.taskStatus='OPEN'");
			query.setParameter("userId", userId);
			taskInstanceresult = query.getResultList();
			// inst = (TaskInstance) taskInstanceresult.get(0);
			// entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskInstanceresult;
	}

	public long startProcess(int processId) {

		long businessProcessId = 0;
		try {
			// entityManager = PersistenceHelper.getEntityManager();
			// entityManager.getTransaction().begin();
			businessProcessId = 0;
			ProcessInstance processInstance = new ProcessInstance();

			DynamicExecution dk = getDynamicExecutionList(processId, -1,
					ExecutionUnitResultType.SUCCESS,
					ExecutionUnitType.STARTNODE).get(0);
			processInstance.setProcessId(processId);
			processInstance.setLastModificationDate(new Timestamp(System
					.currentTimeMillis()));
			processInstance.setStartDate(new Timestamp(System
					.currentTimeMillis()));
			processInstance.setProcessStatus(ExecutionUnitStatus.OPEN);
			entityManager.persist(processInstance);
			// entityManager.getTransaction().commit();
			businessProcessId = processInstance.getProcessInstanceId();
			// entityManager.getTransaction().begin();
			TaskInstance taskInstance = new TaskInstance();
			taskInstance.setProcessInstanceId(processInstance
					.getProcessInstanceId());
			taskInstance.setTaskId(dk.getNextExecutionUnit());
			taskInstance.setProcessId(processId);
			// taskInstance.setTaskInstanceId(taskInstanceId);
			taskInstance.setTaskOwnerGroupId(dk.getGroupId());
			taskInstance.setTaskOwnerId(1);
			taskInstance.setTaskStatus(ExecutionUnitStatus.OPEN);
			taskInstance.setTaskType(getTaskHandler(dk.getNextExecutionUnit())
					.getTaskType());
			entityManager.persist(taskInstance);
			// entityManager.getTransaction().commit();
			processInstance.setTaskInstanceId(taskInstance.getTaskInstanceId());
			// entityManager.getTransaction().begin();
			entityManager.persist(processInstance);
			// entityManager.getTransaction().commit();

			AuditManager.getAuditor().logProcessInstance(entityManager,
					processInstance, dk.getGroupId());
			AuditManager.getAuditor().logTaskInstance(entityManager,
					taskInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// start process
		// audit process
		// call start task method
		// audit task

		return businessProcessId;
	}

	// create process and return
	// create task and return
	// start process and save
	// save task
	// audit process
	// audit log

	
	public Object completeTask(long taskInstanceId, Object taskInput,
			int ownerId) {

		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		long businessProcessId = 0;
		try {
			// entityManager.getTransaction().begin();
			TaskInstance currentTaskInstance = getTaskInstancefromId(taskInstanceId);
			// get task from
			Class<?> cls;
			Object output = null;
			// perform task
			try {
				cls = Class.forName(getTaskHandler(
						currentTaskInstance.getTaskId()).getTaskHandler());
				output = cls.getMethod("execute", new Class[] { String.class })
						.invoke(taskInput, "1");
			} catch (Exception e) {
				System.out.println("Exception while executing the task.");
				// TODO uncomment
				// return null;
			}

			// get output and pass it while instantiating new task and submit it
			// to
			// complete script task.
			currentTaskInstance.setTaskStatus(ExecutionUnitStatus.CLOSED);
			entityManager.merge(currentTaskInstance);
			// entityManager.getTransaction().commit();
			TaskInstanceLog tlog = AuditManager.getAuditor()
					.retreiveTaskInstanceLog(entityManager,
							currentTaskInstance.getTaskInstanceId());
			tlog.setTaskStatus(ExecutionUnitStatus.CLOSED);
			AuditManager.getAuditor()
					.updateTaskInstanceLog(entityManager, tlog);
			businessProcessId = 0;
			// TODO ExecutionUnitType
			DynamicExecution dk = getDynamicExecutionList(
					currentTaskInstance.getProcessId(),
					currentTaskInstance.getTaskId(),
					ExecutionUnitResultType.SUCCESS, ExecutionUnitType.TASK).get(0);
			businessProcessId = instantiateNextExecutionUnit(entityManager, dk,
					currentTaskInstance.getProcessInstanceId(), output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return businessProcessId;
	}

	private long instantiateNextExecutionUnit(EntityManager entityManager,
			DynamicExecution dk, long processInstanceId, Object output) {
		long businessProcessId = 0;
		TaskInstance newTaskInstance = new TaskInstance();
		ProcessInstance processInstance;
		ProcessInstanceLog plog;
		TaskInstanceLog tlog;
		switch (dk.getNextExecutionUnitType()) {

		case PROCESS:
			ProcessKey pk= new ProcessKey();
			pk.setProcessId(dk.getNextExecutionUnit());
			pk.setProcessVersion(dk.getExecutionUnitVersion());
			Process nestedProcess = getProcessfromId(pk);
			// entityManager.getTransaction().begin();
			processInstance = new ProcessInstance();
			processInstance.setProcessId(nestedProcess.getProcessKey().getProcessId());
			processInstance.setParentProcessInstanceId(processInstanceId);
			processInstance.setLastModificationDate(new Timestamp(System
					.currentTimeMillis()));
			processInstance.setStartDate(new Timestamp(System
					.currentTimeMillis()));
			processInstance.setProcessStatus(ExecutionUnitStatus.OPEN);
			entityManager.persist(processInstance);
			// entityManager.getTransaction().commit();
			businessProcessId = processInstance.getProcessInstanceId();
			// processInstance = getProcessInstance();
			DynamicExecution dkForSubProcess;
			if (processInstance.getParentProcessInstanceId() != 0) {
				dkForSubProcess = getDynamicExecutionList(
						nestedProcess.getProcessKey().getProcessId(), -1,
						ExecutionUnitResultType.SUCCESS,
						ExecutionUnitType.STARTNODE).get(0);
			} else {
				dkForSubProcess = dk;
			}
			// entityManager.getTransaction().begin();
			newTaskInstance.setProcessInstanceId(processInstance
					.getProcessInstanceId());
			newTaskInstance.setTaskId(dkForSubProcess.getNextExecutionUnit());
			newTaskInstance.setTaskType(getTaskHandler(
					dkForSubProcess.getNextExecutionUnit()).getTaskType());
			newTaskInstance.setProcessId(processInstance.getProcessId());
			// taskInstance.setTaskInstanceId(taskInstanceId);
			newTaskInstance.setTaskOwnerGroupId(dkForSubProcess.getGroupId());
			newTaskInstance.setTaskOwnerId(TaskDelegator.getDelegator()
					.fetchUserIdfromGroup(dkForSubProcess.getGroupId()));
			newTaskInstance.setTaskStatus(ExecutionUnitStatus.OPEN);
			entityManager.persist(newTaskInstance);
			// entityManager.getTransaction().commit();
			processInstance.setTaskInstanceId(newTaskInstance
					.getTaskInstanceId());
			entityManager.merge(processInstance);
			// entityManager.getTransaction().commit();

			AuditManager.getAuditor().logProcessInstance(entityManager,
					processInstance, dk.getGroupId());
			AuditManager.getAuditor().logTaskInstance(entityManager,
					newTaskInstance);
			if (TaskType.SCRIPT.equals(newTaskInstance.getTaskType())) {
				completeTask(newTaskInstance.getTaskInstanceId(), null,
						newTaskInstance.getTaskOwnerId());
			}

			break;

		case TASK:
			processInstance = getProcessInstancefromId(processInstanceId);

			// processInstance = getProcessInstance();
			// entityManager.getTransaction().begin();
			TaskInstance oldTaskInstance = getTaskInstancefromId(processInstance
					.getTaskInstanceId());
			newTaskInstance.setProcessInstanceId(processInstanceId);
			newTaskInstance.setTaskId(dk.getNextExecutionUnit());
			newTaskInstance.setProcessId(processInstance.getProcessId());
			// taskInstance.setTaskInstanceId(taskInstanceId);
			newTaskInstance.setTaskOwnerGroupId(dk.getGroupId());
			newTaskInstance.setTaskOwnerId(TaskDelegator.getDelegator()
					.fetchUserIdfromGroup(dk.getGroupId()));
			newTaskInstance.setTaskStatus(ExecutionUnitStatus.OPEN);
			newTaskInstance.setTaskType(getTaskHandler(
					dk.getNextExecutionUnit()).getTaskType());
			entityManager.persist(newTaskInstance);
			// entityManager.getTransaction().commit();
			// processInstance.setTaskInstanceId(newTaskInstanceId);
			// entityManager.getTransaction().begin();
			processInstance.setTaskInstanceId(newTaskInstance
					.getTaskInstanceId());
			entityManager.merge(processInstance);
			// entityManager.getTransaction().commit();
			plog = AuditManager.getAuditor().retreiveProcessInstanceLog(
					entityManager, processInstance.getProcessInstanceId());
			plog.setTaskInstanceId(newTaskInstance.getTaskInstanceId());
			AuditManager.getAuditor().updateProcessInstancelog(entityManager,
					plog);
			tlog = AuditManager.getAuditor().retreiveTaskInstanceLog(
					entityManager, oldTaskInstance.getTaskInstanceId());
			tlog.setTaskStatus(ExecutionUnitStatus.CLOSED);
			AuditManager.getAuditor()
					.updateTaskInstanceLog(entityManager, tlog);
			AuditManager.getAuditor().logTaskInstance(entityManager,
					newTaskInstance);
			// if next task is script task, then complete it automatically
			if (TaskType.SCRIPT.equals(newTaskInstance.getTaskType())) {
				completeTask(newTaskInstance.getTaskInstanceId(), null,
						newTaskInstance.getTaskOwnerId());
			}
			break;

		case RULE:
			break;

		case ENDNODE:
			processInstance = getProcessInstancefromId(processInstanceId);
			// entityManager.getTransaction().begin();
			// TODO set process result
			processInstance.setProcessStatus(ExecutionUnitStatus.CLOSED);
			entityManager.merge(processInstance);
			// entityManager.getTransaction().commit();
			tlog = AuditManager.getAuditor().retreiveTaskInstanceLog(
					entityManager, processInstance.getTaskInstanceId());
			tlog.setTaskStatus(ExecutionUnitStatus.CLOSED);
			AuditManager.getAuditor()
					.updateTaskInstanceLog(entityManager, tlog);
			plog = AuditManager.getAuditor().retreiveProcessInstanceLog(
					entityManager, processInstance.getProcessInstanceId());
			plog.setStatus(ExecutionUnitStatus.CLOSED);
			plog.setEndDate(new Timestamp(System.currentTimeMillis()));
			AuditManager.getAuditor().updateProcessInstancelog(entityManager,
					plog);

			if (processInstance.getParentProcessInstanceId() != 0) {
				// TODO check if there are other children

				dk = getDynamicExecutionList(
						getProcessInstancefromId(
								processInstance.getParentProcessInstanceId())
								.getProcessId(),
						processInstance.getProcessId(),
						ExecutionUnitResultType.SUCCESS,
						ExecutionUnitType.PROCESS).get(0);
				instantiateNextExecutionUnit(entityManager, dk,
						processInstance.getParentProcessInstanceId(), null);
				// find next execution unit
				// start unit
				// log
			}

			break;

		case STARTNODE:
			break;
		case TERMINATE:
			// TODO set process result as ABORT
			break;
		default:
			break;
		}
		return businessProcessId;
	}

	private ProcessInstance getProcessInstancefromId(long processInstanceId) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		ProcessInstance processInstance = null;
		try {
			processInstance = (ProcessInstance) entityManager.find(
					ProcessInstance.class, processInstanceId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return processInstance;
	}

	private TaskInstance getTaskInstancefromId(long taskInstanceId) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		TaskInstance taskInstance = null;
		try {
			taskInstance = (TaskInstance) entityManager.find(
					TaskInstance.class, taskInstanceId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskInstance;
	}

	private DynamicExecution getDynamicExecution(int processId,
			int currentExecutionUnitId, ExecutionUnitResultType currentResult,
			ExecutionUnitType currentExecutionUnitType) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		DynamicExecution dProcess = null;
		try {
			DynamicExecutionKey dk = new DynamicExecutionKey();
			dk.setProcessId(processId);
			dk.setCurrentExecutionUnit(currentExecutionUnitId);
			dk.setCurrentResult(currentResult);
			dk.setCurrentExecutionUnitType(currentExecutionUnitType);

			dProcess = (DynamicExecution) entityManager.find(
					DynamicExecution.class, dk);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dProcess;
	}

	private List<DynamicExecution> getDynamicExecutionList(int processId,
			int currentExecutionUnitId, ExecutionUnitResultType currentResult,
			ExecutionUnitType currentExecutionUnitType) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		List dProcess = null;
		try {
			// entityManager = PersistenceHelper.getEntityManager();
			Query query = (Query) entityManager
					.createQuery("Select dk from DynamicExecution dk where dk.processId= :processId and dk.currentExecutionUnit= :currentExecutionUnitId"
							+ " and dk.currentResult= :currentResult "
							+ "and dk.currentExecutionUnitType= :currentExecutionUnitType");
			// entityManager.getTransaction().commit();
			query.setParameter("processId", processId);
			query.setParameter("currentExecutionUnitId", currentExecutionUnitId);
			query.setParameter("currentResult", currentResult);
			query.setParameter("currentExecutionUnitType", currentExecutionUnitType);
			dProcess = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dProcess;
	}

	private Process getProcessfromId(ProcessKey processKey) {
		// EntityManager entityManager = PersistenceHelper.getEntityManager();
		Process process = null;
		try {
			process = (Process) entityManager.find(Process.class, processKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return process;
	}

	public void loadData() {
		DataLoader.loadData(entityManager);

	}

}
