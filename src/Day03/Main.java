package Day03;

import Common.Core;
import Common.Day;

import java.util.Objects;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Day<Integer, Integer> aocDay = new Day<>(3, "mul sum", "conditional mul sum");

        Scanner input = aocDay.start();
        Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)", Pattern.CASE_INSENSITIVE);

        boolean cond = true;
        int mul_sum = 0;
        int cond_mul_sum = 0;

        while (input.hasNextLine()) {
            Matcher matcher = pattern.matcher(input.nextLine());

            while (matcher.find()) {
                if (Objects.equals(matcher.group(0), "do()")) {
                    cond = true;
                    System.out.println(Core.GREEN + "ENABLE");
                } else if (Objects.equals(matcher.group(0), "don't()")) {
                    cond = false;
                    System.out.println(Core.RED + "DISABLE");
                } else {
                    int a = Integer.parseInt(matcher.group(1));
                    int b = Integer.parseInt(matcher.group(2));

                    if (cond) {
                        cond_mul_sum += a * b;
                        System.out.printf(Core.BLUE + "MUL %3d %3d\n", a, b);
                    } else {
                        System.out.printf(Core.RESET + "MUL %3d %3d\n", a, b);
                    }

                    mul_sum += a * b;
                }
            }
        }

        aocDay.recordPart1(mul_sum);
        aocDay.recordPart2(cond_mul_sum);

        aocDay.complete();
    }
}
