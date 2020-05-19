import java.util.ArrayList;

public abstract class RecordedCommand implements Command{
	private static ArrayList<RecordedCommand> undoList = new ArrayList<>();
	private static ArrayList<RecordedCommand> redoList = new ArrayList<>();
	
	public abstract void execute(String[] cmdParts);

	public abstract void undoMe();

	public abstract void redoMe();
	
	public void addRedoCommand(RecordedCommand cmd) {
		redoList.add(cmd);
	}
	public void addUndoCommand(RecordedCommand cmd) {
		undoList.add(cmd);
	}
	protected static void clearRedoList() {
		redoList.clear();
	}
	public static void undoOneCommand() {
		if(!undoList.isEmpty()) {
		undoList.remove(undoList.size()-1).undoMe();
		}else {
			System.out.println("Nothing to undo.");
		}
	}
	public static void redoOneCommand() {
		if(!redoList.isEmpty())
		{
		redoList.remove(redoList.size()-1).redoMe();
		}else {
			System.out.println("Nothing to redo.");
		}
	}
	
}
