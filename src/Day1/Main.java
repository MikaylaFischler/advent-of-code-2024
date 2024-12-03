package Day1;

import Common.Core;
import Common.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static final int NUM_ROWS = 1000;

    public static void main(String[] args) {
        Day<Integer, Integer> aoc_day = new Day<>(1, "total distance", "similarity score");

        Scanner input = aoc_day.start();

        int[] l_list = new int[NUM_ROWS];
        int[] r_list = new int[NUM_ROWS];

        HashMap<Integer, Integer> map = new HashMap<>();

        int i = 0;

        while (input.hasNextLine()) {
            int a = input.nextInt();
            int b = input.nextInt();

            l_list[i]   = a;
            r_list[i++] = b;

            map.put(b, map.getOrDefault(b, 0) + 1);
        }

        Arrays.sort(l_list);
        Arrays.sort(r_list);

        int diff_sum = 0;

        for (i = 0; i < NUM_ROWS; i++) {
            diff_sum += Math.abs(l_list[i] - r_list[i]);
        }

        aoc_day.recordPart1(diff_sum);

        int sim_score = 0;

        for (i = 0; i < NUM_ROWS; i++) {
            sim_score += l_list[i] * map.getOrDefault(l_list[i], 0);
        }

        aoc_day.recordPart2(sim_score);

        aoc_day.complete();
    }
}