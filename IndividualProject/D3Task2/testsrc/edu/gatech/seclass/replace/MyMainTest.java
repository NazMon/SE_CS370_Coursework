package edu.ga.seclass.replace;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class MyMainTest {
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // Actual test cases
    /* Test Case #1
     * Implementation of test frame #1
     * If the "<From>" attribute does not exist,
     * the replace throw an error; without options
     */
    @Test
    public void mainTest7() throws  Exception{
    	String args[] = {"", "world", "--", "<filename>", "[<filename>]"};

    	assertEquals("Missing <From> attribute", errStream.toString().trim());
    }

    /* Test Case #2
     * Implementation of test frame #2
     * If the "--" does not exist, the replace
     * utility should fail; without options
     */
    @Test
    public void mainTest8() throws Exception {
    	String args[] = {"dreams", "<Memes>", "<filename>", "[<filename>]"};

    	assertEquals("Missing <--> keyword", errStream.toString().trim());
    }

    /* Test Case #3
     * Implementation of test frame #3
     * "--" is in the wrong position,
     * the replace  utility should fail;
     * without options
     */
    @Test
    public void mainTest9() throws Exception {
    	String args[] = {"--", "dreams", "<Memes>", "<filename>", "[<filename>]"};

    	assertEquals("<--> Keyword is in the wrong location", errStream.toString().trim());
    }


    /* Test Case #4
     * Implementation of test frame #3
     * "--" is in a different wrong position,
     * the replace  utility should fail;
     * without options
     */
    @Test
    public void mainTest10() throws Exception {
    	String args[] = {"dreams", "--", "<Memes>", "<filename>", "[<filename>]"};

    	assertEquals("<--> Keyword is in the wrong location", errStream.toString().trim());
    }

    /* Test Case #5
     * Implementation of test frame #3
     * "--" is in a different wrong position,
     * the replace  utility should fail;
     * without using options
     */
    @Test
    public void mainTest11() throws Exception {
    	String args[] = {"dreams",  "<Memes>", "<filename>", "--", "[<filename>]"};

    	assertEquals("<--> Keyword is in the wrong location", errStream.toString().trim());
    }

    /* Test Case #6
     * Implementation of test frame #3
     * "--" is in a different wrong position,
     * the replace  utility should fail;
     * without using options
     */
    @Test
    public void mainTest12() throws Exception {
    	String args[] = {"dreams",  "<Memes>", "<filename>", "[<filename>]", "--"};

    	assertEquals("<--> Keyword is in the wrong location", errStream.toString().trim());
    }

    /* Test Case #7
     * Implementation of test frame #4
     * Multiple instances of a single
     * OPT attribute - using the [-b]
     */
    @Test
    public void mainTest13() throws Exception {
    	String args[] = {"-b","-b","dreams",  "<Memes>", "--", "<filename>", "[<filename>]"};

    	assertEquals("[-b] OPT used too many times", errStream.toString().trim());
    }

    /* Test Case #8
     * Implementation of test frame #4
     * Multiple instances of a single
     * OPT attribute - using the [-f]
     */
    @Test
    public void mainTest14() throws Exception {
    	String args[] = {"-f","-f","dreams",  "<Memes>", "--", "<filename>", "[<filename>]"};

    	assertEquals("[-f] OPT used too many times", errStream.toString().trim());
    }

    /* Test Case #9
     * Implementation of test frame #4
     * Multiple instances of a single
     * OPT attribute - using the [-l]
     */
    @Test
    public void mainTest15() throws Exception {
    	String args[] = {"-l","-l","dreams",  "<Memes>", "--", "<filename>", "[<filename>]"};

    	assertEquals("[-l] OPT used too many times", errStream.toString().trim());
    }

    /* Test Case #10
     * Implementation of test frame #4
     * Multiple instances of a single
     * OPT attribute - using the [-i]
     */
    @Test
    public void mainTest16() throws Exception {
    	String args[] = {"-i","-i","dreams",  "<Memes>", "--", "<filename>", "[<filename>]"};

    	assertEquals("[-i] OPT used too many times", errStream.toString().trim());
    }

    /* Test Case #11
     * Implementation of test frame #6
     * If file size is 0, throw an error
     */
    @Test
    public void mainTest17() throws Exception {
    	File inputFile1 = createInputFile1();
        long size = inputFile1.getTotalSpace();
        //To be implemented
        //Main.testSize(size);
        assertEquals("File Size not found", errStream.toString().trim());
    }

    /* Test Case #12
     * Implementation of test frame #7
     * If file name is not specified
     */
    @Test
    public void mainTest18() throws Exception {
    	File inputFile1 = createInputFile1();
    	String args[] = {"dreams",  "<Memes>", "--", ""};
    	String fileName = inputFile1.getName();
        assertEquals("No File Arguments were found", errStream.toString().trim());
    }

    /* Test Case #12
     * Implementation of test frame #8
     * If there are 0 files included in
     * the utility call
     */
    @Test
    public void mainTest19() throws Exception {
    	File inputFile1 = createInputFile1();
    	String args[] = {"dreams",  "<Memes>", "--"};
    	String fileName = inputFile1.getName();
        assertEquals("No File Arguments were found", errStream.toString().trim());
    }

    /* Test Case #13
     * Implementation of test frame #9
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -l, -i - applied
     * Occurrences of each - 1
     * 1 File Size, Name are Present
     */
    @Test
    public void mainTest20() throws Exception {
    	File inputFile = createInputFile2();
    	String args[] = {"-b","-f","-l","-i","dreams", "", "--", inputFile.getPath()};


    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #14
     * Implementation of test frame #10
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -l, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest21() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();
    	File inputFile3 = createInputFile3();
    	String args[] = {"-b","-f","-l","-i","dreams","", "--", inputFile.getPath(), inputFile2.getPath(), inputFile3.getPath()};


    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());
    	String expected3 = "There are no hopes and";
    	String actual3 = getFileContent(inputFile3.getPath());


    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    	assertEquals("Correct!", expected3, actual3);
    }

    /* Test Case #15
     * Implementation of test frame #11
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -l - applied
     * Occurrences of each - 1
     * 1 File Size, and Name are Present
     */
    @Test
    public void mainTest22() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b","-f","-l","dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #16
     * Implementation of test frame #12
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -l - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest23() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b","-f","-l","dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #17
     * Implementation of test frame #13
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest24() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b","-f","-i","dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #18
     * Implementation of test frame #14
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest25() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b","-f","-i","dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #19
     * Implementation of test frame #15
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -f - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest26() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b","-f","dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #20
     * Implementation of test frame #16
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -f - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest27() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b","-f","dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #21
     * Implementation of test frame #17
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -l, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest28() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b","-l", "-i", "dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #22
     * Implementation of test frame #18
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -l, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest29() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b","-l", "-i", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #23
     * Implementation of test frame #19
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -l - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest30() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b","-l","dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #24
     * Implementation of test frame #20
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -l - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest31() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b","-l", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #25
     * Implementation of test frame #21
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest32() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b","-i","dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #26
     * Implementation of test frame #22
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest33() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b","-i", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #27
     * Implementation of test frame #23
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest34() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b","dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #28
     * Implementation of test frame #24
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -b - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest35() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #29
     * Implementation of test frame #25
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -f, -l, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest36() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-f", "-l", "-i", "dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #30
     * Implementation of test frame #26
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -f, -l, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest37() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-f", "-l", "-i", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #31
     * Implementation of test frame #27
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -f, -l- applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest38() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-f", "-l", "dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #32
     * Implementation of test frame #28
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -f, -l - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest39() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-f", "-l", "-i", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #33
     * Implementation of test frame #29
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -f, -i- applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest40() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-f", "-i", "dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #34
     * Implementation of test frame #30
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -f, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest41() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-f", "-i", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #35
     * Implementation of test frame #31
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -f - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest42() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-f", "dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #36
     * Implementation of test frame #32
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -f - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest43() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-f", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #37
     * Implementation of test frame #33
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -l, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest44() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-l", "-i", "dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #38
     * Implementation of test frame #34
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -l, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest45() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-l", "-i", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #39
     * Implementation of test frame #35
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -l - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest46() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-l", "dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #40
     * Implementation of test frame #36
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -l - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest47() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-l", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #41
     * Implementation of test frame #37
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest48() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-i", "dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #42
     * Implementation of test frame #38
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest49() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-i", "dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #43
     * Implementation of test frame #39
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * None - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest50() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"dreams", "", "--", inputFile.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #44
     * Implementation of test frame #40
     * From - Alphanumeric
     * To - Empty String
     * -- - Exists
     * DD Position - Correct
     * None - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest51() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"dreams", "", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #45
     * Implementation of test frame #41
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -l, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest52() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b", "-f", "-l", "-i", "dreams", "memes", "--", inputFile.getPath()};

    	String expected = "There are no hopes and memes";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #46
     * Implementation of test frame #42
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -l, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest53() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "-f", "-l", "-i", "dreams", "memes", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and memes";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and memes";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #47
     * Implementation of test frame #43
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -l - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest54() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b", "-f", "-l", "dreams", "memes", "--", inputFile.getPath()};

    	String expected = "There are no hopes and memes";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #48
     * Implementation of test frame #44
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -l - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest55() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "-f", "-l", "dreams", "memes", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and memes";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and memes";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #49
     * Implementation of test frame #45
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest56() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b", "-f", "-i", "dreams", "memes", "--", inputFile.getPath()};

    	String expected = "There are no hopes and memes";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #50
     * Implementation of test frame #46
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -f, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest57() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "-f", "-i", "dreams", "memes", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "There are no hopes and memes";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "There are no hopes and memes";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #51
     * Implementation of test frame #47
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -f - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest58() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b", "-f", "responsibility", "memes", "--", inputFile.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #52
     * Implementation of test frame #48
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -f - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest59() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "-f", "responsibility", "memes", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "With great power, comes great memes";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #53
     * Implementation of test frame #49
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -l, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest60() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b", "-l", "-i", "responsibility", "memes", "--", inputFile.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #54
     * Implementation of test frame #50
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -l, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest61() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "-l", "-i", "responsibility", "memes", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "With great power, comes great memes";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #55
     * Implementation of test frame #51
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -l - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest62() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b", "-l", "responsibility", "memes", "--", inputFile.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #56
     * Implementation of test frame #52
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -l - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest63() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "-l", "responsibility", "memes", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "With great power, comes great memes";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #57
     * Implementation of test frame #53
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -i - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest64() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b", "-i", "responsibility", "memes", "--", inputFile.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #58
     * Implementation of test frame #54
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b, -i - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest65() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "-i", "responsibility", "memes", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "With great power, comes great memes";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }

    /* Test Case #59
     * Implementation of test frame #55
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b - applied
     * Occurrences of each - 1
     * 1 File Sizes, and Names are Present
     */
    @Test
    public void mainTest66() throws Exception {
    	File inputFile = createInputFile1();

    	String args[] = {"-b", "responsibility", "memes", "--", inputFile.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());

    	assertEquals("Correct!", expected, actual);
    }

    /* Test Case #60
     * Implementation of test frame #56
     * From - Alphanumeric
     * To - Alphanumeric
     * -- - Exists
     * DD Position - Correct
     * -b - applied
     * Occurrences of each - 1
     * Multiple File Sizes, and Names are Present
     */
    @Test
    public void mainTest67() throws Exception {
    	File inputFile = createInputFile1();
    	File inputFile2 = createInputFile2();

    	String args[] = {"-b", "responsibility", "memes", "--", inputFile.getPath(), inputFile2.getPath()};

    	String expected = "With great power, comes great memes";
    	String actual = getFileContent(inputFile.getPath());
    	String expected2 = "With great power, comes great memes";
    	String actual2 = getFileContent(inputFile2.getPath());

    	assertEquals("Correct!", expected, actual);
    	assertEquals("Correct!", expected2, actual2);
    }
}
