package BusinessLayer.UserModule;

import ServiceLayer.DataSource.DAOFactory;
import ServiceLayer.DataSource.UserDAO;

public class User
{
	String firstName;
	String lastName;
	String cnic;
	String UserName;
	String password;
	int age;
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getCnic()
	{
		return cnic;
	}
	public void setCnic(String cnic)
	{
		this.cnic = cnic;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getUserName()
	{
		return UserName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public void setUserName(String userName)
	{
		UserName = userName;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public static int totalUsers()
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		UserDAO dao = factory.getUserDao();
		return dao.countUsers();
	}
	public void addToDatabase()
	{
		DAOFactory fact = DAOFactory.getDataServiceInstance(1);
		UserDAO dao = fact.getUserDao();
		dao.addUser(this);

	}
}
