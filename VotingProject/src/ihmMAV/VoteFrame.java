package ihmMAV;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import votingApplication.Candidate;
import votingApplication.Voter;
import votingApplication.VotingMachine;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class VoteFrame extends JFrame
{

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JPanel voterPanel = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JLabel lblINSEE = null;
    private JLabel lblName = null;
    private JLabel lblDpt = null;

    private VotingMachine machineRef = null;
    private Voter voterRef = null;
    private JPanel machinePanel = null;
    private JLabel jLabel3 = null;
    private JLabel jLabel4 = null;
    private JLabel jLabel5 = null;
    private JLabel lblCanton = null;
    private JLabel lblDistrict = null;
    private JLabel lblDptM = null;
    private JPanel candidatesPanel = null;
    private JLabel lblMess = null;
    private JButton bntVote = null;
    private JButton bntDetails = null;
    private JPanel detailPanel = null;

    private Candidate[] candidates = null; //  @jve:decl-index=0:

    private ArrayList radioCdts = null; //  @jve:decl-index=0:
    private JLabel jLabel6 = null;
    private JLabel jLabel7 = null;
    private JLabel jLabel8 = null;
    private JLabel jLabel9 = null;
    private JLabel jLabel10 = null;
    private JLabel lblCName = null;
    private JLabel lblCFirstName = null;
    private JLabel lblCAge = null;
    private JLabel lblCProfession = null;
    private JLabel lblCMandates = null;
    
    private Boolean showDetail = false;
    private String message = "";

    /**
     * This is the default constructor
     */
    public VoteFrame(VotingMachine _machineRef, Voter _voterRef)
    {
	super();
	machineRef = _machineRef;
	voterRef = _voterRef;
	initialize();

    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize()
    {
	this.setSize(611, 419);
	this.setContentPane(getJContentPane());
	this.setTitle("Vote application");
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane()
    {
	if (jContentPane == null)
	{
	    
	    lblMess = new JLabel();
	    lblMess.setBounds(new Rectangle(12, 130, 582, 22));
	    lblMess.setText("");
	    jContentPane = new JPanel();
	    jContentPane.setLayout(null);
	    jContentPane.add(getVoterPanel(), null);
	    jContentPane.add(getMachinePanel(), null);
	    jContentPane.add(getCandidatesPanel(), null);
	    
	    message = machineRef.checkRightToVote(voterRef.INSEE());
	    
	    if (message.equalsIgnoreCase("M_OK"))
	    {
		lblMess.setText("INFO : Vous n'avez pas encore voté");
		lblMess.setForeground(Color.blue);
	    }
	    else if (message.equalsIgnoreCase("M_VOTED"))
	    {
		lblMess.setText("INFO : Vous avez déja voté");
		lblMess.setForeground(Color.red);
		bntVote.setEnabled(false);
	    }
	    else if (message.equalsIgnoreCase("M_NOT_ALLOW"))
	    {
		lblMess.setText("INFO : Vous n'avez pas le droit de voter dans ce département");
		lblMess.setForeground(Color.red);
		bntVote.setEnabled(false);
	    }
	    
	    jContentPane.add(lblMess, null);
	    jContentPane.add(getDetailPanel(), null);
	    
	    
	}
	return jContentPane;
    }

    /**
     * This method initializes voterPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getVoterPanel()
    {
	if (voterPanel == null)
	{
	    lblDpt = new JLabel();
	    lblDpt.setBounds(new Rectangle(118, 77, 146, 23));
	    lblDpt.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblDpt.setText(String.valueOf(voterRef.department()));
	    lblName = new JLabel();
	    lblName.setBounds(new Rectangle(118, 50, 146, 21));
	    lblName.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblName.setText(voterRef.firstName() + " " + voterRef.lastName());
	    lblINSEE = new JLabel();
	    lblINSEE.setBounds(new Rectangle(118, 26, 146, 18));
	    lblINSEE.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblINSEE.setText(voterRef.INSEE());
	    jLabel2 = new JLabel();
	    jLabel2.setBounds(new Rectangle(13, 77, 90, 23));
	    jLabel2.setText("Département :");
	    jLabel1 = new JLabel();
	    jLabel1.setBounds(new Rectangle(13, 50, 61, 21));
	    jLabel1.setText("Name :");
	    jLabel = new JLabel();
	    jLabel.setBounds(new Rectangle(13, 26, 54, 18));
	    jLabel.setText("INSEE :");
	    voterPanel = new JPanel();
	    voterPanel.setLayout(null);
	    voterPanel.setBounds(new Rectangle(12, 14, 279, 112));
	    voterPanel.setBorder(BorderFactory.createLineBorder(new Color(204,
		    204, 204), 2));
	    voterPanel.setBorder(BorderFactory.createTitledBorder("Electeur"));
	    voterPanel.add(jLabel, null);
	    voterPanel.add(jLabel1, null);
	    voterPanel.add(jLabel2, null);
	    voterPanel.add(lblINSEE, null);
	    voterPanel.add(lblName, null);
	    voterPanel.add(lblDpt, null);
	}
	return voterPanel;
    }

    /**
     * This method initializes machinePanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getMachinePanel()
    {
	if (machinePanel == null)
	{
	    lblDptM = new JLabel();
	    lblDptM.setBounds(new Rectangle(125, 77, 145, 23));
	    lblDptM.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblDptM.setText(String.valueOf(machineRef.department()));
	    lblDistrict = new JLabel();
	    lblDistrict.setBounds(new Rectangle(125, 50, 144, 21));
	    lblDistrict.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblDistrict.setText(machineRef.district());
	    lblCanton = new JLabel();
	    lblCanton.setBounds(new Rectangle(125, 26, 141, 18));
	    lblCanton.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblCanton.setText(machineRef.canton());
	    jLabel5 = new JLabel();
	    jLabel5.setBounds(new Rectangle(13, 77, 98, 23));
	    jLabel5.setText("Département :");
	    jLabel4 = new JLabel();
	    jLabel4.setBounds(new Rectangle(13, 50, 97, 21));
	    jLabel4.setText("Circonscription :");
	    jLabel3 = new JLabel();
	    jLabel3.setBounds(new Rectangle(13, 26, 97, 18));
	    jLabel3.setText("Canton :");
	    machinePanel = new JPanel();
	    machinePanel.setLayout(null);
	    machinePanel.setBounds(new Rectangle(294, 14, 302, 112));
	    machinePanel.setBorder(BorderFactory.createLineBorder(new Color(
		    204, 204, 204), 2));
	    machinePanel.setBorder(BorderFactory
		    .createTitledBorder("Lieu de vote"));
	    machinePanel.add(jLabel3, null);
	    machinePanel.add(jLabel4, null);
	    machinePanel.add(jLabel5, null);
	    machinePanel.add(lblCanton, null);
	    machinePanel.add(lblDistrict, null);
	    machinePanel.add(lblDptM, null);
	}
	return machinePanel;
    }

    /**
     * This method initializes candidatesPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getCandidatesPanel()
    {
	if (candidatesPanel == null)
	{
	    candidates = machineRef.getCandidates();
	    radioCdts = new ArrayList();

	    candidatesPanel = new JPanel();
	    candidatesPanel.setLayout(null);
	    candidatesPanel.setBounds(new Rectangle(11, 162, 277, 197));
	    candidatesPanel.setBorder(BorderFactory.createLineBorder(new Color(
		    204, 204, 204), 2));
	    candidatesPanel.setBorder(BorderFactory
		    .createTitledBorder("Liste des candidats"));

	    ButtonGroup grp = new ButtonGroup();

	    int h = 20;

	    for (int i = 0; i < candidates.length; i++)
	    {
		JRadioButton radio = new JRadioButton();
		radio.setBounds(new Rectangle(13, h, 147, 21));
		radio.setText(candidates[i].firstName + " "
			+ candidates[i].lastName);
		radio.setName(candidates[i].candidateNumber);
		h = h + 30;

		grp.add(radio);
		candidatesPanel.add(radio, null);
		radioCdts.add(radio);
	    }

	    candidatesPanel.add(getBntVote(), null);
	    candidatesPanel.add(getBntDetails(), null);
	}
	return candidatesPanel;
    }

    /**
     * This method initializes bntVote
     * 
     * @return javax.swing.JButton
     */
    private JButton getBntVote()
    {
	if (bntVote == null)
	{
	    bntVote = new JButton();
	    bntVote.setBounds(new Rectangle(11, 166, 85, 24));
	    bntVote.setText("Voter");

	    bntVote.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
		    JRadioButton radio = null;

		    for (int i = 0; i < radioCdts.size(); i++)
		    {
			radio = (JRadioButton) radioCdts.get(i);
			if (radio.isSelected())
			{
			    voterRef.registerVote(candidates[i].candidateNumber, machineRef.serial());
			    bntVote.setEnabled(false);
			    lblMess.setText("Vote enregistré");
			}
		    }
		}
	    }
		    );
	}
	return bntVote;
    }

    /**
     * This method initializes bntDetails
     * 
     * @return javax.swing.JButton
     */
    private JButton getBntDetails()
    {
	if (bntDetails == null)
	{
	    bntDetails = new JButton();
	    bntDetails.setBounds(new Rectangle(102, 166, 84, 25));
	    bntDetails.setText("Détail");
	    bntDetails.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
		    JRadioButton radio = null;

		    for (int i = 0; i < radioCdts.size(); i++)
		    {
			radio = (JRadioButton) radioCdts.get(i);
			if (radio.isSelected() && !candidates[i].candidateNumber.equalsIgnoreCase("numBlanc"))
			{
			    lblCMandates.setText(candidates[i].mandates);
			    lblCProfession.setText(candidates[i].profession);
			    lblCAge.setText(String.valueOf(candidates[i].age));
			    lblCFirstName.setText(candidates[i].firstName);
			    lblCName.setText(candidates[i].lastName);
			    detailPanel.setVisible(true);
			}
		    }
		}
	    }
		    );
	    
	}
	return bntDetails;
    }

    /**
     * This method initializes detailPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getDetailPanel()
    {
	if (detailPanel == null)
	{
	    lblCMandates = new JLabel();
	    lblCMandates.setBounds(new Rectangle(105, 125, 162, 18));
	    lblCMandates.setText("");
	    lblCMandates.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblCProfession = new JLabel();
	    lblCProfession.setBounds(new Rectangle(105, 100, 162, 18));
	    lblCProfession.setText("");
	    lblCProfession.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblCAge = new JLabel();
	    lblCAge.setBounds(new Rectangle(105, 75, 162, 18));
	    lblCAge.setText("");
	    lblCAge.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblCFirstName = new JLabel();
	    lblCFirstName.setBounds(new Rectangle(105, 50, 162, 18));
	    lblCFirstName.setText("");
	    lblCFirstName.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lblCName = new JLabel();
	    lblCName.setBounds(new Rectangle(105, 25, 162, 18));
	    lblCName.setText("");
	    lblCName.setFont(new Font("Dialog", Font.PLAIN, 12));
	    jLabel10 = new JLabel();
	    jLabel10.setBounds(new Rectangle(13, 125, 80, 18));
	    jLabel10.setText("Mandats :");
	    jLabel9 = new JLabel();
	    jLabel9.setBounds(new Rectangle(13, 100, 80, 18));
	    jLabel9.setText("Profession :");
	    jLabel8 = new JLabel();
	    jLabel8.setBounds(new Rectangle(13, 75, 80, 18));
	    jLabel8.setText("Age :");
	    jLabel7 = new JLabel();
	    jLabel7.setBounds(new Rectangle(13, 50, 80, 18));
	    jLabel7.setText("Prénom :");
	    jLabel6 = new JLabel();
	    jLabel6.setBounds(new Rectangle(13, 25, 80, 18));
	    jLabel6.setText("Nom :");
	    detailPanel = new JPanel();
	    detailPanel.setLayout(null);
	    detailPanel.setBounds(new Rectangle(295, 162, 300, 197));
	    detailPanel.setBorder(BorderFactory.createLineBorder(new Color(204,
		    204, 204), 2));
	    detailPanel.add(jLabel6, null);
	    detailPanel.add(jLabel7, null);
	    detailPanel.add(jLabel8, null);
	    detailPanel.add(jLabel9, null);
	    detailPanel.add(jLabel10, null);
	    detailPanel.add(lblCName, null);
	    detailPanel.add(lblCFirstName, null);
	    detailPanel.add(lblCAge, null);
	    detailPanel.add(lblCProfession, null);
	    detailPanel.add(lblCMandates, null);
	    detailPanel.setBorder(BorderFactory.createTitledBorder("Detail"));
	    detailPanel.setVisible(false);
	}
	return detailPanel;
    }

  

} // @jve:decl-index=0:visual-constraint="10,10"
