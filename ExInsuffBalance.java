
public class ExInsuffBalance extends Exception{
	
	public ExInsuffBalance() { super("Insufficient balance"); }
	public ExInsuffBalance(int days) { super("Insufficient balance. "+ days + " days left only!"); }

}
