package votingApplication;

/**
* votingApplication/VotingMachineHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from votingApplication.idl
* lundi 9 avril 2007 23 h 37 CEST
*/

public final class VotingMachineHolder implements org.omg.CORBA.portable.Streamable
{
  public votingApplication.VotingMachine value = null;

  public VotingMachineHolder ()
  {
  }

  public VotingMachineHolder (votingApplication.VotingMachine initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = votingApplication.VotingMachineHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    votingApplication.VotingMachineHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return votingApplication.VotingMachineHelper.type ();
  }

}
