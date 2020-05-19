import java.util.*;

public class Company {
	private ArrayList<Employee> allEmployee;
	private ArrayList<Team> allTeams;
	private static Company instance = new Company();
	private Company() {
		allEmployee = new ArrayList<>();
		allTeams = new ArrayList<>();
	}
	public static Company getInstance() {
		return instance;
	}
	
	public void listTeams() {
		Team.list(allTeams);
	}
	
	public Employee createEmployee(String name, int yr)
	{
		Employee e = new Employee(name,yr);
		allEmployee.add(e);
		Collections.sort(allEmployee);
		return e;
		
	}
	
	public Team createTeam(String tName, String hName) throws ExEmployeeNotFound
	{
		Employee e = Employee.searchEmployee(allEmployee, hName);
		Team t = new Team(tName, e);
		allTeams.add(t);
		Collections.sort(allTeams);
		return t;
	}
	
	public void addEmployee(Employee e) {
		allEmployee.add(e);
		Collections.sort(allEmployee);
	}
	
	public void removeEmployee(Employee e) {
		allEmployee.remove(e);
	}
	
	public void removeTeam(Team t) {
		allTeams.remove(t);
		t.removeHead();
	}
	public void addTeam(Team t) {
		allTeams.add(t);
		t.addHead();
	}
	public Employee search(String name) throws ExEmployeeNotFound {
		return Employee.searchEmployee(allEmployee, name);
	}
	public Team searchTeam(String name) {
		return Team.searchTeam(allTeams,name);
	}
	public boolean checkExist(String name) {
		return Employee.employeeExist(allEmployee, name);
	}
	public boolean checkTeamExist(String name) {
		return Team.checkExist(allTeams,name);
	}
	public void listEmployees() {
		for(Employee a:allEmployee)
		{
			System.out.println(a.getName()+" (Entitled Annual Leaves: " + a.getYr()+" days)");
		}
	}
	public void listAllLeaves() {
		for(Employee e:allEmployee)
		{
			System.out.println(e.getName()+ ":");
			e.listLeaves();
		}
		
	}
	public void listAllTeamMembers() {
		for (Team t:allTeams)
		{
			t.listMember();
		}
	}

}
