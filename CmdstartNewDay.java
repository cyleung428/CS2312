
public class CmdstartNewDay extends RecordedCommand{

	String oldDay;
	String newDay;
	
	
	@Override
	public void execute(String[] cmdParts) {
		oldDay = SystemDate.getInstance().clone().toString();
		newDay = cmdParts[1];
		SystemDate.createTheInstance(newDay);
		
		addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); 
		System.out.println("Done.");//<====== There maybe some commands stored in the redo list.  Clear them.
		
	}
	@Override
	public void undoMe() {
		SystemDate.createTheInstance(oldDay);
		addRedoCommand(this);
		
	}
	@Override
	public void redoMe() {
		SystemDate.createTheInstance(newDay);
		addUndoCommand(this);
		
	}
}
