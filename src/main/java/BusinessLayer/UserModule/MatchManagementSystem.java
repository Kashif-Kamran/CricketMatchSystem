package BusinessLayer.UserModule;

public class MatchManagementSystem
{

	Admin logedInAdmin = null;
	User logedInScorer = null;

	public MatchManagementSystem()
	{

	}

	public boolean loginAdmin(String userName, String password) // should throw Invalid Admin Exception
	{
		if (logedInAdmin != null)
		{
			return false;
		}
		Admin admin = Admin.getAdminFromDB(userName);
		if (admin == null
				|| (admin.getPassword().compareTo(password) != 0 || admin.getUserName().compareTo(userName) != 0))
		{
			System.out.println("Invalid UserName or Password. Try Again...");
			return false;
		}
		logedInAdmin = admin;
		return true;
	}
	public boolean loginScorer(String userName, String password) 
	{
		User scorer = User.loadUserFromDB(userName);
		if (scorer == null
				|| (scorer.getUserName().compareTo(userName) != 0 || scorer.getPassword().compareTo(password) != 0))
		{
			System.out.println("Unable to Login Scorer: Reason Invalid UserName or Password");
			return false;
		}
		logedInScorer = scorer;
		return true;
	}
	public User getLogedInScorer()
	{
		return logedInScorer;
	}
	public Admin getLogedInAdmin()
	{
		return logedInAdmin;
	}
	public boolean addNewScorer(User newUser)
	{
		if (logedInAdmin != null)
		{
			try {
				newUser.addToDB();
				return true;
			} catch (Exception exc)
			{
				System.out.println("Exception exc : " + exc.getMessage());
				return false;
			}
		}
		else {
			System.out.println("Admin is Not loged In");
		return false;
			
		}
		
	}
	public boolean logoutScorer()
	{
		if (logedInScorer != null)
		{
			logedInScorer = null;
			return true;
		}
		return false;

	}
	public boolean logoutAdmin()
	{
		if(logedInAdmin!=null)
		{
			logedInAdmin = null;
			return true;
		}
		return false;
	}

	
	public static void main(String[] args) throws Exception
	{
		MatchManagementSystem system = new MatchManagementSystem();
		if (system.loginAdmin("root", "root0101"))
		{
			System.out.println("Loged In : " + system.getLogedInAdmin().toString());
		}
		if (system.loginScorer("root", "root"))
		{
			System.out.println("Loged in Scorer : " + system.getLogedInScorer().toString());

		}
	}
}
