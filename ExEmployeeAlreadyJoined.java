
public class ExEmployeeAlreadyJoined extends Exception{

	public ExEmployeeAlreadyJoined() { super("Employee has already joined the team!"); }
	public ExEmployeeAlreadyJoined(String message) { super(message); }
}
