package com.homeshop18.bpm.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.homeshop18.bpm.Entity.DynamicExecutionKey;
import com.homeshop18.bpm.Entity.Group;
import com.homeshop18.bpm.Entity.ProcessKey;
import com.homeshop18.bpm.Entity.Task;
import com.homeshop18.bpm.Entity.User;
import com.homeshop18.bpm.Process.PersistenceHelper;
import com.homeshop18.bpm.common.EnumHolder.ExecutionUnitResultType;
import com.homeshop18.bpm.common.EnumHolder.ExecutionUnitType;
import com.homeshop18.bpm.common.EnumHolder.TaskType;

/**
 * @author Girish.Yadav
 *
 */
public class DataLoader {
	/*
	 * @PersistenceContext(unitName="com.homeshop18.casepanel") static
	 * EntityManager entityManager;
	 */

	public static void loadData(EntityManager entityManager) {
		// Session session = null;
		// SessionFactory sessionFactory = SessionFactoryBuilder.getSession();
		// session = entityManager.getTunwrap(org.hibernate.Session.class);
		// session = sessionFactory.openSession();
		// session.beginTransaction();
		// EntityManager entityManager=PersistenceHelper.getEntityManager();
		Group g1 = new Group();
		g1.setGroupId(1);
		g1.setName("TROUBLESHOOTDESK");
		Group g2 = new Group();
		g2.setGroupId(2);
		g2.setName("ESCALATIONDESK");
		Group g3 = new Group();
		g3.setGroupId(3);
		g3.setName("COURIERDESK");
		Group g4 = new Group();
		g4.setGroupId(4);
		g4.setName("RESOLUTIONDESK");
		// set user 1
		User user1 = new User();
		user1.setUserId(1);
		user1.setEmailId("girish.yadav@homeshop18.com");
		user1.setPassword("1234");
		user1.setUserRole("POSTSALE");
		user1.setUserGroup("1");
		user1.setUserStatus("active");
		// set user 2
		User user2 = new User();
		user2.setUserId(2);
		user2.setEmailId("anuj.paul@homeshop18.com");
		user2.setPassword("1234");
		user2.setUserRole("POSTSALE");
		user2.setUserGroup("2");
		user2.setUserStatus("active");
		// set user 3
		User user3 = new User();
		user3.setUserId(3);
		user3.setEmailId("amit.chandra@homeshop18.com");
		user3.setPassword("1234");
		user3.setUserRole("POSTSALE");
		user3.setUserGroup("3");
		user3.setUserStatus("active");
		// set user 4
		User user4 = new User();
		user4.setUserId(4);
		user4.setEmailId("harvijay.gagneja@homeshop18.com");
		user4.setPassword("1234");
		user4.setUserRole("POSTSALE");
		user4.setUserGroup("4");
		user4.setUserStatus("active");
		// set user 4
		User user5 = new User();
		user5.setUserId(5);
		user5.setEmailId("samarth.srivastava@homeshop18.com");
		user5.setPassword("1234");
		user5.setUserRole("POSTSALE");
		user5.setUserGroup("3");
		user5.setUserStatus("active");
		// Dynamic process
		com.homeshop18.bpm.Entity.Process masterCaseProcess = new com.homeshop18.bpm.Entity.Process();
		masterCaseProcess.setProcessName("masterCaseProcess");
		ProcessKey pk1 = new ProcessKey();
		pk1.setProcessId(1);
		pk1.setProcessVersion("1.0");
		masterCaseProcess.setProcessKey(pk1);
		// masterCaseProcess.setProcessType(ProcessType.DYNAMIC);
		masterCaseProcess.setProcessDescription("started with every case");
		// Dynamic process mapping
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping1 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping2 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping3 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping11 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping12 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping13 = new com.homeshop18.bpm.Entity.DynamicExecution();

		//DynamicExecutionKey dk = new DynamicExecutionKey();
		dynamicProcessMapping1.setProcessId(1);
		dynamicProcessMapping1.setCurrentExecutionUnit(-1);
		dynamicProcessMapping1.setCurrentExecutionUnitType(ExecutionUnitType.STARTNODE);
		dynamicProcessMapping1.setCurrentResult(ExecutionUnitResultType.SUCCESS);

		//dynamicProcessMapping1.setDynamicKey(dk);
		dynamicProcessMapping1.setNextExecutionUnit(1);
		dynamicProcessMapping1.setNextExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping1.setGroupId(1);

		//DynamicExecutionKey dk2 = new DynamicExecutionKey();
		dynamicProcessMapping2.setProcessId(1);
		dynamicProcessMapping2.setCurrentExecutionUnit(1);
		dynamicProcessMapping2.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping2.setCurrentResult(ExecutionUnitResultType.SUCCESS);

		//dynamicProcessMapping2.setDynamicKey(dk2);
		dynamicProcessMapping2.setNextExecutionUnit(2);
		dynamicProcessMapping2.setNextExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping2.setGroupId(2);

		//DynamicExecutionKey dk3 = new DynamicExecutionKey();
		dynamicProcessMapping3.setProcessId(1);
		dynamicProcessMapping3.setCurrentExecutionUnit(2);
		dynamicProcessMapping3.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping3.setCurrentResult(ExecutionUnitResultType.SUCCESS);

		//dynamicProcessMapping3.setDynamicKey(dk3);
		dynamicProcessMapping3.setNextExecutionUnit(2);
		dynamicProcessMapping3
				.setNextExecutionUnitType(ExecutionUnitType.PROCESS);

		//DynamicExecutionKey dk11 = new DynamicExecutionKey();
		dynamicProcessMapping11.setProcessId(1);
		dynamicProcessMapping11.setCurrentExecutionUnit(2);
		dynamicProcessMapping11.setCurrentExecutionUnitType(ExecutionUnitType.PROCESS);
		dynamicProcessMapping11.setCurrentResult(ExecutionUnitResultType.SUCCESS);
		//dynamicProcessMapping11.setDynamicKey(dk11);
		dynamicProcessMapping11.setNextExecutionUnit(8);
		dynamicProcessMapping11
				.setNextExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping11.setGroupId(4);

		//DynamicExecutionKey dk12 = new DynamicExecutionKey();
		dynamicProcessMapping12.setProcessId(1);
		dynamicProcessMapping12.setCurrentExecutionUnit(2);
		dynamicProcessMapping12.setCurrentExecutionUnitType(ExecutionUnitType.PROCESS);
		dynamicProcessMapping12.setCurrentResult(ExecutionUnitResultType.ABORT);
		//dynamicProcessMapping12.setDynamicKey(dk12);
		dynamicProcessMapping12.setNextExecutionUnit(-1);
		dynamicProcessMapping12
				.setNextExecutionUnitType(ExecutionUnitType.ENDNODE);

		//DynamicExecutionKey dk13 = new DynamicExecutionKey();
		dynamicProcessMapping13.setProcessId(1);
		dynamicProcessMapping13.setCurrentExecutionUnit(8);
		dynamicProcessMapping13.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping13.setCurrentResult(ExecutionUnitResultType.SUCCESS);
		//dynamicProcessMapping13.setDynamicKey(dk13);
		dynamicProcessMapping13.setNextExecutionUnit(8);
		dynamicProcessMapping13
				.setNextExecutionUnitType(ExecutionUnitType.ENDNODE);

		// Linear Process
		com.homeshop18.bpm.Entity.Process replacementProcess = new com.homeshop18.bpm.Entity.Process();
		replacementProcess.setProcessName("replacementProcess");
		ProcessKey pk2 = new ProcessKey();
		pk2.setProcessId(2);
		pk2.setProcessVersion("1.0");
		replacementProcess.setProcessKey(pk2);
		replacementProcess
				.setProcessDescription("started with replacement case");
		// refundProcess.setProcessType(ProcessType.LINEAR);
		// Linear Process mapping
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping4 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping5 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping6 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping7 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping8 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping9 = new com.homeshop18.bpm.Entity.DynamicExecution();
		com.homeshop18.bpm.Entity.DynamicExecution dynamicProcessMapping10 = new com.homeshop18.bpm.Entity.DynamicExecution();

		//DynamicExecutionKey dk4 = new DynamicExecutionKey();
		dynamicProcessMapping4.setProcessId(2);
		dynamicProcessMapping4.setCurrentExecutionUnit(-1);
		dynamicProcessMapping4.setCurrentExecutionUnitType(ExecutionUnitType.STARTNODE);
		dynamicProcessMapping4.setCurrentResult(ExecutionUnitResultType.SUCCESS);
		//dynamicProcessMapping4.setDynamicKey(dk4);
		dynamicProcessMapping4.setNextExecutionUnit(3);
		dynamicProcessMapping4.setNextExecutionUnitType(ExecutionUnitType.TASK);

		//DynamicExecutionKey dk5 = new DynamicExecutionKey();
		dynamicProcessMapping5.setProcessId(2);
		dynamicProcessMapping5.setCurrentExecutionUnit(3);
		dynamicProcessMapping5.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping5.setCurrentResult(ExecutionUnitResultType.SUCCESS);
		//dynamicProcessMapping5.setDynamicKey(dk5);
		dynamicProcessMapping5.setNextExecutionUnit(4);
		dynamicProcessMapping5.setNextExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping5.setGroupId(3);

		//DynamicExecutionKey dk6 = new DynamicExecutionKey();
		dynamicProcessMapping6.setProcessId(2);
		dynamicProcessMapping6.setCurrentExecutionUnit(3);
		dynamicProcessMapping6.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping6.setCurrentResult(ExecutionUnitResultType.FAILURE);
		//dynamicProcessMapping6.setDynamicKey(dk6);
		dynamicProcessMapping6.setNextExecutionUnit(5);
		dynamicProcessMapping6.setNextExecutionUnitType(ExecutionUnitType.TASK);

		//DynamicExecutionKey dk7 = new DynamicExecutionKey();
		dynamicProcessMapping7.setProcessId(2);
		dynamicProcessMapping7.setCurrentExecutionUnit(5);
		dynamicProcessMapping7.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping7.setCurrentResult(ExecutionUnitResultType.SUCCESS);
		//dynamicProcessMapping7.setDynamicKey(dk7);
		dynamicProcessMapping7.setNextExecutionUnit(-1);
		dynamicProcessMapping7
				.setNextExecutionUnitType(ExecutionUnitType.TERMINATE);

		//DynamicExecutionKey dk8 = new DynamicExecutionKey();
		dynamicProcessMapping8.setProcessId(2);
		dynamicProcessMapping8.setCurrentExecutionUnit(4);
		dynamicProcessMapping8.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping8.setCurrentResult(ExecutionUnitResultType.SUCCESS);
		//dynamicProcessMapping8.setDynamicKey(dk8);
		dynamicProcessMapping8.setNextExecutionUnit(6);
		dynamicProcessMapping8.setNextExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping8.setGroupId(3);

		//DynamicExecutionKey dk9 = new DynamicExecutionKey();
		dynamicProcessMapping9.setProcessId(2);
		dynamicProcessMapping9.setCurrentExecutionUnit(6);
		dynamicProcessMapping9.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping9.setCurrentResult(ExecutionUnitResultType.SUCCESS);
		//dynamicProcessMapping9.setDynamicKey(dk9);
		dynamicProcessMapping9.setNextExecutionUnit(7);
		dynamicProcessMapping9.setNextExecutionUnitType(ExecutionUnitType.TASK);

		//DynamicExecutionKey dk10 = new DynamicExecutionKey();
		dynamicProcessMapping10.setProcessId(2);
		dynamicProcessMapping10.setCurrentExecutionUnit(7);
		dynamicProcessMapping10.setCurrentExecutionUnitType(ExecutionUnitType.TASK);
		dynamicProcessMapping10.setCurrentResult(ExecutionUnitResultType.SUCCESS);
		//dynamicProcessMapping10.setDynamicKey(dk10);
		dynamicProcessMapping10.setNextExecutionUnit(-1);
		dynamicProcessMapping10
				.setNextExecutionUnitType(ExecutionUnitType.ENDNODE);

		// Task entries
		Task firstTask = new Task();
		Task secondTask = new Task();
		Task thirdTask = new Task();
		Task fourthTask = new Task();
		Task fifthTask = new Task();
		Task sixthTask = new Task();
		Task seventhTask = new Task();
		Task eighthTask = new Task();

		firstTask.setTaskId(1);
		firstTask
				.setTaskHandler("com.homeshop18.casepanel.Tasks.DummyTaskHandler");
		firstTask.setTaskDataInputType(null);
		firstTask.setTaskDataOutputType("java.lang.String");
		firstTask.setTaskType(TaskType.USER);

		secondTask.setTaskId(2);
		secondTask
				.setTaskHandler("com.homeshop18.casepanel.Tasks.DummyTaskHandler");
		secondTask.setTaskDataInputType(null);
		secondTask.setTaskDataOutputType("java.lang.String");
		secondTask.setTaskType(TaskType.USER);

		thirdTask.setTaskId(3);
		thirdTask
				.setTaskHandler("com.homeshop18.casepanel.Tasks.ReversePickupRequired");
		thirdTask.setTaskDataInputType(null);
		thirdTask.setTaskDataOutputType("java.lang.String");
		thirdTask.setTaskType(TaskType.SCRIPT);

		fourthTask.setTaskId(4);
		fourthTask
				.setTaskHandler("com.homeshop18.casepanel.Tasks.ReversePickupDone");
		fourthTask.setTaskDataInputType(null);
		fourthTask.setTaskDataOutputType("java.lang.String");
		fourthTask.setTaskType(TaskType.USER);

		fifthTask.setTaskId(5);
		fifthTask
				.setTaskHandler("com.homeshop18.casepanel.Tasks.ReversePickupFailed");
		fifthTask.setTaskDataInputType(null);
		fifthTask.setTaskDataOutputType("java.lang.String");
		fifthTask.setTaskType(TaskType.USER);

		sixthTask.setTaskId(6);
		sixthTask
				.setTaskHandler("com.homeshop18.casepanel.Tasks.ReversePickupDelivered");
		sixthTask.setTaskDataInputType(null);
		sixthTask.setTaskDataOutputType("java.lang.String");
		sixthTask.setTaskType(TaskType.USER);

		seventhTask.setTaskId(7);
		seventhTask
				.setTaskHandler("com.homeshop18.casepanel.Tasks.SalesReturn");
		seventhTask.setTaskDataInputType(null);
		seventhTask.setTaskDataOutputType("java.lang.String");
		seventhTask.setTaskType(TaskType.SCRIPT);

		eighthTask.setTaskId(8);
		eighthTask
				.setTaskHandler("com.homeshop18.casepanel.Tasks.ProcessReplacement");
		eighthTask.setTaskDataInputType(null);
		eighthTask.setTaskDataOutputType("java.lang.String");
		eighthTask.setTaskType(TaskType.USER);
		// entityManager.getTransaction().begin();
		entityManager.persist(g1);
		entityManager.persist(g2);
		entityManager.persist(g3);
		entityManager.persist(g4);
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(user3);
		entityManager.persist(user4);
		entityManager.persist(user5);
		entityManager.persist(masterCaseProcess);
		entityManager.persist(replacementProcess);
		entityManager.persist(dynamicProcessMapping1);
		entityManager.persist(dynamicProcessMapping2);
		entityManager.persist(dynamicProcessMapping3);
		entityManager.persist(dynamicProcessMapping4);
		entityManager.persist(dynamicProcessMapping5);
		entityManager.persist(dynamicProcessMapping6);
		entityManager.persist(dynamicProcessMapping7);
		entityManager.persist(dynamicProcessMapping8);
		entityManager.persist(dynamicProcessMapping9);
		entityManager.persist(dynamicProcessMapping10);
		entityManager.persist(dynamicProcessMapping11);
		entityManager.persist(dynamicProcessMapping12);
		entityManager.persist(dynamicProcessMapping13);
		entityManager.persist(firstTask);
		entityManager.persist(secondTask);
		entityManager.persist(thirdTask);
		entityManager.persist(fourthTask);
		entityManager.persist(fifthTask);
		entityManager.persist(sixthTask);
		entityManager.persist(seventhTask);
		entityManager.persist(eighthTask);
		// entityManager.getTransaction().commit();
		// entityManager.close();;

	}

}
