import java.util.ArrayList;

public class LeavePeriod implements Comparable<LeavePeriod>{
	private Day startDay;
	private Day finishDay;
	private ArrayList<Day> leaveDays = new ArrayList<>();
	private int days;
	
	public ArrayList<Day> getLeaveDays() {return leaveDays;}
	
	public LeavePeriod (Day dayS,Day dayF)
	{
		startDay = dayS;
		finishDay = dayF;
		int times = 1;
		int sYear = dayS.getYear();
		int sMonth = dayS.getMonth();
		int sDay = dayS.getDay();
		int fYear = dayF.getYear();
		int fMonth = dayF.getMonth();
		int fDay = dayF.getDay();
		while (sYear!=fYear || sMonth!=fMonth||sDay!=fDay)
		{
			
			if (Day.valid(sYear, sMonth, sDay+1))
			{
				sDay++;
				leaveDays.add(new Day(sYear,sMonth,sDay));
			}else if (Day.valid(sYear, sMonth+1, 1))
			{
				sMonth++;
				sDay = 1;
				leaveDays.add(new Day(sYear,sMonth,sDay));
			}else
			{
				sYear++;
				sMonth = 1;
				sDay = 1;
				leaveDays.add(new Day(sYear,sMonth,sDay));
			}
			times++;
		}
		days = times;
	}
		
		


	public int getDays() {return days;}
	public Day getStart() {return startDay;}
	public Day getFinish() {return finishDay;}
	
	public String toString() {
		return startDay + " to "+finishDay;
	}

	@Override
	public int compareTo(LeavePeriod o) {
		if (startDay.getYear()>o.startDay.getYear())
			return 1;
		else if (startDay.getYear()<o.startDay.getYear())
			return -1;
		else if (startDay.getMonth()>o.startDay.getMonth())
			return 1;
		else if (startDay.getMonth()<o.startDay.getMonth())
			return -1;
		else if (startDay.getDay()>o.startDay.getDay())
			return 1;
		else if (startDay.getDay()<o.startDay.getDay())
			return -1;
		else
			return 0;
	}


	public boolean checkOverLap(LeavePeriod l) {
		ArrayList<Day> daysCompare = l.getLeaveDays();
		for (Day d1:daysCompare)
		{
			for (Day d2:leaveDays)
			{
				if (d1.getDay()==d2.getDay()&&d1.getMonth()==d2.getMonth()&&d1.getYear()==d2.getYear())
				{
					return true;
				}
			}
		}
		
		
		return false;
	}
	

}
