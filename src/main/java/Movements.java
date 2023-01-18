import com.opencsv.CSVReader;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movements {

  private static  List<ParseBankStatement> movements;

    public Movements(String pathMovementsCsv) {
        parseLines(pathMovementsCsv);
    }

    public static List<ParseBankStatement> parseLines (String pathMovementsCsv) {

        movements = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(pathMovementsCsv));
            String[] fragments = reader.readNext();
            if (fragments.length != 8) {
                System.out.println("Wrong line: " + fragments);
                         }
            String dateFormat = "dd.MM.yy";

            while ((fragments = reader.readNext()) != null) {
                movements.add(new ParseBankStatement(
                        fragments[1],
                        (new SimpleDateFormat(dateFormat)).parse(fragments[3]),
                        fragments[5],
                        Double.parseDouble(fragments[6].replace(",",".")),
                        Double.parseDouble(fragments[7].replace(",","."))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    return  movements;
   }

//  не нужен
    private static String setLine(String text) {
        String setLine = text;
        String begin = "";
        String middle = "";
        String end = "";
        int n = 0;

        for(int i=0; i<text.length(); i++) {
           n = 0;
            if (text.indexOf(i) == '\"') {
                n = n + 1;
            }
        }
            if (n>0 && n <=2 ) {
                for(int m=0; m<text.length(); m++) {
                    if(text.indexOf(m) == '\"') {
                        begin = text.substring(0, m);
                        m= m+1;
                        while (m<text.length() && text.indexOf(m) != '\"') {
                           middle += text.substring(m,m);
                            m = m+1;
                        }
                        if (m < text.length()-1) {
                            end = text.substring(m, text.length()-1);
                        } else {
                            end = "";
                        }

                    }
                }
                setLine = begin + middle.replace(",",".") + end;
            } else {
            }

         return setLine;
    }

    public static double getExpenseSum() {
    double expenseSum = 0.0;
   for (ParseBankStatement move : movements) {
       expenseSum += Math.abs(move.getMinus());
   }
       return expenseSum;
    }

    public static double getIncomeSum() {
        double incomeSum = 0.0;
        for (ParseBankStatement move : movements) {
            incomeSum += move.getPlus();
        }
        return incomeSum;
    }

    public static String printToString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Сумма расходов: " + getExpenseSum() + " руб." + "\n")
                .append("Сумма доходов: " + getIncomeSum() + " руб." + "\n");
        builder.append("Операции по расходам: " + "\n");
        for (ParseBankStatement move : movements) {
            if(move.getMinus() > 0) {
                builder.append(move.getTypeOperation() + "   на сумму:  " + move.getMinus() + " руб." + "\n");
            }
        }

        return builder.toString();
    }


}
