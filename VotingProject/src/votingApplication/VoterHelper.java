package votingApplication;


/**
* votingApplication/VoterHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from votingApplication.idl
* lundi 9 avril 2007 23 h 37 CEST
*/

abstract public class VoterHelper
{
  private static String  _id = "IDL:votingApplication/Voter:1.0";

  public static void insert (org.omg.CORBA.Any a, votingApplication.Voter that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static votingApplication.Voter extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (votingApplication.VoterHelper.id (), "Voter");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static votingApplication.Voter read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_VoterStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, votingApplication.Voter value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static votingApplication.Voter narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof votingApplication.Voter)
      return (votingApplication.Voter)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      votingApplication._VoterStub stub = new votingApplication._VoterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static votingApplication.Voter unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof votingApplication.Voter)
      return (votingApplication.Voter)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      votingApplication._VoterStub stub = new votingApplication._VoterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}