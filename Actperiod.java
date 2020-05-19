
public class Actperiod implements Comparable<Actperiod>{
	private Employee actBoss;
	private LeavePeriod bossLeave;

	public Actperiod(LeavePeriod l,Employee e) {
		actBoss = e;
		bossLeave = l;
	}
	
	public Employee getActBoss() {return actBoss;}
	public LeavePeriod getBossLeave() {return bossLeave;}
	public String toString() {
		return bossLeave + ": "+ actBoss.getName();
	}

	@Override
	public int compareTo(Actperiod o) {
		if (bossLeave.getStart().getYear()>o.getBossLeave().getStart().getYear())
			return 1;
		else if (bossLeave.getStart().getYear()<o.getBossLeave().getStart().getYear())
			return -1;
		else if (bossLeave.getStart().getMonth()>o.getBossLeave().getStart().getMonth())
			return 1;
		else if (bossLeave.getStart().getMonth()<o.getBossLeave().getStart().getMonth())
			return -1;
		else if (bossLeave.getStart().getDay()>o.getBossLeave().getStart().getDay())
			return 1;
		else if (bossLeave.getStart().getDay()<o.getBossLeave().getStart().getDay())
			return -1;
		else
			return 0;
	}



}
