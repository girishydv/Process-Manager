package com.girish.bpm.Process;

import java.util.List;

import com.girish.bpm.DAO.DataLoader;
import com.girish.bpm.Entity.TaskInstance;

public class ConsoleMenuDemo {
	private static boolean loaded = false;

	static {
		if (!loaded)
			// DataLoader.loadData();
			loaded = true;
		System.out.println("Loading data");

	}

	public static void main(String[] args) {
		// Local variable
		int swValue;
		while (true) {
			// Display menu graphics
			System.out.println("============================");
			System.out.println("|   Process Manager DEMO    |");
			System.out.println("============================");
			System.out.println("| Options:                 |");
			System.out.println("|        1. Start Process  |");
			System.out.println("|        2. Complete Task1 |");
			System.out.println("|        3. Complete Task2 |");
			System.out.println("|        4. Complete Task3 |");
			System.out.println("|        5. Complete Task4 |");
			System.out.println("|        6. Complete Task5 |");
			System.out.println("|        7. Exit           |");
			System.out.println("============================");
			swValue = Keyin.inInt(" Select option: ");

			// Switch construct
			switch (swValue) {
			case 1:
				System.out
						.println("Going Call process manager for starting dynamic process for case.");
				// new ProcessManager().startProcess(1);
				break;
			case 2:
				System.out
						.println("Going Call process manager for completing task1.");
				List<TaskInstance> taksInstance = new ProcessManager()
						.getInstances("girish.yadav@homeshop18.com");
				// new
				// ProcessManager().completeTask(taksInstance.get(0).getTaskInstanceId(),
				// null, 2);
				break;
			case 3:
				System.out
						.println("Going Call process manager for completing task2.");
				/*
				 * TaskInstance taksInstance2 = ProcessManager
				 * .getInstances("anuj.paul@homeshop18.com");
				 * ProcessManager.completeTask
				 * (taksInstance2.getTaskInstanceId(), null, 3);
				 */
				break;
			case 4:
				System.out
						.println("Going Call process manager for completing task3.");
				/*
				 * TaskInstance taksInstance3 = ProcessManager
				 * .getInstances("amit.chandra@homeshop18.com");
				 * ProcessManager.completeTask
				 * (taksInstance3.getTaskInstanceId(), null, 4);
				 */
				break;
			case 5:
				System.out
						.println("Going Call process manager for completing task4.");
				/*
				 * TaskInstance taksInstance4 = ProcessManager
				 * .getInstances("harvijay.gagneja@homeshop18.com");
				 * ProcessManager
				 * .completeTask(taksInstance4.getTaskInstanceId(), null, 5);
				 */
				break;
			case 6:
				System.out
						.println("Going Call process manager for completing task5.");
				/*
				 * TaskInstance taksInstance5 = ProcessManager
				 * .getInstances("samarth.srivastava@homeshop18.com");
				 * ProcessManager
				 * .completeTask(taksInstance5.getTaskInstanceId(), null, 0);
				 */
				break;
			case 7:
				System.exit(1);
				break;
			default:
				System.out.println("Invalid selection");
				break; // This break is not really necessary
			}
		}
	}
}

// **********************************************************
// **********************************************************
// Program: Keyin
// Reference: Session 20
// Topics:
// 1. Using the read() method of the ImputStream class
// in the java.io package
// 2. Developing a class for performing basic console
// input of character and numeric types
// **********************************************************
// **********************************************************

class Keyin {

	// *******************************
	// support methods
	// *******************************
	// Method to display the user's prompt string
	public static void printPrompt(String prompt) {
		System.out.print(prompt + " ");
		System.out.flush();
	}

	// Method to make sure no data is available in the
	// input stream
	public static void inputFlush() {
		int dummy;
		int bAvail;

		try {
			while ((System.in.available()) != 0)
				dummy = System.in.read();
		} catch (java.io.IOException e) {
			System.out.println("Input error");
		}
	}

	// ********************************
	// data input methods for
	// string, int, char, and double
	// ********************************
	public static String inString(String prompt) {
		inputFlush();
		printPrompt(prompt);
		return inString();
	}

	public static String inString() {
		int aChar;
		String s = "";
		boolean finished = false;

		while (!finished) {
			try {
				aChar = System.in.read();
				if (aChar < 0 || (char) aChar == '\n')
					finished = true;
				else if ((char) aChar != '\r')
					s = s + (char) aChar; // Enter into string
			}

			catch (java.io.IOException e) {
				System.out.println("Input error");
				finished = true;
			}
		}
		return s;
	}

	public static int inInt(String prompt) {
		while (true) {
			inputFlush();
			printPrompt(prompt);
			try {
				return Integer.valueOf(inString().trim()).intValue();
			}

			catch (NumberFormatException e) {
				System.out.println("Invalid input. Not an integer");
			}
		}
	}

	public static char inChar(String prompt) {
		int aChar = 0;

		inputFlush();
		printPrompt(prompt);

		try {
			aChar = System.in.read();
		}

		catch (java.io.IOException e) {
			System.out.println("Input error");
		}
		inputFlush();
		return (char) aChar;
	}

	public static double inDouble(String prompt) {
		while (true) {
			inputFlush();
			printPrompt(prompt);
			try {
				return Double.valueOf(inString().trim()).doubleValue();
			}

			catch (NumberFormatException e) {
				System.out
						.println("Invalid input. Not a floating point number");
			}
		}
	}
}
