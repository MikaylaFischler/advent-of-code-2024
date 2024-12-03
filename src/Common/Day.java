package Common;

import java.util.Scanner;

public class Day<Part1, Part2> {
    private final int day;
    private final String part1Title;
    private final String part2Title;
    private Part1 part1Answer;
    private Part2 part2Answer;
    private long start;

    public Day(int thisDay, String answer1Title, String answer2Title) {
        day = thisDay;
        part1Title = answer1Title;
        part2Title = answer2Title;
    }

    public Scanner start() {
        System.out.println(Core.WHITE + "x------------------------------------------------x");
        System.out.printf(Core.WHITE + "|           " + Core.BLUE + "2024 " + Core.B_BLUE + "advent of code" + Core.BLUE + " day %02d" + Core.WHITE + "           |\n", day);
        System.out.println(Core.WHITE + "x------------------------------------------------x\n" + Core.RESET);

        Scanner input = Core.readInput("Day" + String.format("%02d", day));

        System.out.println(Core.YELLOW + "starting..." + Core.RESET);

        start = System.nanoTime();

        return input;
    }

    public void recordPart1(Part1 answer) {
        part1Answer = answer;
    }

    public void recordPart2(Part2 answer) {
        part2Answer = answer;
    }

    public void complete() {
        System.out.println(Core.GREEN + "done.\n" + Core.RESET);

        System.out.printf(Core.B_WHITE + "total time taken" + Core.WHITE + ": " + Core.RED + "%f" + Core.WHITE + " ms\n", (System.nanoTime() - start) / 1_000_000f);
        System.out.println(Core.B_RED + "[" + Core.MAGENTA + "part 1" + Core.B_RED + "] " + Core.B_WHITE + part1Title + Core.WHITE + ": " + Core.CYAN + part1Answer);
        System.out.println(Core.B_RED + "[" + Core.MAGENTA + "part 2" + Core.B_RED + "] " + Core.B_WHITE + part2Title + Core.WHITE + ": " + Core.CYAN + part2Answer + Core.RESET);
    }
}
