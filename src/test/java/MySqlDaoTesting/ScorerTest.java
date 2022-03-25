package MySqlDaoTesting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import BusinessLayer.UserModule.MatchManagementSystem;

public class ScorerTest
{
	@Test
	public void Test_1_LoginAdmin()
	{
		MatchManagementSystem system = new MatchManagementSystem();
		boolean check = system.loginAdmin("root", "root0101");
		assertTrue(check);
		assertTrue(system.getLogedInAdmin().getUserName().compareTo("root")==0);
		assertTrue(system.getLogedInAdmin().getPassword().compareTo("root0101") == 0);
	}

	@Test
	public void Test_2_LogoutAdmin()
	{
		MatchManagementSystem system = new MatchManagementSystem();
		system.loginAdmin("root", "root0101");
		boolean check = system.logoutAdmin();
		assertTrue(check);
		assertFalse(system.logoutAdmin());
		}


}
