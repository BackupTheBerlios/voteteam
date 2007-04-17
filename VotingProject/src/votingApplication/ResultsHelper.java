package votingApplication;


/**
* votingApplication/ResultsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from VotingApplication.idl
* mardi 17 avril 2007 18 h 27 CEST
*/

abstract public class ResultsHelper
{
  private static String  _id = "IDL:votingApplication/Results:1.0";

  public static void insert (org.omg.CORBA.Any a, votingApplication.ResultData[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static votingApplication.ResultData[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = votingApplication.ResultDataHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (votingApplication.ResultsHelper.id (), "Results", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static votingApplication.ResultData[] read (org.omg.CORBA.portable.InputStream istream)
  {
    votingApplication.ResultData value[] = null;
    int _len0 = istream.read_long ();
    value = new votingApplication.ResultData[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = votingApplication.ResultDataHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, votingApplication.ResultData[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      votingApplication.ResultDataHelper.write (ostream, value[_i0]);
  }

}
