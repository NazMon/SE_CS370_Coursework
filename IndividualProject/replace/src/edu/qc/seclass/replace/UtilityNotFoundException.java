package edu.qc.seclass.replace;
import java.io.*;

public class UtilityNotFoundException extends Exception
{
  private String str;
  public UtilityNotFoundException(String str)
  {
     this.str = str;
  }
  public String getString()
  {
     return str;
  }
}
