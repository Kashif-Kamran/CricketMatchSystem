package ServiceLayer.DataSource;

import java.util.List;

import BusinessLayer.UserModule.User;

public interface UserDAO
{
	public int countUsers();

	public boolean addUser(User user) throws Exception;

	public boolean updateUser(User user) throws Exception;

	public boolean deleteUser(User user) throws Exception;

	public User getUser(String userName);

	public List<User> getAllUsers();
}
