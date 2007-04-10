/********************************************************************************
 * VotingMachineImpl
 * Auteur : Kassem Badaoui
 * Date : 30/03/2006
 * desc : Implémentation de la classe VotingMachine
 ********************************************************************************/
package servicesImpl;

import votingApplication.Candidate;
import votingApplication.Voter;
import votingApplication._VotingMachineImplBase;
import java.sql.*;
import java.util.ArrayList;

public class VotingMachineImpl extends _VotingMachineImplBase
{
    private String canton = "";
    private short department = 0;
    private String district = "";
    private String office = "";
    private String serial = "";
    
    public String canton()
    {
	return canton;
    }

    public void canton(String newCanton)
    {
	canton = newCanton;
    }

    public short department()
    {
	return department;
    }

    public void department(short newDepartment)
    {
	department = newDepartment;
    }

    public String district()
    {
	return district;
    }

    public void district(String newDistrict)
    {
	district = newDistrict;
    }

    public String office()
    {
	return office;
    }

    public void office(String newOffice)
    {
	office = newOffice;
    }

    public String serial()
    {
	return serial;
    }

    public void serial(String newSerial)
    {
	serial = newSerial;
    }

    public boolean checkAccess(Voter voter)
    {
	try
	{
	    boolean access = false;
	    
	    //connection a la base de donnée
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    String dbName = "votingdb";
	    String url = "jdbc:odbc:LocalServer;DSN=" + dbName;
	    
	    Connection con = DriverManager.getConnection(url);
	    
	    String query = "SELECT * FROM VOTER";
	    
	    Statement stm = con.createStatement();
	    ResultSet rs = stm.executeQuery(query);
	    
	    while (rs.next())
	    {
		if (rs.getString("INSEE").equalsIgnoreCase(voter.INSEE()) && rs.getString("password").equalsIgnoreCase(voter.password()))
		{
		    access = true;
		    voter.firstName(rs.getString("firstName"));
		    voter.lastName(rs.getString("lastName"));
		    voter.department(rs.getShort("department"));
		}
	    }
	    
	    rs.close();
	    stm.close();
	    con.close();
	    
	    return access;
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
	return false;
    }

    public Candidate[] getCandidates()
    {
	
	try
	{
	    
	    //connection a la base de donnée
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    String dbName = "votingdb";
	    String url = "jdbc:odbc:LocalServer;DSN=" + dbName;
	    
	    Connection con = DriverManager.getConnection(url);
	   	    
	    String queryCount = "SELECT * FROM CANDIDATE";
	    Statement stmCount = con.createStatement();
	    ResultSet rsCount = stmCount.executeQuery(queryCount);
	    int count = 0;
	    while(rsCount.next()){count++;}
	    rsCount.close();
	    stmCount.close();
	    
	    Candidate[] candidates = new Candidate[count];
	    
	    String query = "SELECT * FROM CANDIDATE ORDER BY numCandidate";
	    Statement stm = con.createStatement();
	    ResultSet rs = stm.executeQuery(query);

	    int i=0;
	    while (rs.next())
	    {
		Candidate aCandidate = new Candidate(
			rs.getString("numCandidate"),
			rs.getString("firstName"),
			rs.getString("lastName"),
			rs.getShort("age"),
			rs.getString("profession"),
			rs.getString("mandates"),
			rs.getString("description")
			);

		candidates[i] = aCandidate;
		i++;
	    }
	    
	    rs.close();
	    stm.close();
	    con.close();
	    
	    return candidates.clone();
	    
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	return null;
    }

    public String checkRightToVote(String insee)
    {
	try
	{
	    String message = "M_OK";
		
	    //on vérifie d'abord si l'électeur a déja voté
		
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    String dbName = "votingdb";
	    String url = "jdbc:odbc:LocalServer;DSN=" + dbName;
	    
	    Connection con = DriverManager.getConnection(url);
	   	    
	    String query = "SELECT * FROM VOTE WHERE INSEE='" + insee + "'";
	    Statement stm = con.createStatement();
	    ResultSet rs = stm.executeQuery(query);
	    
	    if (rs.next())
	    {
		message = "M_VOTED";
	    }
	    
	    rs.close();
	    stm.close();
		
	    //si l'électeur n'a pas encore voté
	    //on vérifie s'il a droit de voter sur ce département
	    
	    if (message.equalsIgnoreCase("M_OK"))
	    {
		    String queryDpt = "SELECT * FROM VOTER WHERE INSEE='" + insee + "'";
		    Statement stmDpt = con.createStatement();
		    ResultSet rsDpt = stmDpt.executeQuery(queryDpt);
		    
		    if (rsDpt.next())
		    {
			if (rsDpt.getShort("department") != department)
			{
			    message = "M_NOT_ALLOW";
			}
		    }
		    
		    rsDpt.close();
		    stmDpt.close();
	    }
	    
	    con.close();
	    return message;

	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
	return null;
    }

    public void initMAV()
    {
	try
	{
	    //connection a la base de donnée
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    String dbName = "votingdb";
	    String url = "jdbc:odbc:LocalServer;DSN=" + dbName;
	    
	    Connection con = DriverManager.getConnection(url);
	    
	    String query = "SELECT * FROM VotingMachine WHERE serial='"+ serial +"'";
	    
	    Statement stm = con.createStatement();
	    ResultSet rs = stm.executeQuery(query);
	    
	    if (rs.next())
	    {
		canton = rs.getString("canton");
		department = rs.getShort("department");
		office = rs.getString("office");
		district = rs.getString("district");
	    }
	    
	    rs.close();
	    stm.close();
	    con.close();
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }

}
