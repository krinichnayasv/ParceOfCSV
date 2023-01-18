import com.opencsv.CSVReader;
import net.sf.saxon.expr.Component;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String MOVEMENTS_CSV = "files/movementList1.csv";

    public static void main(String[] args) {

        Movements movements = new Movements(MOVEMENTS_CSV);
        System.out.println(Movements.printToString());


    }

}
