import java.util.*;

public class Team implements Comparable<Team>{
	private String teamName;
	private Employee head;
	private Day dateSetup;
	private String headRole;
	private ArrayList<Employee> member = new ArrayList<>();
	private ArrayList<Actperiod> allActHeads = new ArrayList<>();
	
	public String getName() {return teamName;}
	
	public void addMember(Employee e)
	{
		member.add(e);
		e.addRoles(teamName);
		Collections.sort(member);
	}
	public void removeMember(Employee e)
	{
		member.remove(e);
		e.removeRoles(teamName);
	}
	
	public Team(String n, Employee hd)
	{
		teamName = n;
		head = hd;
		dateSetup = SystemDate.getInstance().clone();
		member.add(hd);
		hd.addheadRoles(teamName);
		headRole = teamName + " (Head of Team)";
		hd.addRoles(headRole);
	}
	public static void list(ArrayList<Team> list)
	{
		System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
		for(Team t: list)
		{
			System.out.printf("%-30s%-10s%-13s\n", t.teamName,t.head.getName(),t.dateSetup );
		}
		
	}
	@Override
	public int compareTo(Team o) {
		return this.teamName.compareTo(o.teamName);
	}
	
	public static Team searchTeam(ArrayList<Team> list, String Name) {
		for (Team t:list)
		{
			if (Name.equals(t.teamName))
			{
				return t;
			}
		}
		return null;
	}
	public static boolean checkExist(ArrayList<Team> list, String name) {
		for (Team t:list)
		{
			if (name.equals(t.teamName))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean checkJoin(String cmdParts) {
		for (Employee e:member)
		{
			if (cmdParts.equals(e.getName()))
				return true;
		}
		return false;
	}
	public void listMember() {
		System.out.printf("%s:\n",teamName);
		for (Employee e:member)
		{
			if (e.equals(head))
				System.out.println(e.getName()+" (Head of Team)");
			else
				System.out.println(e.getName());
		}
		if (!allActHeads.isEmpty())
		{
			System.out.println("Acting heads:");
			for (Actperiod p:allActHeads)
			{
				System.out.println(p);
			}
		}
	}
	public void removeHead() {
		head.removeRoles(headRole);
		head.reMoveHeadRoles(teamName);
	}
	public void addHead() {
		head.addRoles(headRole);
		head.addheadRoles(teamName);	
	}
	public void addActHeads(Actperiod p) {
		allActHeads.add(p);
		Collections.sort(allActHeads);
	}

	public void removeActHeads(Actperiod p) {
		allActHeads.remove(p);
		
	}


}
