package ServiceLayer.DataSource;

import ServiceLayer.DataSource.MySqlDaoImpl.MysqlDAOFactory;

public abstract class DAOFactory
{
	private static final int MySQLDataSource = 1;
	public abstract UserDAO getUserDao();

	public static DAOFactory getDataServiceInstance(int whichFactory)
	{
		if (whichFactory == 1)
		{
			return MysqlDAOFactory.getInstance();
		}
		else
		{
			System.out.println("Error in Getting DataBase Factory");
			return null;
		}
	}
}