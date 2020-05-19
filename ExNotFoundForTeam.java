
public class ExNotFoundForTeam extends Exception{

	public ExNotFoundForTeam() { super("Employee (May) not found for Production Team!"); }
	public ExNotFoundForTeam(String message1,String message2) { super("Employee ("+message1+") not found for "+message2+"!"); }
	
}
