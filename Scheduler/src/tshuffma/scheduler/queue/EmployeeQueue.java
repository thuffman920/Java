package tshuffma.scheduler.queue;

import java.util.ArrayList;
import tshuffma.scheduler.util.Employee;

/**
 * This creates a list of employees, which holds the
 * preferred and present schedules, the names and their
 * job positions are in this company
 * @author Tyler Huffman
 */
public class EmployeeQueue {

	/** This is the list of employees in this company */
	private ArrayList<Employee> listOfEmployees;
	
	/**
	 * This initializes the list of employees, which is
	 * apart of this company
	 */
	public EmployeeQueue() {
		listOfEmployees = new ArrayList<Employee>();
	}
	
	/**
	 * This finds an employee from the list of employees
	 * which sits at a certain index
	 * @param index the index of the employee
	 * @return the employee at this index
	 */
	public Employee getEmployee(int index) {
		if (index >= this.size())
			throw new IndexOutOfBoundsException("Outside of the bounds");
		return listOfEmployees.get(index);
	}
	
	/**
	 * This takes a new employee and adds the employee
	 * to the rear of the list
	 * @param next the next employee to be added
	 */
	public void addToRear(Employee next) {
		this.listOfEmployees.add(next);
	}
	
	/**
	 * This adds a new employee at a certain index
	 * @param index the index of that the new employee
	 * @param next the new employee to be added
	 */
	public void addAtIndex(int index, Employee next) {
		this.listOfEmployees.add(index, next);
	}
	
	/**
	 * This finds an employee in the list, based solely
	 * upon a full name
	 * @param employeeName the name of the employee
	 * @return the employee associated with this name
	 */
	public Employee findEmployee(String employeeName) {
		int index = -1;
		for (int i = 0; i < this.size(); i++) {
			if (employeeName.equalsIgnoreCase(this.listOfEmployees.get(i).getName())) {
				index = i;
				break;
			}
		}
		if (index == -1)
			return null;
		return this.listOfEmployees.get(index);
	}

	/**
	 * This returns the length of the list of employees,
	 * which are employed in this company
	 * @return the integer size of the array
	 */
	public int size() {
		if (this.isEmpty())
			return 0;
		else
			return listOfEmployees.size();
	}
	
	/**
	 * This returns to the user if the array is empty or not
	 * @return true if the list of employees is empty
	 */
	public boolean isEmpty() {
		return this.listOfEmployees.isEmpty();
	}
	
	/**
	 * This changes the list of employees into a string formation,
	 * which prints out the preferred schedules
	 * @return the string formation of the preferred schedules
	 */
	public String toPreferredString() {
		String list = "";
		for (int i = 0; i < this.size(); i++)
			list += listOfEmployees.get(i).toPreferredString() + "\n";
		return list;
	}
	
	/**
	 * This changes the list of employees into a string formation,
	 * which prints out the present schedules
	 * @return the string formation of the present schedules
	 */
	public String toPresentString() {
		String list = "";
		for (int i = 0; i < this.size(); i++)
			list += listOfEmployees.get(i).toPresentString() + "\n";
		return list;
	}
}