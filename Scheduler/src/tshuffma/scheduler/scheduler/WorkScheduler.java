package tshuffma.scheduler.scheduler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tshuffma.scheduler.database.Login;
import tshuffma.scheduler.database.PositionReader;
import tshuffma.scheduler.database.ScheduleReader;
import tshuffma.scheduler.queue.EmployeeQueue;
import tshuffma.scheduler.util.Employee;
import tshuffma.scheduler.util.Schedule;

/**
 * 
 * @author Tyler Huffman
 */
public class WorkScheduler {
	
	private PositionReader jobPositions;
	
	private ScheduleReader schedule;
	
	private Login logging;
	
	private boolean isLoggedIn;
	
	private EmployeeQueue employees;
	
	public WorkScheduler() {
		logging = new Login();
		isLoggedIn = false;
		employees = null;
	}
	
	public boolean logIn(String user, String pass) {
		isLoggedIn = logging.account(user, pass);
		if (isLoggedIn) {
			jobPositions = new PositionReader();
			schedule = new ScheduleReader(jobPositions);
			employees= schedule.getEmployeeList();
		}
		return isLoggedIn;
	}
	
	public void logOut() {
		isLoggedIn = false;
		jobPositions = null;
		schedule = null;
		employees = null;
	}
	
	public void addAccount(String user, String pass) {
		try {
			Scanner reader = new Scanner(new File("tshuffma\\scheduler\\database\\Login.java"));
			ArrayList<String> lines = new ArrayList<String>();
			while (reader.hasNextLine()) {
				String next = reader.nextLine();
				if (next.equals("String[] usernames")) {
					String fix = next.substring(0, next.lastIndexOf("}"));
					fix +=  ", \"" + user + "\"};";
					next = fix;
				} else if (next.equals("String[] passwords")) {
					String fix = next.substring(0, next.lastIndexOf("}"));
					fix +=  ", \"" + user + "\"};";
					next = fix;
				}
				lines.add(next);
			}
			reader.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter("Login.java"));
			for (int i = 0; i < lines.size(); i++) {
				writer.write(lines.get(i));
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeSchedule() {
		String[][][] people = new String[employees.size()][7][];
		int[][] list = new int[jobPositions.size()][7 * 24];
		int[][] jobs = new int[jobPositions.size()][7 * 24];
		int[][] unjobs = new int[jobPositions.size()][7 * 24];
		double[][] imp = new double[employees.size()][2];
		int index = 0;
		for (int i = 0; i < employees.size(); i++) {
			for (int j = 0; j < 7; j++) {
				int st = (int)(Math.round(employees.getEmployee(i).getPreferredSchedule().dayAt(j).getStartingTime()));
				int en = (int)(Math.round(employees.getEmployee(i).getPreferredSchedule().dayAt(j).getEndingTime()));
				people[i][j] = employees.getEmployee(i).getPreferredSchedule().dayAt(j).getJobPositionAbbreviation().split(",");
				imp[i][0] += (en - st);
				imp[i][1] += (int)((en - st) / schedule.getHours());
				if (st != en)
					for (int k = st; k <= en; k++)
						for (int l = 0; l < people[i][j].length; l++)
							list[jobPositions.findJobIndex(people[i][j][l])][j * 24 + k]++;
			}
			if ((employees.getEmployee(i).isFullTime() && imp[i][0] <= schedule.getFullTime() + imp[i][1] * schedule.getLunch()) ||
					(!employees.getEmployee(i).isFullTime() && imp[i][0] < schedule.getFullTime() + imp[i][1] * schedule.getLunch())) {
				employees.getEmployee(i).setPresentSchedule(employees.getEmployee(i).getPreferredSchedule());
				for (int j = 0; j < 7 * 24; j++)
					if (people[i][(int)(j / 24)].length == 1 && list[index = jobPositions.findJobIndex(people[i][(int)(j / 24)][0])][j] != 0) {
						jobs[index][j]++;
						unjobs[index][j]--;
					}
			}
			for (int j = 0; j < 7 * 24; j++)
				for (int k = 0; k < people[i][(int)(j / 24)].length; k++)
					if (list[index = jobPositions.findJobIndex(people[i][(int)(j / 24)][k])][j] != 0)
						unjobs[index][j]++;
		}
		String[][] names = new String[jobPositions.size()][7];
		for (int i = 0; i < employees.size(); i++)
			for (int j = 0; j < 7; j++) 
				for (int k = 0; k < people[i][j].length; k++)
					if (unjobs[jobPositions.findJobIndex(people[i][j][k])][j] == 1 && k < 2)
						names[jobPositions.findJobIndex(people[i][j][k])][j] = employees.getEmployee(i).getName();
//		for (int i = 0; i < jobPositions.size(); i++) {
//			for (int j = 0; j < 7; j++)
//				System.out.print("" + names[i][j] + "\t");
//			System.out.println();
//		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		WorkScheduler schedule = new WorkScheduler();
		schedule.logIn("admin", "pas5w0Rd");
		schedule.writeSchedule();
		long end = System.currentTimeMillis();
		System.out.println("Elapsed Time: " + (end - start) + " ms");
	}
}
