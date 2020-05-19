
public class CmdListLeaves implements Command{

	@Override
	public void execute(String[] cmdParts) {
		try {
			Company company = Company.getInstance();
			if (cmdParts.length >1)
			{
				Employee e = company.search(cmdParts[1]);
				e.listLeaves();
			}else
			{
				company.listAllLeaves();
			}
		} catch (ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		}

	}

}
