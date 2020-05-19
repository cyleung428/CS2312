import java.util.ArrayList;

public class CmdTakeLeave extends RecordedCommand{
	private LeavePeriod leaves;
	private Employee e;
	private ArrayList<Actperiod> allPeriod = new ArrayList<>();
	private ArrayList<Team> allActTeam = new ArrayList<>();
	private ArrayList<Employee> allActHead = new ArrayList<>();
	private ArrayList<ActHeadPeriod> allActHeadPeriod = new ArrayList<>();
	


	@Override
	public void execute(String[] cmdParts) {
		try {
			Company company = Company.getInstance();
			SystemDate sDate = SystemDate.getInstance();
			e = company.search(cmdParts[1]);
			leaves = new LeavePeriod(new Day(cmdParts[2]), new Day(cmdParts[3]));
			
			
			if (sDate.getYear() >= leaves.getStart().getYear())
			{
				if (sDate.getYear() == leaves.getStart().getYear())
				{
					if (sDate.getMonth()>leaves.getStart().getMonth())
						throw new ExWrongDate(sDate);
					else if (sDate.getMonth()==leaves.getStart().getMonth() && sDate.getDay() >leaves.getStart().getDay())
						throw new ExWrongDate(sDate);
				}
				else 
				{
					throw new ExWrongDate(sDate);
				}
			}
			if (leaves.getDays()>e.getYr())
				throw new ExInsuffBalance(e.getYr());
			ArrayList<LeavePeriod> list = e.getAllLeave();
			for (LeavePeriod l:list)
			{
				if(leaves.checkOverLap(l))
				{
					throw new ExOverlap(l);
				}
			}
			
			e.checkActHeadPeriod(leaves);
			
			int position = 4;
			while((position+2) <=cmdParts.length)
			{
				Team t = company.searchTeam(cmdParts[position]);
				allActTeam.add(t);
				if (!t.checkJoin(cmdParts[++position]))
				{
					throw new ExNotFoundForTeam(cmdParts[position],t.getName());
				}
				Employee actHead = company.search(cmdParts[position]);
				
				for (LeavePeriod l:actHead.getAllLeave())
				{
					if (l.checkOverLap(leaves))
					{
						throw new ExOnLeave(actHead.getName(),l);
					}
				}
				

				position++;
			}
			
			
			for (String role:e.getHeadRoles())
			{
				boolean checkIncludeRole = false;
				for(Team includedTeam:allActTeam)
				{
					if (role.equals(includedTeam.getName()))
					{
						checkIncludeRole =true;
					}
				}
				if (!checkIncludeRole)
				{
					throw new ExNoActingHead(role);
				}
			}
			
			
			
			
			
			
			position = 4;
			int times = e.getHeadRoles().size();
				
			while(times>0)
			{
				Team t = company.searchTeam(cmdParts[position]);
				Employee actHead = company.search(cmdParts[++position]);
				for (String r:e.getHeadRoles())
				{
					if (r.equals(t.getName()))
					{
						times--;
						Actperiod p = new Actperiod(leaves,actHead);
						ActHeadPeriod ap = new ActHeadPeriod(t,leaves);
						actHead.addActPeriod(ap);
						t.addActHeads(p);
						allPeriod.add(p);
						allActHead.add(actHead);
						allActHeadPeriod.add(ap);
					}
				}
				position++;
			}
			
			
			e.addLeaves(leaves);
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done.");

			
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExOverlap e) {
			System.out.println(e.getMessage());
		} catch (ExInsuffBalance e) {
			System.out.println(e.getMessage());
		} catch (ExWrongDate e) {
			System.out.println(e.getMessage());
		} catch (ExOnLeave e) {
			System.out.println(e.getMessage());
		} catch (ExNotFoundForTeam e) {
			System.out.println(e.getMessage());
		} catch (ExNoActingHead e) {
			System.out.println(e.getMessage());
		} catch (ExOverlapActHead e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public void undoMe() {
		e.removeLeaves(leaves);
		for (int i=0;i<allPeriod.size();i++)
		{
			allActTeam.get(i).removeActHeads(allPeriod.get(i));
		}
		for (int i=0;i<allActHead.size();i++)
		{
			allActHead.get(i).removeActPeriod(allActHeadPeriod.get(i));
		}
		addRedoCommand(this);
		
	}

	@Override
	public void redoMe() {
		try {
			e.addLeaves(leaves);
			for (int i=0;i<allPeriod.size();i++)
			{
				allActTeam.get(i).addActHeads(allPeriod.get(i));
			}
			for (int i=0;i<allActHead.size();i++)
			{
				allActHead.get(i).addActPeriod(allActHeadPeriod.get(i));
			}
			addUndoCommand(this);
		} catch (ExOverlap | ExInsuffBalance e) {
			System.out.println(e.getMessage());
		}
	}

}
