/********************************************************************************
 * SRV
 * Auteur : Kassem Badaoui
 * Date : 30/03/2006
 * desc : Implémentation du serveur
 ********************************************************************************/

package application;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import servicesImpl.VoterImpl;
import servicesImpl.VotingMachineImpl;

public class SRV
{

	public static void main(String[] args)
	{
		try
		{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// create servant and register it with the ORB
			VoterImpl voterRef = new VoterImpl();
			orb.connect(voterRef);
			
			VotingMachineImpl machineRef = new VotingMachineImpl();
			orb.connect(machineRef);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// bind the Object Reference in Naming
			NameComponent voter = new NameComponent("Voter", "");
			NameComponent voterPath[] = { voter };
			ncRef.rebind(voterPath, voterRef);
			
			//bind the Object Reference in Naming
			NameComponent machine = new NameComponent("VotingMachine", "");
			NameComponent machinePath[] = { machine };
			ncRef.rebind(machinePath, machineRef);
			
			System.out.println("serveur prêt...");
			
			// wait for invocations from clients
			java.lang.Object sync = new java.lang.Object();
			synchronized (sync)
			{
				sync.wait();
			}

		} catch (Exception e)
		{
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);

		}
	}

}
