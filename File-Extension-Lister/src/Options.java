import java.util.Scanner;

/**
 * Created by Michi on 05.03.2017.
 */
public class Options {

    private static boolean _createtextfile = true; //default
    private static boolean _outputrepresentation = true; //default
    private static boolean _showfilecounter = true; //default

    static Scanner UserInput = new Scanner(System.in);

    public static void setMainOptions()
    {
        //create txt file?
        System.out.println("Save the result as a .txt file?");
        System.out.println("WARNING. If an old FileList exist, it will be overwritten. (y)es/(n)o       default: yes");
        switch(UserInput.nextLine()){
            case "y":
                setCreateTextFile(true);
                break;
            case "n":
                setCreateTextFile(false);
                break;
            default:
                break;
        }

        //outputrepresentation
        System.out.println("Show filenames or complete directories? (f)ilenames/(d)irectories      default: filenames");
        switch(UserInput.nextLine()){
            case "f":
                setOutputRepresentation(true);
                break;
            case "d":
                setOutputRepresentation(false);
                break;
            default:
                break;
        }

        //Print out number of files
        System.out.println("Print out the number of files? (y)es/(n)o      default: yes");
        switch(UserInput.nextLine()){
            case "y":
                setShowFileCounter(true);
                break;
            case "n":
                setShowFileCounter(false);
                break;
            default:
                break;
        }
    }

    public static void getMainOptions()
    {
        String outputrepresentation;
        System.out.println("Save the result as a .txt file? " + getCreateTextFile());
        if (getOutputRepresentation())
        {
            outputrepresentation = "filenames";
        }
        else{
            outputrepresentation = "complete directories";
        }
        System.out.println("Show filenames or complete directories? " + outputrepresentation);
        System.out.println("Print out the number of files? " + getShowFileCounter());
    }

    private static void setCreateTextFile(boolean state)
    {
        _createtextfile = state;
    }
    public static boolean getCreateTextFile()
    {
        return _createtextfile;
    }

    private static void setOutputRepresentation(boolean filenames)
    {
        _outputrepresentation = filenames;
    }
    public static boolean getOutputRepresentation()
    {
        return _outputrepresentation;
    }

    private static void setShowFileCounter(boolean state)
    {
        _showfilecounter = state;
    }
    public static boolean getShowFileCounter()
    {
        return _showfilecounter;
    }
}
