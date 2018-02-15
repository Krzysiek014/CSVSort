import java.io.File;
import java.io.FileNotFoundException;
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
    public static void main(String[] args) throws FileNotFoundException {
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

        chooseColumn();

        cmdScan.close();

        sort(column);

        for(String[] s: list)
        System.out.println(s[0] + "          "+ s[1]);
    }

    private static void sort(int i){
        String[] header = list.get(0);
        list.remove(0);
        Collections.sort(list, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[i].compareTo(o2[i]);
            }
        });
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

}