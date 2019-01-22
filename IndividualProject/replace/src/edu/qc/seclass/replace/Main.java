package edu.qc.seclass.replace;
/** @author Nazib Mondal CS370 - Queens College */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/********* Main Class *********/
public class Main {
    /* Member Variables */
    static int DDPos;
    static final String R = "replace";
    static final String DD = "--";
    static String errorMessage = " is not a valid call to the 'replace' utility.";

    /********** Driver Method **********/
    public static void main(String[] args) {
        try {
          //Initializing the position of the '--'
          DDPos = DDPosition(args);

          /* Incorrect Call of 'replace' throws this exception */
          if (args.length < 1) {
        	  throw new UtilityNotFoundException(" ");
          }

          /* Throw exception if first arg is called incorrectly or '--' is missing */
          if (!args[0].equalsIgnoreCase(R) || DDPos < 0) {
            throw new UtilityNotFoundException(args[0]);
          }

          /* There must be a minimum of 5 arguments to run the program */
          if (args.length >= 5){

            //Handles No OPTs and simple single <from,to> pair replacement
            if (singleCheck(args[3])){
              for (int file = DDPos+1; file < args.length; file++){
                replaceWithNoOPTS(args[1], args[2], args[file]);
              }
            }

            /* No OPTs, multiple <from, to> pairs */
            else if (!optCheck(args)){
              //Iterates through which files you want to replace
              for (int fileNum = DDPos+1; fileNum < args.length; fileNum++){
                //Iterates through the <from, to> pairs
                for (int argNum = 1; argNum < DDPos; argNum += 2){
                  replaceWithNoOPTS(args[argNum], args[argNum+1], args[fileNum]);
                }
              }
            }

            /* OPTS, possibly multiple files */
            else if (optCheck(args)){
              System.out.println("OPTS have not been implemented yet");
            }
          }

          /* Less than 5 will throw an exception */
          else {
            throw new InvalidUtilityCallException(args[0]);
          }
        }
        catch (UtilityNotFoundException | InvalidUtilityCallException e){
          System.out.println(e.getMessage() + errorMessage);
        }
    }
    /********* End of Driver Method *********/

    /* Method to replace the file with the corresponding from/to strings */
    private static void replaceWithNoOPTS(String from, String to, String fileName){
      //Initialization of string that needs to be rewritten and files
      String fileContents = "";
      File inputFile = new File(fileName);

      try{
        //Initializing Scanner to read File and concatenate to our string
        Scanner s = new Scanner(inputFile);
        while (s.hasNext()){
          fileContents += (s.next() + " ");
        }
        fileContents = fileContents.replace(from, to);
        s.close();

        //Print our newly written string with replaced words to initial file
        PrintWriter outputFile = new PrintWriter(inputFile);
        outputFile.print(fileContents);
        outputFile.close();
      }
      catch (FileNotFoundException e){
        e.printStackTrace();
      }
    }

    /* Method that returns true if we're handling simplest case - single pair <from, to> */
    private static boolean singleCheck(String arg3){
      return arg3.equals(DD);
    }

    /* Method that tests for OPTs */
    private static boolean optCheck(String[] array){
      return optCounter(array) > 0;
    }

    /* Returns the number of OPTs that need to be applied */
    private static int optCounter(String[] arr){
      int counter = 0;
      for (int i = 1; i <= DDPos; i++){
        if (arr[i].equals("-b") ||
            arr[i].equals("-f") ||
            arr[i].equals("-l") ||
            arr[i].equals("-i")){
              counter++;
            }
      }
      try{
        //However we can only apply an OPT once, so more than 4 OPT calls is illegal
        if (counter > 4){
          throw new TooManyOptsException("Only up to 4 OPTs can be applied!");
        }
      }
      catch (TooManyOptsException e){
        System.out.println(e.getMessage());
      }
      return counter;
    }

    /* Find position of the '--' string */
    private static int DDPosition(String[] argumentArray){
      int position = -1;
      //Traverse the given command line argument to find the string
      for (int i = 0; i < argumentArray.length; i++){
        if (argumentArray[i].equals(DD)){
          position = i;
        }
      }
      return position;
    }

    private static void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
    }
}
/********** End of Main Class **********/
