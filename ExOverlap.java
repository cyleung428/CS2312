
public class ExOverlap extends Exception{
	public ExOverlap(LeavePeriod l) { super("Overlap with leave from "+ l +"!"); }
	public ExOverlap() { super("Overlap with leave!"); }
}
