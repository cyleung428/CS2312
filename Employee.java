import java.util.*;

public class Employee implements Comparable<Employee>{
	private String name;
	private int yrLeavesEntitled;
	private ArrayList<LeavePeriod> allLeaves = new ArrayList<>();
	private ArrayList<String> allRoles = new ArrayList<>();
	private ArrayList<String> headRoles = new ArrayList<>();
	private ArrayList<ActHeadPeriod> allActPeriod = new ArrayList<>();
	
	public void checkActHeadPeriod(LeavePeriod l) throws ExOverlapActHead 
	{
		for (ActHeadPeriod act:allActPeriod)
		{
			if (act.getLeavePeriod().checkOverLap(l))
				throw new ExOverlapActHead(name,act.getTeam().getName(),act.getLeavePeriod());
		}
	}
	
	public void addActPeriod(ActHeadPeriod ap)
	{
		allActPeriod.add(ap);
	}
	
	public void removeActPeriod(ActHeadPeriod ap)
	{
		allActPeriod.remove(ap);
	}
	public ArrayList<String> getHeadRoles() {return headRoles;}
	
	
	public void addheadRoles(String role)
	{
		headRoles.add(role);
	}
	
	public void reMoveHeadRoles(String role)
	{
		headRoles.remove(role);
	}
	
	public void addRoles(String role)
	{
		allRoles.add(role);
		Collections.sort(allRoles);
	}
	public void removeRoles(String role)
	{
		allRoles.remove(role);
	}
	
	public ArrayList<LeavePeriod> getAllLeave() {return allLeaves;}
	
	public Employee(String n,int yle)
	{
		name = n;
		yrLeavesEntitled = yle;
	}
	
	public void addLeaves(LeavePeriod leaves) throws ExOverlap, ExInsuffBalance
	{
		
		yrLeavesEntitled -= leaves.getDays();
		allLeaves.add(leaves);
		Collections.sort(allLeaves);
	}
	public void removeLeaves(LeavePeriod leaves)
	{
		allLeaves.remove(leaves);
		yrLeavesEntitled+= leaves.getDays();
	}
	
	public String getName() {
		return name;
	}
	public int getYr() {
		return yrLeavesEntitled;
	}
	
	
	public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) throws ExEmployeeNotFound
	{
		for (Employee e:list)
		{
			if(e.getName().equals(nameToSearch))
			{
				return e;
			}
		}
		throw new ExEmployeeNotFound();
		
	}
	
	public static boolean employeeExist(ArrayList<Employee> list, String name) {
		for (Employee e:list)
		{
			if(e.getName().equals(name))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Employee o) {
		return this.name.compareTo(o.name);
	}
	public void listLeaves() {
		if (allLeaves.isEmpty())
			System.out.println("No leave record");
		else
		{
		for (LeavePeriod lp:allLeaves)
		{
			System.out.println(lp);
		}
		}
	}
	public void listRoles() {
		if (allRoles.isEmpty())
			System.out.println("No role");
		for (String r:allRoles)
		{
			System.out.println(r);
		}
		
	}
	

}
