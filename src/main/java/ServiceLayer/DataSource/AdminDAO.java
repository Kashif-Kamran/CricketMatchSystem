package ServiceLayer.DataSource;

import BusinessLayer.UserModule.Admin;

public interface AdminDAO
{
	public int countAdmin();

	public boolean addAdmin(Admin admin) throws Exception; // Done

	public boolean deleteAdmin(Admin admin) throws Exception; // Done

	public boolean updateAdmin(Admin admin) throws Exception; // Done

	public Admin getAdmin(String userName); // Done

}
