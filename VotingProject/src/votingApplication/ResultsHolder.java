package votingApplication;


/**
* votingApplication/ResultsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from VotingApplication.idl
* mardi 17 avril 2007 18 h 27 CEST
*/

public final class ResultsHolder implements org.omg.CORBA.portable.Streamable
{
  public votingApplication.ResultData value[] = null;

  public ResultsHolder ()
  {
  }

  public ResultsHolder (votingApplication.ResultData[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = votingApplication.ResultsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    votingApplication.ResultsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return votingApplication.ResultsHelper.type ();
  }

}
