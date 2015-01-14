package com.homeshop18.bpm.Process;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Girish.Yadav
 *
 */
public class TaskDelegator {
	public enum GroupQueue {
		TROUBLESHOOT, ESCALATION, COURIER, RESOLUTION, SYSTEM
	};

	private Map<Integer, GroupQueue> groupResolver = new HashMap<Integer, GroupQueue>(
			4, (float) 0.75);
	private Queue<Integer> troubleshootQueue = new ArrayBlockingQueue<Integer>(
			10);
	private Queue<Integer> escalationQueue = new ArrayBlockingQueue<Integer>(10);
	private Queue<Integer> courierQueue = new ArrayBlockingQueue<Integer>(10);
	private Queue<Integer> resolutiuonQueue = new ArrayBlockingQueue<Integer>(
			10);

	public int fetchUserIdfromGroup(int groupId) {
		GroupQueue groupQueue = groupResolver.get(groupId);
		int head = 0;
		switch (groupQueue) {
		case TROUBLESHOOT:
			synchronized (this) {
				head = troubleshootQueue.poll();
				troubleshootQueue.add(head);
			}
			break;
		case COURIER:
			synchronized (this) {
				head = courierQueue.poll();
				courierQueue.add(head);
			}
			break;
		case ESCALATION:
			synchronized (this) {
				head = escalationQueue.poll();
				escalationQueue.add(head);
			}
			break;
		case RESOLUTION:
			synchronized (this) {
				head = resolutiuonQueue.poll();
				resolutiuonQueue.add(head);
			}
			break;
		case SYSTEM:
			head = 0;
			break;
		default:
			break;

		}

		return head;
	}

	private static final class taskDelegatorHolder {
		public static final TaskDelegator taskDelegator = new TaskDelegator();
	}

	private TaskDelegator() {
		groupResolver.put(0, GroupQueue.SYSTEM);
		groupResolver.put(1, GroupQueue.TROUBLESHOOT);
		groupResolver.put(2, GroupQueue.ESCALATION);
		groupResolver.put(3, GroupQueue.COURIER);
		groupResolver.put(4, GroupQueue.RESOLUTION);
		// TODO put these details from db
		troubleshootQueue.add(1);
		escalationQueue.add(2);
		courierQueue.add(3);
		courierQueue.add(5);
		resolutiuonQueue.add(4);
	}

	public static TaskDelegator getDelegator() {
		return taskDelegatorHolder.taskDelegator;
	}
}
