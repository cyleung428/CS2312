
public class CmdAddTeamMembers extends RecordedCommand{
	private Employee e;
	private Team t;

	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length<3)
				throw new ExInsufficientCommandArguments();
			Company company = Company.getInstance();
			if (!company.checkTeamExist(cmdParts[1]))
				throw new ExTeamNotFound();
			t = company.searchTeam(cmdParts[1]);
			e = company.search(cmdParts[2]);
			if (t.checkJoin(cmdParts[2]))
				throw new ExEmployeeAlreadyJoined();
			
			t.addMember(e);
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done.");
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExEmployeeAlreadyJoined e) {
			System.out.println(e.getMessage());
		} catch (ExInsufficientCommandArguments e) {
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public void undoMe() {
		t.removeMember(e);
		addRedoCommand(this);
		
	}

	@Override
	public void redoMe() {
		t.addMember(e);
		addUndoCommand(this);
	}

}
