package ServiceLayer.DataSource;

import java.util.ArrayList;

import BusinessLayer.UserModule.User;

public interface UserDAO
{
	public int countUsers();

	public boolean addUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);

	public boolean getUser(String userName);

	public ArrayList<User> getAllUsers();
}
