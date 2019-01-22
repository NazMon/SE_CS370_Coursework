package edu.qc.seclass.replace;

import java.io.*;

public class InvalidUtilityCallException extends Exception
{
  private String str;
  public InvalidUtilityCallException(String str)
  {
     this.str = str;
  }
  public String getString()
  {
     return str;
  }
}
