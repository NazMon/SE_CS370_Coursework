package edu.qc.seclass.replace;

import java.io.*;

public class TooManyOptsException extends Exception
{
  private String str;
  public TooManyOptsException(String str)
  {
     this.str = str;
  }
  public String getString()
  {
     return str;
  }
}
