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

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import com.homeshop18.bpm.Process.ProcessManager;

/**
 * @author Girish.Yadav
 *
 */
@Startup
@javax.ejb.Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class ProcessBean implements ProcessLocal {

	@Resource
	private UserTransaction ut;

	private static boolean loaded = false;
	@Inject
	@Singleton
	ProcessManager processManager;

	// private RuntimeManager singletonManager;

	@PostConstruct
	public void configure() {
		// use toString to make sure CDI initializes the bean
		// this makes sure that RuntimeManager is started asap,
		// otherwise after server restart complete task won't move process
		// forward
		// singletonManager.toString();
	}

	public long startProcess(String recipient) throws Exception {

		long processInstanceId = -1;

		try {
			if (!loaded) {
				ut.begin();
				processManager.loadData();
				// start a new process instance
				// Map<String, Object> params = new HashMap<String, Object>();
				// params.put("recipient", recipient);
				/*
				 * ProcessInstance processInstance = ksession.startProcess(
				 * "com.sample.rewards-basic", params);
				 */

				// processInstanceId = processInstance.getId();
				// processInstanceId=processManager.startProcess(Integer.valueOf(recipient));
				ut.commit();
				loaded = true;
			}
			ut.begin();
			processInstanceId = processManager.startProcess(Integer
					.valueOf(recipient));
			/*
			 * TaskInstance
			 * taskInstance=processManager.createNewTask(processInstance);
			 * taskInstance
			 * .setProcessInstanceId(processInstance.getProcessInstanceId());
			 * processInstance
			 * .setTaskInstanceId(taskInstance.getTaskInstanceId());
			 */
			/*
			 * processManager.updateProcessAndTaskInstance(processInstance,
			 * taskInstance);
			 * AuditManager.getAuditor().logProcessInstance(processInstance,
			 * taskInstance.getTaskOwnerGroupId());
			 * AuditManager.getAuditor().logTaskInstance(taskInstance);
			 */
			ut.commit();

			System.out.println("Process started ... : processInstanceId = "
					+ processInstanceId);

		} catch (Exception e) {
			e.printStackTrace();
			if (ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.rollback();
			}
			throw e;
		}

		return processInstanceId;
	}

}
