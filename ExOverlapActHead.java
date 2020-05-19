
public class ExOverlapActHead extends Exception{

	public ExOverlapActHead() { super("Overlap!"); }
	public ExOverlapActHead(String name, String team, LeavePeriod leavePeriod) { super("Cannot take leave.  "+name+" is the acting head of "+team+" during "+leavePeriod+"!"); }
	
}
