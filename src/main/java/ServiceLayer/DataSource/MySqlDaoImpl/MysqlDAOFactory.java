package ServiceLayer.DataSource.MySqlDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import ServiceLayer.DataSource.DAOFactory;
import ServiceLayer.DataSource.UserDAO;

// Singleton Pattern with Implemented
public class MysqlDAOFactory extends DAOFactory
{
	String url = "jdbc:mysql://localhost:3306/cricket_ms";
	String uName = "root";
	String password = "4regfast@01";
	// Other Private members
	private static MysqlDAOFactory mysqlDAOFactory; // for SingleTonMethod
	private static MysqlConnectionPoolDataSource dataSource;
	private static DatabaseMetaData dbMetaData;

	private MysqlDAOFactory()
	{
		try
		{
		dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setURL(url);
		dataSource.setUser(uName);
		dataSource.setPassword(password);
	} catch (Exception e)
	{
		System.out.println("Error MySqlDAOFactory");
		e.printStackTrace();
		dataSource = null;
	}
	}

	public Connection getConnection()
	{
		try
		{
			Connection c = dataSource.getConnection();
			dbMetaData = (DatabaseMetaData) c.getMetaData();
			System.out.println("1. Connection Establishing :" + dbMetaData.getDatabaseProductName() + " --> Passed");
			return c;
		} catch (SQLException e)
		{
			System.out.println("1. Connection Establish: --> Failed");
			e.printStackTrace();
			return null;
		}

	}

	public void freeConnection(Connection c)
	{
		try
		{
			c.close();
			System.out.println("3. Connection Freed: " + dbMetaData.getDatabaseProductName() + " --> Passed");
		} catch (SQLException e)
		{
			System.out.println("3. Connection Establish: --> Failed");
			e.printStackTrace();
		}
	}

	public static MysqlDAOFactory getInstance()
	{
		if (mysqlDAOFactory == null)
		{
			mysqlDAOFactory = new MysqlDAOFactory();
		}
		return mysqlDAOFactory;
	}

	@Override
	public UserDAO getUserDao()
	{
		// TODO Auto-generated method stub
		return new MySqlUserDao();
	}

}
