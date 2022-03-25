package ServiceLayer.DataSource.MySqlDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

import BusinessLayer.UserModule.Admin;
import ServiceLayer.DataSource.AdminDAO;

public class MySqlAdminDao implements AdminDAO
{

	@Override
	public int countAdmin()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean addAdmin(Admin admin) throws Exception
	{
		boolean check = false;
		MysqlDAOFactory factory = MysqlDAOFactory.getInstance();
		Connection connect = factory.getConnection();
		PreparedStatement prepState = null;
		String query="INSERT INTO admin VALUES (?,?,?,?,?,?)";
		try
		{
			prepState = connect.prepareStatement(query);
			prepState.setString(1, admin.getUserName());
			prepState.setString(2, admin.getPassword());
			prepState.setString(3, admin.getFirstName());
			prepState.setString(4, admin.getLastName());
			prepState.setString(5, admin.getEmailId());
			prepState.setString(6, admin.getCnic());
			prepState.executeUpdate();

		} catch (SQLException exc)
		{
			throw new SQLDataException("Error In Insert Admin : " + exc.getMessage());
		} finally
		{
			factory.freeConnection(connect);
		}
		return check;
	}

	@Override
	public boolean deleteAdmin(Admin admin) throws SQLDataException
	{
		boolean check = false;
		MysqlDAOFactory factory = MysqlDAOFactory.getInstance();
		Connection connect = factory.getConnection();
		String query = "DELETE FROM admin WHERE user_name=?";
		PreparedStatement prepState = null;
		try
		{
			prepState = connect.prepareStatement(query);
			prepState.setString(1, admin.getUserName());
			prepState.executeUpdate();
			check = true;

		} catch (SQLException exc)
		{
			throw new SQLDataException("Unable to Delete the Admin : " + exc.getMessage());
		}
		finally
		{
			factory.freeConnection(connect);
		}
		return check;
	}
	@Override
	public boolean updateAdmin(Admin admin)
	{
		boolean check = false;
		MysqlDAOFactory factory = MysqlDAOFactory.getInstance();
		Connection connect = factory.getConnection();
		PreparedStatement statement=null;
		String query = "Update admin Set password=?, first_name=?, last_name=?, cnic=?, email_id=? WHERE user_name=? ";
		try {
			statement = connect.prepareStatement(query);
			statement.setString(1, admin.getPassword());
			statement.setString(2, admin.getFirstName());
			statement.setString(3, admin.getLastName());
			statement.setString(4, admin.getCnic());
			statement.setString(5, admin.getEmailId());
			statement.setString(6, admin.getUserName());
			statement.executeUpdate();
			System.out.println("Update Admin Code Complition");
			check = true;
		} catch (SQLException exc)
		{
			System.out.println("Error : " + exc.getMessage());
		}finally {
			factory.freeConnection(connect);
		}
		return check;
	}

	@Override
	public Admin getAdmin(String userName)
	{

		MysqlDAOFactory daoFactory = MysqlDAOFactory.getInstance();
		Connection connect = daoFactory.getConnection();
		PreparedStatement prepState = null;
		Admin admin = null;
		String query = "SELECT * FROM admin WHERE user_name=?";
		try
		{
			prepState = connect.prepareStatement(query);
			prepState.setString(1, userName);
			ResultSet result = prepState.executeQuery();
			if (result.next() == false)
			{
				System.out.println("admin Not Found");
				admin = null;
			}
			else
			{	
				admin=new Admin();
				admin.setUserName(result.getString("user_name"));
				admin.setPassword(result.getString("password"));
				admin.setFirstName(result.getString("first_name"));
				admin.setLastName(result.getString("last_name"));
				admin.setCnic(result.getString("cnic"));
				admin.setEmailId(result.getString("email_id"));
			}
		} catch (SQLException exc)
		{
			System.out.println("Error While Getting Admin ");
		} finally
		{
			daoFactory.freeConnection(connect);
		}
		return admin;
	}

}
