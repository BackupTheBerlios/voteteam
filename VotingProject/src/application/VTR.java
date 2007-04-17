/**
 * 
 */
package application;

import ihmMAV.Authentification;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import votingApplication.VTRHelper;
import votingApplication.VotingMachine;
import votingApplication.VotingMachineHelper;

/**
 * @author conde
 *
 */
public class VTR
{

    /**
     * @param args
     */
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
            
            //VTR
            NameComponent vtr = new NameComponent("vtr", "");
            NameComponent vtrPath[] = {vtr};
            votingApplication.VTR vtrRef = VTRHelper.narrow(ncRef.resolve(vtrPath));
            
           //VTR FRAME
            
            
        } catch (Exception e) 
        {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }

    }

}
