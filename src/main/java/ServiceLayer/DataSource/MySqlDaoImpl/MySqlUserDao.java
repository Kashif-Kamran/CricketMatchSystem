package ServiceLayer.DataSource.MySqlDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import BusinessLayer.UserModule.User;
import ServiceLayer.DataSource.UserDAO;

public class MySqlUserDao implements UserDAO
{

	@Override
	public boolean addUser(User user)
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
		return totalUsers;
	}
	@Override
	public boolean updateUser(User user)
	{
//		MysqlDAOFactory daoFactory = MysqlDAOFactory.getInstance();
//		Connection connect = daoFactory.getConnection();
//		PreparedStatement statement = null;
//		String query=""

		return false;
	}

	@Override
	public boolean deleteUser(User user)
	{
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<User> getAllUsers()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getUser(String userName)
	{
		return false;
	}

}
