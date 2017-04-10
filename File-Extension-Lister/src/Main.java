import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Michi on 04.03.2017.
 */
public class Main {

    public static void main (String[] args){
        Scanner UserInput = new Scanner(System.in);


        //Program description
        System.out.println("This program let you choose a directory and an extensionfilter for listing all the files in the choosen directory and its subdirectories.");
        System.out.println("If you want you can save the result in a .txt file located in the src folder.");

        //Options
        System.out.println("");
        System.out.println("s: (s)et Options");
        System.out.println("g: (g)et Options");
        System.out.println("enter: start program");
        boolean mainmenu = true;
        while(mainmenu == true) {
            switch (UserInput.nextLine()) {
                case "s":
                    Options.setMainOptions();
                    System.out.println("s: (s)et Options, g: (g)et Options, enter: start program");
                    break;
                case "g":
                    Options.getMainOptions();
                    System.out.println("s: (s)et Options, g: (g)et Options, enter: start program");
                    break;
                default:
                    mainmenu = false;
                    break;
            }

        }

        //User choose directory
        System.out.println("Directory:");
        String directoryWindows = UserInput.nextLine();
        ListFilesUtil listFilesUtil = new ListFilesUtil(directoryWindows);
        //set the new choosen working directory to directoryWindows
        directoryWindows = listFilesUtil.testDirectory();

        //User set the extensions
        listFilesUtil.setFileExtensions();

        //list the files
        System.out.println("loading. please wait...");
        System.out.println("");
        listFilesUtil.listFilterFiles(directoryWindows);

        //print counter
        if(Options.getShowFileCounter()) {
            listFilesUtil.printCounterFilteredFiles();
            System.out.println("");
        }

        //create textfile
        if(Options.getCreateTextFile()) {
            System.out.println("");
            System.out.println("--textfile created--");
            listFilesUtil.createTextFile();

        }

        //open textfile
        if(Options.getCreateTextFile())
        {
            System.out.println("Open textfile? (y)es/(n)o");
            switch(UserInput.nextLine())
            {
                case "y":
                    try {
                        Desktop.getDesktop().open(new File(listFilesUtil.getDirectoryTextfile()));}
                    catch (IOException e) {
                        System.out.println("Error open textfile. Please contact the admin.");
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("--finished--");
    }


}


