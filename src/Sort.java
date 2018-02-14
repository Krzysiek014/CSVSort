import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Krzysiek014 on 14.02.2018.
 */
public class Sort {

    private static File file;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner cmdScan = new Scanner(System.in);

        System.out.println("Enter URI to your .csv file...");
        file = new File(cmdScan.nextLine());

        Scanner scan = new Scanner(file);
        List<String[]> list = new LinkedList<>();
        while(scan.hasNextLine()) {
            list.add(scan.nextLine().split(","));
        }
    }
}
