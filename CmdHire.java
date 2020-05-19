
public class CmdHire extends RecordedCommand{
	private Employee e;
	
	@Override
	public void execute(String[] cmdParts)
	{
		try {
			if (cmdParts.length<3)
			    throw new ExInsufficientCommandArguments();
			Company company = Company.getInstance();
			if (company.checkExist(cmdParts[1]))
				throw new ExEmployeeExist();
			if (Integer.parseInt(cmdParts[2])>300)
				throw new ExAnnualLeavesOutOfRange();
			e = company.createEmployee(cmdParts[1],Integer.parseInt(cmdParts[2]));
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done.");
		} catch (ExAnnualLeavesOutOfRange e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientCommandArguments e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeExist e) {
			System.out.println(e.getMessage());
		} 

	}
	
	@Override
	public void undoMe()
	{
		Company company = Company.getInstance();
		company.removeEmployee(e);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		Company company = Company.getInstance();
		company.addEmployee(e);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}


}
