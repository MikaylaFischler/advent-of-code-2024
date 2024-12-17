package Day07;

import Common.Day;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Day<Long, Long> aocDay = new Day<>(7, "calibration result", "calibration result (concat)");

        Scanner input = aocDay.start();

        ArrayList<Equation> eqs = new ArrayList<>();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.isEmpty()) {
                String[] parts = line.split(":");
                Scanner eqScanner = new Scanner(parts[1]);
                ArrayList<Long> values = new ArrayList<>();

                while (eqScanner.hasNextLong()) {
                    values.add(eqScanner.nextLong());
                }

                eqs.add(new Equation(Long.parseLong(parts[0]), values));
            }
        }

        long p1Result = 0;
        long p2Result = 0;

        for (Equation eq : eqs) {
            if (eq.checkValidBiOp()) {
                p1Result += eq.getTestValue();
            }

            if (eq.checkValidTriOp()) {
                p2Result += eq.getTestValue();
            }
        }

        aocDay.recordPart1(p1Result);
        aocDay.recordPart2(p2Result);

        aocDay.complete();
    }
}
