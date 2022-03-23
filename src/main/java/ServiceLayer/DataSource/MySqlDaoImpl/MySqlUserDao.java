package ServiceLayer.DataSource.MySqlDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BusinessLayer.UserModule.User;
import ServiceLayer.DataSource.UserDAO;

public class MySqlUserDao implements UserDAO
{

	@Override
	public boolean addUser(User user) throws Exception
	{
		boolean check;
		MysqlDAOFactory daoFactory = MysqlDAOFactory.getInstance();
		Connection connect = daoFactory.getConnection();
		PreparedStatement prepState = null;
		String query = "INSERT INTO users VALUES (?,?,?,?,?,?)";
		try {
			prepState = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prepState.setString(1,user.getUserName());
			prepState.setString(2, user.getPassword());
			prepState.setString(3, user.getFirstName());
			prepState.setString(4, user.getLastName());
			prepState.setString(5, user.getCnic());
			prepState.setInt(6, user.getAge());
			prepState.executeUpdate();
			check = true;
		} catch (SQLIntegrityConstraintViolationException exc)
		{
			throw new IllegalArgumentException(
					"User with userName : " + user.getUserName() + " is Already Present Choose New User Name ");

		} catch (SQLException exc)
		{
			System.out.println("Exception while Insertion " + exc);
			check = false;
		}
		finally
		{
			daoFactory.freeConnection(connect);
		}
		return check;
	}
	@Override
	public int countUsers()
	{
		MysqlDAOFactory daoFactory = MysqlDAOFactory.getInstance();
		Connection connect = daoFactory.getConnection();
		int totalUsers = -1;
		ResultSet reSet = null;
		String quary = "SELECT COUNT(*) FROM users";
		try
		{
			Statement state = connect.createStatement();
			reSet = state.executeQuery(quary);
			reSet.next();
			totalUsers = reSet.getInt(1);
		} catch (SQLException exc)
		{
			System.out.println("Error in Count User Function " + exc);
		}
		finally
		{
			daoFactory.freeConnection(connect);
		}
		return totalUsers;
	}
	@Override
	public User getUser(String userName)
	{
		MysqlDAOFactory daoFactory = MysqlDAOFactory.getInstance();
		Connection connect = daoFactory.getConnection();
		PreparedStatement prepState = null;
		User user;
		String query = "SELECT * FROM users WHERE user_name=?";
		try
		{
			prepState = connect.prepareStatement(query);
			prepState.setString(1, userName);
			ResultSet result = prepState.executeQuery();
			if (result.next() == false)
			{
				System.out.println("Result Set is empty");
				user = null;
			}
			else
			{
				user = new User();
				user.setUserName(result.getString("user_name"));
				user.setPassword(result.getString("password"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setCnic(result.getString("cnic"));
				user.setAge(result.getInt("age"));
			}
		} catch (SQLException exc)
		{
			System.out.println("Getting Error While Getting User"+exc);
			user = null;
		} finally
		{
			daoFactory.freeConnection(connect);
		}

		return user;
	}
	@Override
	public boolean updateUser(User user) throws Exception
	{
		boolean check = false;
		MysqlDAOFactory factory = MysqlDAOFactory.getInstance();
		Connection connect = factory.getConnection();
		PreparedStatement statement = null;
		String query = "UPDATE users Set password=? , first_name=?, last_name=?, cnic=?, age=? WHERE user_name=?";
		try
		{
			statement = connect.prepareStatement(query);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getCnic());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getUserName());
			statement.executeUpdate();
			System.out.println("Update User Code Complition");
			check = true;
		} catch (SQLException exc)
		{
			throw new SQLDataException("Unable to Update User : " + exc.getMessage());
		}
		finally
		{
			factory.freeConnection(connect);
		}

		return check;
	}
	@Override
	public boolean deleteUser(User user) throws Exception
	{
		boolean check = false;
		MysqlDAOFactory factory = MysqlDAOFactory.getInstance();
		Connection connect = factory.getConnection();
		String query = "DELETE FROM users WHERE user_name=?";
		PreparedStatement prepState = null;
		try
		{
			prepState = connect.prepareStatement(query);
			System.out.println("UserName : " + user.getUserName());
			prepState.setString(1, user.getUserName());
			prepState.executeUpdate();
			System.out.println("Deletion Program Complete");
			check = true;
		} catch (SQLException exc)
		{
			throw new SQLDataException("Unable to Delete the User : " + exc.getMessage());
		}
		finally {
			factory.freeConnection(connect);
		}
		return check;
	}
	@Override
	public List<User> getAllUsers()
	{
		MysqlDAOFactory factory = MysqlDAOFactory.getInstance();
		Connection connect = factory.getConnection();
		List<User> userList = null;
		PreparedStatement statement = null;
		String query = "SELECT * FROM users";
		try {
			statement = connect.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			int i = 0;
			userList = new ArrayList<User>();
			while (result.next())
			{
				User tempUser = new User();
				tempUser.setUserName(result.getString("user_name"));
				tempUser.setPassword(result.getString("password"));
				tempUser.setFirstName(result.getString("first_name"));
				tempUser.setLastName(result.getString("last_name"));
				tempUser.setCnic(result.getString("cnic"));
				tempUser.setAge(result.getInt("age"));
				userList.add(tempUser);
			}
		} catch (SQLException exc)
		{
			System.out.println("Error in GetAllUser : " + exc);
		}
		finally
		{
			factory.freeConnection(connect);
		}
		return userList;
	}

}
