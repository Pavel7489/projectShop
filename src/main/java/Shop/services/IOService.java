package Shop.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOService {
    public static final String EXIT_CHAR = "e";
    private static final String MENU_LEGEND =
            "1. Print staff\n" +
                    "2. Add new employee\n" +
                    "3. Dismiss employee by name\n" +
                    "4. Change employee salary\n" +
                    "5. Change employee position\n" +
                    "6. Change employee plans\n"+
                    "7. Change employee lateness\n"+
                    EXIT_CHAR + ". exit\n";
    private BufferedReader rdr;

    public IOService() {
        this.rdr = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getString() throws IOException {

        return rdr.readLine();
    }

    public int getInt() throws IOException, NumberFormatException {

        return Integer.parseInt(getString());
    }





    public void printLegend() {

        System.out.println(MENU_LEGEND);
    }
}
