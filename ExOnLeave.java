
public class ExOnLeave extends Exception{
	public ExOnLeave() { super("on leave "); }
	public ExOnLeave(String message, LeavePeriod l) { super(message+" is on leave during "+l+"!"); }
	

}
