
public class ActHeadPeriod {
	private Team team;
	private LeavePeriod actPeriod;
	
	public ActHeadPeriod(Team t,LeavePeriod l)
	{
		team = t;
		actPeriod = l;
	}
	
	public Team getTeam() {return team;}
	public LeavePeriod getLeavePeriod() {return actPeriod;}

}
