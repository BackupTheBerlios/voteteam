/********************************************************************************
 * Authentification
 * Auteur : Kassem Badaoui
 * Date : 30/03/2006
 * desc : Fenêtre pour se connecter a la MAV
 ********************************************************************************/

package ihmMAV;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import votingApplication.Voter;
import votingApplication.VoterHelper;
import votingApplication.VotingMachine;

public class Authentification implements ActionListener

{
    private JFrame frameLogin;
    private Container containerLogin;
    JLabel lblUsername;
    JLabel lblPasswd;
    JLabel lblEtat;
    JTextField txtUsername;
    JPasswordField txtPasswd;
    JButton boutonLogin;
    String[] args;
    VotingMachine machineRef;

    public Authentification(String[] args, VotingMachine _machineRef)
    {
	frameLogin = new JFrame();
	containerLogin = frameLogin.getContentPane();
	containerLogin.setLayout(null);
	lblUsername = new JLabel("Login: ");
	lblPasswd = new JLabel("Pass: ");
	lblEtat = new JLabel("");
	lblEtat.setForeground(Color.red);
	txtUsername = new JTextField();
	txtPasswd = new JPasswordField();
	boutonLogin = new JButton("Login");
	boutonLogin.setActionCommand("Connexion");
	boutonLogin.addActionListener(this);
	machineRef = _machineRef;
	

	ajouterComposant(containerLogin, lblUsername, 10, 10, 100, 30);
	ajouterComposant(containerLogin, txtUsername, 120, 12, 200, 30);
	ajouterComposant(containerLogin, lblPasswd, 10, 40, 100, 30);
	ajouterComposant(containerLogin, txtPasswd, 120, 42, 200, 30);
	ajouterComposant(containerLogin, boutonLogin, 120, 80, 200, 40);
	ajouterComposant(containerLogin, lblEtat, 50, 130, 300, 30);

	frameLogin.setTitle("Authentification");
	frameLogin.setSize(350, 190);
	frameLogin.setLocation(350, 200);
	frameLogin.setVisible(true);
	this.args = args;
    }

    public void ajouterComposant(Container c, Component comp, int x, int y, int x1, int y1)
    {
	comp.setBounds(x, y, x1, y1);
	c.add(comp);
    }

    public void actionPerformed(ActionEvent e)
    {
	if (e.getActionCommand().equals("Connexion"))
	{
	    if (txtUsername.getText().equals("")|| txtPasswd.getText().equals(""))
	    {
		this.lblEtat.setText("Veuillez saisir votre login ou mot de passe");
	    } 
	    else
	    {
		try
		{
	            // create and initialize the ORB
	            ORB orb = ORB.init(args, null);
	            
	            // get the root naming context
	            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	            NamingContext ncRef = NamingContextHelper.narrow(objRef);
	            
	            NameComponent voter = new NameComponent("Voter", "");
	            NameComponent voterPath[] = {voter};
	            Voter voterRef = VoterHelper.narrow(ncRef.resolve(voterPath));
	            
	            voterRef.INSEE(txtUsername.getText());
	            voterRef.password(txtPasswd.getText());
	            
	            if (machineRef.checkAccess(voterRef))
	            {
	        	VoteFrame voteFrame = new VoteFrame(machineRef,voterRef);
	        	voteFrame.setVisible(true);
	        	frameLogin.setVisible(false);
	            }
	            else
	            {
	        	this.lblEtat.setText("accés REFUSE");
	            }
	            
		    
		} catch (Exception ex)
		{
		    System.out.println(ex);
		}
	    }
	}
    }
}
