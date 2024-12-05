package Day02;

import Common.Core;
import Common.Day;

import java.util.Scanner;

public class Main {
    enum RESULT {
        UNSAFE, SAFE, DAMPENED
    }

    public static void main(String[] args) {
        Day<Integer, Integer> aocDay = new Day<>(2, "safe reports", "safe reports");

        Scanner input = aocDay.start();

        int safe_count = 0;
        int safe_d_count = 0;

        while (input.hasNextLine()) {
            String[] split = input.nextLine().split(" ");
            int[] report = new int[split.length];

            for (int i = 0; i < split.length; i++) {
                report[i] = Integer.parseInt(split[i]);
            }

            RESULT safe = isSafe(report, false);
            safe_count += (safe == RESULT.SAFE) ? 1 : 0;
            safe_d_count += (safe != RESULT.UNSAFE) ? 1 : 0;
        }

        aocDay.recordPart1(safe_count);
        aocDay.recordPart2(safe_d_count);

        aocDay.complete();
    }

    /**
     * delete a possibly bad entry from a report
     * @param report current report
     * @param deleteIndex index to drop from the report
     * @return updated report
     */
    private static int[] dampen(int[] report, int deleteIndex) {
        int[] newReport = new int[report.length - 1];

        int j = 0;
        for (int i = 0; i < report.length; i++) {
            if (i != deleteIndex) {
                newReport[j++] = report[i];
            }
        }

        return newReport;
    }

    /**
     * check if a report is safe
     * @param report initial report
     * @param retry if this is a recursive retry (prevent further retries)
     * @return report result
     */
    private static RESULT isSafe(int[] report, boolean retry) {
        boolean safe = report[0] != report[1];
        boolean inc  = report[0] < report[1];

        if (!(safe || retry)) {
            System.out.print(Core.RED);
            for (int i : report) System.out.print(i + " ");
            System.out.print(Core.WHITE + "\n > retrying, deleting initial  -> ");

            return isSafe(dampen(report, 0), true);
        } else if (safe) {
            for (int i = 1; i < report.length; i++) {
                final int delta = Math.abs(report[i - 1] - report[i]);
                safe = (delta >= 1) && (delta <= 3);

                if (inc) {
                    safe &= report[i - 1] < report[i];
                } else {
                    safe &= report[i - 1] > report[i];
                }

                if (!(safe || retry)) {
                    System.out.print(Core.RED);
                    for (int x : report) System.out.print(x + " ");
                    System.out.print(Core.WHITE + "\n > retrying, deleting initial  -> ");

                    RESULT result = isSafe(dampen(report, 0), true);
                    if (result != RESULT.UNSAFE) return result;

                    System.out.print(Core.WHITE + " > retrying, deleting previous -> ");

                    result = isSafe(dampen(report, i - 1), true);
                    if (result != RESULT.UNSAFE) return result;

                    System.out.print(Core.WHITE + " > retrying, deleting current  -> ");

                    result = isSafe(dampen(report, i), true);
                    return result;
                } else if (!safe) break;
            }
        }

        if (safe) {
            if (retry) {
                System.out.print(Core.MAGENTA);
            } else {
                System.out.print(Core.BLUE);
            }

            for (int i = 0; i < report.length; i++) {
                System.out.print(report[i] + ((i == report.length - 1) ? "\n" : " "));
            }
        } else {
            System.out.print(Core.RED);

            for (int i = 0; i < report.length; i++) {
                System.out.print(report[i] + ((i == report.length - 1) ? "\n" : " "));
            }
        }

        return safe ? (retry ? RESULT.DAMPENED : RESULT.SAFE) : RESULT.UNSAFE;
    }
}
