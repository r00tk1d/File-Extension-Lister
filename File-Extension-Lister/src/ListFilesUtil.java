/**
 * Created by Michi on 04.03.2017.
 */
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ListFilesUtil {

    private int _counterFiles = 0;
    private String _directoryTextfile;
    private String _directory;
    private List<String> _filteredFiles = new ArrayList<String>();
    private List<String> _fileextensions = new ArrayList<String>();
    private Scanner _fileExtensionsInput = new Scanner(System.in);


    /**
     * constructor
     * create a new object with a directory choosed by the User
     *
     * @param directory directory choosed by User
     */
    public ListFilesUtil(String directory) {
        _directory = directory;
    }

    /**
     * added by r00tk1d
     * List all the movie files under a directory and subdirectories
     *
     * @param directoryName to be listed
     */

    public void listFilterFiles(String directoryName) {

        //get all the files from a directory, list the files
        String[] fileextensionArr = new String[_fileextensions.size()];
        fileextensionArr = _fileextensions.toArray(fileextensionArr);
        FileNameExtensionFilter filterFiles = new FileNameExtensionFilter("filterExtensionsDescription", fileextensionArr);
        File dir = new File(directoryName);
        File[] fList = dir.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isFile() && filterFiles.accept(file)) {
                    if (Options.getOutputRepresentation()) {
                        System.out.println(file.getName());
                        _filteredFiles.add(file.getName());
                    } else if (!Options.getOutputRepresentation()) {
                        System.out.println(file.getAbsolutePath());
                        _filteredFiles.add(file.getAbsolutePath());
                    }
                    _counterFiles++;
                } else if (file.isDirectory()) {
                    listFilterFiles(file.getAbsolutePath());
                }
            }

    }

    /**
     * Set the Filterextensions and save it in an ArrayList
     */

    public void setFileExtensions() {
        //User set the filterextensionsfilter
        System.out.println("first filterextension:");
        _fileextensions.add(_fileExtensionsInput.nextLine());
        while (_fileextensions.get(0) == "") {
            System.out.println("Please add at least one extension:");
            _fileextensions.add(_fileExtensionsInput.nextLine());
        }
        System.out.println("add new filterextension? (y)es/(n)o");
        while (_fileExtensionsInput.nextLine().equals("y")) {
            System.out.println("new filterextension:");
            _fileextensions.add(_fileExtensionsInput.nextLine());
            System.out.println("add new filterextension? (y)es/(n)o");
        }
    }

    /**
     * print out the number of filtered Files
     */
    public void printCounterFilteredFiles() {
        System.out.println("");
        System.out.println("Number of files: " + _counterFiles);
    }

    /**
     * tests if the directory from the user is a directory
     */
    public String testDirectory() {
        File f = new File(_directory);
        while (!(f.exists() || f.isDirectory())) {

            System.out.println("No such directory. Please choose new directory:");
            _directory = _fileExtensionsInput.nextLine();
            f = new File(_directory);
        }
        return _directory;

    }

    /**
     * create a text file with the result
     */
    public void createTextFile() {
        try {
            Path file = Paths.get("FileList.txt");
            Files.write(file, _filteredFiles, Charset.forName("UTF-8"));


            if(Options.getShowFileCounter()) {
                try{
                    Files.write(Paths.get("FileList.txt"), ("Number of files: " + _counterFiles).getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e){
                    System.out.println("Error Number of files can not added to text file. Please Contact the admin.");
                }
            }

            System.out.println("Saved in: " + file.toFile().getAbsolutePath());
            _directoryTextfile = file.toFile().getAbsolutePath();
        } catch (IOException e) {
            System.out.println("Error create textfile. Please contact the admin.");
        }

    }
    public String getDirectoryTextfile ()
    {
        return _directoryTextfile;
    }
























//(YET) UNUSED METHODS

    /**
     * List all the files and folders from a directory
     *
     * @param directoryName to be listed
     */
    public void listFilesAndFolders(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                System.out.println(file.getName());
                _counterFiles++;
            }
    }

    /**
     * List all the files under a directory
     *
     * @param directoryName to be listed
     */
    public void listFiles(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    _counterFiles++;
                }
            }
    }

    /**
     * List all the folder under a directory
     *
     * @param directoryName to be listed
     */
    public void listFolders(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isDirectory()) {
                    System.out.println(file.getName());
                    _counterFiles++;
                }
            }
    }

    /**
     * added by r00tk1d
     * List all the folder and subfolders under a directory
     *
     * @param directoryName to be listed
     */
    public void listFoldersAndSubfolders(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isDirectory()) {
                    System.out.println(file.getName());
                    _counterFiles++;
                    listFoldersAndSubfolders(file.getAbsolutePath());
                }

            }
    }

    /**
     * List all files from a directory and its subdirectories
     *
     * @param directoryName to be listed
     */
    public void listFilesAndFilesSubDirectories(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    System.out.println(file.getAbsolutePath());
                    _counterFiles++;
                } else if (file.isDirectory()) {
                    listFilesAndFilesSubDirectories(file.getAbsolutePath());
                }
            }
    }
}