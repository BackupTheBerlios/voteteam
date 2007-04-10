/********************************************************************************
 * MAV
 * Auteur : Kassem Badaoui
 * Date : 30/03/2006
 * desc : Implémentation du client
 ********************************************************************************/

package application;

import ihmMAV.Authentification;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import votingApplication.Voter;
import votingApplication.VoterHelper;
import votingApplication.VotingMachine;
import votingApplication.VotingMachineHelper;

public class MAV
{

    public static void main(String[] args)
    {
	try
	{
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            
            //MAV
            NameComponent machine = new NameComponent("VotingMachine", "");
            NameComponent machinePath[] = {machine};
            VotingMachine machineRef = VotingMachineHelper.narrow(ncRef.resolve(machinePath));
            
            machineRef.serial("m01");
            machineRef.initMAV();
            
            Authentification auth = new Authentification(args, machineRef);
            
            
        } catch (Exception e) 
        {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }


}


