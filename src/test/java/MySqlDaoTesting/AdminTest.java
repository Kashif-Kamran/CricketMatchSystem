package MySqlDaoTesting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import BusinessLayer.UserModule.MatchManagementSystem;

public class AdminTest
{

	@Test
	public void Test_1_LoginUser()
	{
		MatchManagementSystem system = new MatchManagementSystem();
		boolean check = system.loginScorer("root", "root");
		assertTrue(check);
		assertTrue(system.getLogedInScorer().getUserName().compareTo("root") == 0);
		assertTrue(system.getLogedInScorer().getPassword().compareTo("root") == 0);
	}

	@Test
	public void Test_2_LogoutScorer()
	{
		MatchManagementSystem system = new MatchManagementSystem();
		system.loginScorer("root", "root");
		boolean check = system.logoutScorer();
		assertTrue(check);
		assertFalse(system.logoutScorer());
	}
}
