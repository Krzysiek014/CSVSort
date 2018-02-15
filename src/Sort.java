import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Krzysiek014 on 14.02.2018.
 */
public class Sort {

    private static File file;
    private static List<String[]> list;
    private static String fileUri = "";

    public static void main(String[] args) throws FileNotFoundException {

        //Getting file path
        getFile();

        // Scan .csv for entries
        Scanner scan = new Scanner(file);
        list = new LinkedList<>();
        while(scan.hasNextLine()) {
            list.add(scan.nextLine().split(","));
        }
        scan.close();

        sort(0);

        for(String[] s: list)
        System.out.println(s[0]);
    }

    private static void sort(int i){

        Collections.sort(list, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[i].compareTo(o2[i]);
            }
        });

    }

    public static void getFile(){
        Scanner cmdScan = new Scanner(System.in);
        do {
            System.out.println("Enter path to your .csv file...");
            fileUri = cmdScan.nextLine();
        } while (!fileUri.matches("(.+[\\.]csv)"));
        file = new File(fileUri);
        if(file.exists()) {
            cmdScan.close();
        }else{
            System.out.println("File "+fileUri+" does not exist");
            getFile();
        }
    }
}