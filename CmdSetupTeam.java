

public class CmdSetupTeam extends RecordedCommand{
	private Team t;
	
	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length<3)
				throw new ExInsufficientCommandArguments();
			Company company = Company.getInstance();
			if (!company.checkExist(cmdParts[2]))
				throw new ExEmployeeNotFound();
			if (company.checkTeamExist(cmdParts[1]))
				throw new ExTeamExists();
			t = company.createTeam(cmdParts[1], cmdParts[2]);
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		} catch (ExInsufficientCommandArguments e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExTeamExists e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void undoMe() {
		Company company = Company.getInstance();
		company.removeTeam(t);
		addRedoCommand(this);
	}
	@Override
	public void redoMe() {
		Company company = Company.getInstance();
		company.addTeam(t);
		addUndoCommand(this);
		
	}
	
	
	

}
