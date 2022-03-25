package BusinessLayer.UserModule;

import java.util.List;

import ServiceLayer.DataSource.DAOFactory;
import ServiceLayer.DataSource.UserDAO;

public class User
{
	String firstName = "";
	String lastName = "";
	String cnic = "";
	String UserName = "";
	String password = "";
	int age = -1;
	// Getter Functions
	public int getAge()
	{
		return age;
	}
	public String getCnic()
	{
		return cnic;
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

	// Setter Functions
	public void setAge(int age)
	{
		this.age = age;
	}
	public void setCnic(String cnic)
	{
		this.cnic = cnic;
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

	// DataBase CRUD Operations
	public static int getUsersCountFromDB()
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		UserDAO dao = factory.getUserDao();
		return dao.countUsers();
	}
	public void addToDB() throws Exception
	{
		DAOFactory fact = DAOFactory.getDataServiceInstance(1);
		UserDAO dao = fact.getUserDao();
		try {
			dao.addUser(this);
		}
		catch(IllegalArgumentException exc) {
			throw new IllegalArgumentException("Unable to Add user. Reason: User Name is already Present");
		}
	}

	public static User loadUserFromDB(String userName)
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		UserDAO userDao = factory.getUserDao();
		User value = userDao.getUser(userName);
		return value;
	}
	public static List<User> getAllUsersFromDB()
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		UserDAO userDao = factory.getUserDao();
		return userDao.getAllUsers();
	}
	public void deleteUserFromDB() throws Exception
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		UserDAO userDao = factory.getUserDao();
		userDao.deleteUser(this);
	}
	public void updateDataDB() throws Exception
	{
		DAOFactory factory = DAOFactory.getDataServiceInstance(1);
		UserDAO userDao = factory.getUserDao();
		userDao.updateUser(this);
	}
	// Other Helping
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return this.UserName + "  " + this.password + "  " + this.firstName + "  " + this.lastName + "  " + this.cnic
				+ "  " + this.age;
	}

}
