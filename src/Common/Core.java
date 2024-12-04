package Common;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Core {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[0;30m";
    public static final String RED = "\u001B[0;31m";
    public static final String YELLOW = "\u001B[0;33m";
    public static final String GREEN = "\u001B[0;32m";
    public static final String BLUE = "\u001B[0;34m";
    public static final String MAGENTA = "\u001B[0;35m";
    public static final String CYAN = "\u001B[0;36m";
    public static final String WHITE = "\u001B[0;37m";

//    #define RESET_BG	"\x1B[49m"
//    #define BLACK_BG	"\x1B[40m"
//    #define RED_BG		"\x1B[41m"
//    #define YELLOW_BG	"\x1B[43m"
//    #define GREEN_BG	"\x1B[42m"
//    #define BLUE_BG		"\x1B[44m"
//    #define MAGENTA_BG	"\x1B[45m"
//    #define CYAN_BG		"\x1B[46m"
//            #define WHITE_BG	"\x1B[47m"
//
    public static final String B_BLACK = "\u001B[1;30m";
    public static final String B_RED = "\u001B[1;31m";
    public static final String B_YELLOW = "\u001B[1;33m";
    public static final String B_GREEN = "\u001B[1;32m";
    public static final String B_BLUE = "\u001B[1;34m";
    public static final String B_MAGENTA = "\u001B[1;35m";
    public static final String B_CYAN = "\u001B[1;36m";
    public static final String B_WHITE = "\u001B[1;37m";

    /**
     * Open an Advent of Code day's input.txt file and prepare it into a Scanner.
     * This exits the program if no file is found.
     * @param dir the day's directory name
     * @return scanner instance
     */
    public static Scanner readInput(String dir) {
        String path ="src/".concat(dir.concat("/input.txt"));

        try {
            return new Scanner(new File(path));
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "missing input" + RESET);
            System.exit(-1);
        }

        return null;
    }
}