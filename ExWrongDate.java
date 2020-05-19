
public class ExWrongDate extends Exception{
	public ExWrongDate() { super("Wrong Date. "); }
	public ExWrongDate(Day d) { super("Wrong Date.  System date is already "+d+"!"); }
	

}
