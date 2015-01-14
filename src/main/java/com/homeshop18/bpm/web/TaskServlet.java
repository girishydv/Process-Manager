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

package com.homeshop18.bpm.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homeshop18.bpm.Entity.TaskInstance;
import com.homeshop18.bpm.ejb.ProcessOperationException;
import com.homeshop18.bpm.ejb.TaskLocal;

/**
 * @author Girish.Yadav
 *
 */
public class TaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private TaskLocal taskService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String cmd = req.getParameter("cmd");
		String user = req.getParameter("user");

		if (cmd.equals("list")) {

			List<TaskInstance> taskList;
			try {
				taskList = taskService.retrieveTaskList(user);
			} catch (Exception e) {
				throw new ServletException(e);
			}
			req.setAttribute("taskList", taskList);
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context
					.getRequestDispatcher("/task.jsp");
			dispatcher.forward(req, res);

		} else if (cmd.equals("complete")) {

			String message = "";
			long taskId = Long.parseLong(req.getParameter("taskId"));
			try {
				taskService.completeTask(user, taskId);
				message = "Task (id = " + taskId + ") has been completed by "
						+ user;
			} catch (ProcessOperationException e) {
				// Recoverable exception
				message = "Task operation failed. Please retry : "
						+ e.getMessage();
			} catch (Exception e) {
				// Unexpected exception
				throw new ServletException(e);
			}
			req.setAttribute("message", message);
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, res);

		}
	}
}