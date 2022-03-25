package BusinessLayer.UserModule;

import ServiceLayer.DataSource.AdminDAO;
import ServiceLayer.DataSource.DAOFactory;

public class Admin
{
	String userName = "";
	String password = "";
	String firstName = "";
	String lastName = "";
	String emailId = "";
	String cnic = "";

	// Getter Functions
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getEmailId()
	{
		return emailId;
	}
	public String getCnic()
	{
		return cnic;
	}
	// Setter Functions
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
	public void setCnic(String cnic)
	{
		this.cnic = cnic;
	}

	// Other Functions
	public String toString()
	{
		return this.userName + "  " + this.password + "  " + this.firstName + "  " + this.lastName + "  " + this.emailId
				+ "  " + this.cnic;
	}
	// SQL Functions
	public boolean addToDB() throws Exception
	{
		boolean check = false;
		DAOFactory fact = DAOFactory.getDataServiceInstance(1);
		AdminDAO dao = fact.getAdminDao();
		try {
			dao.addAdmin(this);
			check = true;
		}catch(IllegalArgumentException exc)
		{
			check = false;
		}
		return check;
	}
	public void deleteAdminFromDB() throws Exception
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		AdminDAO adminDao = factory.getAdminDao();
		adminDao.deleteAdmin(this);
	}
	public void updateDataDB() throws Exception
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		AdminDAO adDao = factory.getAdminDao();
		adDao.updateAdmin(this);
	}

	public static Admin getAdminFromDB(String userName)
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		AdminDAO adDao = factory.getAdminDao();
		return adDao.getAdmin(userName);
	}
}
