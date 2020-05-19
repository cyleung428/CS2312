
public class ExNoActingHead extends Exception{

	public ExNoActingHead() { super("Please name a member to be the acting head"); }
	public ExNoActingHead(String message) { super("Please name a member to be the acting head of " +message); }
}
