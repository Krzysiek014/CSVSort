import java.io.*;
import java.util.*;

/**
 * Created by Krzysiek014 on 14.02.2018.
 */
public class Sort {

    private static File file;
    private static List<String[]> list;
    private static String path = "";
    private static int column;
    private static Scanner cmdScan;

    public static void main(String[] args) throws IOException {
        cmdScan = new Scanner(System.in);
        //Getting file path
        getFile();

        // Scan .csv for entries
        Scanner scan = new Scanner(file);
        list = new LinkedList<>();
        while(scan.hasNextLine()) {
            list.add(scan.nextLine().split(","));
        }
        scan.close();

        // Sort by column
        chooseColumn();
        sort(column);

        //Print results
        for(String[] s: list) {
            for (String entry : s)
                System.out.print(String.format("%1$20s", entry));
            System.out.println();
        }

        //Saving file
        System.out.println("Save changes to a new file? (y/n)");
        cmdScan.nextLine();
        saveResults();

    }

    private static void sort(int i){
        String[] header = list.get(0);
        list.remove(0);
        list.sort(Comparator.comparing(o -> o[i]));
        list.add(0,header);
    }

    private static void getFile(){
        do {
            System.out.println("Enter path to your .csv file...");
            path = cmdScan.nextLine();
        } while (!path.matches("(.+[\\.]csv)"));
        file = new File(path);
        if(!file.exists()) {
            System.out.println("File "+path+" does not exist");
            getFile();
        }
    }

    private static void chooseColumn(){
        System.out.println("Choose column which should be sorted:");
        for(int i=0;i<list.get(0).length;i++)
            System.out.println(i+")  " + list.get(0)[i]);
        System.out.println("Enter number corresponding to a column");

        try {
            column = cmdScan.nextInt();
        }catch(InputMismatchException e){
            chooseColumn();
        }

    }

    private static void saveResults() throws FileNotFoundException {
        switch(cmdScan.nextLine().toUpperCase()){
            case "Y":
                System.out.println("Enter name for a new .csv file");
                PrintWriter pw = new PrintWriter(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf("\\")+1) + cmdScan.nextLine() +".csv");
                for(String[] entry : list)
                    pw.write(String.join(",",entry)+"\n");
                pw.close();
                break;
            case "N":
                System.out.println("Exit...");
                break;
            default:
                System.out.println("Type y to save changes or n to exit without saving");
                saveResults();
        }


    }

}