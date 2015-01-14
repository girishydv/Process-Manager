/**
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.homeshop18.bpm.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import com.homeshop18.bpm.Entity.TaskInstance;
import com.homeshop18.bpm.Process.ProcessManager;

/**
 * @author Girish.Yadav
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TaskBean implements TaskLocal {

	@Resource
	private UserTransaction ut;

	@Inject
	ProcessManager taskService;

	public List<TaskInstance> retrieveTaskList(String actorId) throws Exception {

		// ut.begin();

		List<TaskInstance> list;

		try {
			list = taskService.getInstances(actorId);
			// ut.commit();
		} /*
		 * catch (RollbackException e) { e.printStackTrace(); throw new
		 * RuntimeException(e); }
		 */
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		System.out.println("retrieveTaskList by " + actorId);
		for (TaskInstance task : list) {
			System.out.println(" task.getId() = " + task.getTaskId());
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.homeshop18.casepanel.ejb.TaskLocal#completeTask(java.lang.String, long)
	 */
	public void completeTask(String actorId, long taskId) throws Exception {

		ut.begin();

		try {
			System.out.println("approveTask (taskId = " + taskId + ") by "
					+ actorId);
			// taskService.start(taskId, actorId);
			taskService.completeTask(Long.valueOf(taskId), null,
					Integer.valueOf(actorId));

			// Thread.sleep(10000); // To test OptimisticLockException

			ut.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			if (cause != null && cause instanceof OptimisticLockException) {
				// Concurrent access to the same process instance
				throw new ProcessOperationException(
						"The same process instance has likely been accessed concurrently",
						e);
			}
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			// Transaction might be already rolled back by TaskServiceSession
			if (ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.rollback();
			}
			throw new RuntimeException(e);
		}
	}

}
