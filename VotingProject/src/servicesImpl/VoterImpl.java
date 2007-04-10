/********************************************************************************
 * VoterImpl
 * Auteur : Kassem Badaoui
 * Date : 30/03/2006
 * desc : Implémentation de la classe Voter
 ********************************************************************************/

package servicesImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import votingApplication._VoterImplBase;

public class VoterImpl extends _VoterImplBase
{
    private String INSEE = "";
    private String firstName = "";
    private String lastName = "";
    private short department = 0;
    private String password = "";

    public String INSEE()
    {
	return INSEE;
    }

    public void INSEE(String newINSEE)
    {
	INSEE = newINSEE;
    }

    public short department()
    {
	return department;
    }

    public void department(short newDepartment)
    {
	department = newDepartment;
    }

    public String firstName()
    {
	return firstName;
    }

    public void firstName(String newFirstName)
    {
	firstName = newFirstName;
    }

    public String lastName()
    {
	return lastName;
    }

    public void lastName(String newLastName)
    {
	lastName = newLastName;
    }

    public String password()
    {
	return password;
    }

    public void password(String newPassword)
    {
	password = newPassword;
    }

    public void registerVote(String aCandidate, String aMachine) 
    {
	try
	{
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    String dbName = "votingdb";
	    String url = "jdbc:odbc:LocalServer;DSN=" + dbName;
	    
	    Connection con = DriverManager.getConnection(url);
	    
	    PreparedStatement pstm = con.prepareStatement("INSERT INTO Vote(INSEE,numCandidate, serial) Values(?,?,?)");
	    pstm.setString(1, INSEE);
	    pstm.setString(2, aCandidate);
	    pstm.setString(3, aMachine);
	    
	    pstm.executeUpdate();
	    pstm.close();
	   
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }

}
