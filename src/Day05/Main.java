package Day05;

import Common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Day<Integer, Integer> aocDay = new Day<>(5, "middle sum", "fixed middle sum");

        Scanner input = aocDay.start();

        HashMap<Integer, Rule> rules = new HashMap<>();
        ArrayList<ArrayList<Integer>> passUpdates = new ArrayList<>();
        ArrayList<ArrayList<Integer>> failUpdates = new ArrayList<>();

        boolean parsingRules = true;

        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (line.isEmpty()) {
                // blank line between rules and updates
                parsingRules = false;
            } else {
                if (parsingRules) {
                    String[] fields = line.split("\\|");

                    int id    = Integer.parseInt(fields[0]);
                    int after = Integer.parseInt(fields[1]);

                    if (!rules.containsKey(id)) {
                        rules.put(id, new Rule(id));
                    }

                    rules.get(id).addRequireAfter(after);
                } else {
                    // parse the update into an arraylist of integers
                    ArrayList<Integer> update = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

                    ArrayList<Rule> ruleSet = new ArrayList<>(); // rules applicable to this update
                    boolean pass = true;

                    // initial check for good updates
                    for (Map.Entry<Integer, Rule> rule : rules.entrySet()) {
                        if (update.contains(rule.getValue().getID())) {
                            pass &= rule.getValue().checkUpdate(update);
                            ruleSet.add(rule.getValue());
                        }
                    }

                    if (pass) {
                        passUpdates.add(update);
                    } else {
                        failUpdates.add(fixUpdate(ruleSet, update));
                    }
                }
            }
        }

        int mid_sum = 0;
        int mid_sum_fix = 0;

        for (ArrayList<Integer> passUpdate : passUpdates) {
            mid_sum += passUpdate.get(passUpdate.size() / 2);
        }

        aocDay.recordPart1(mid_sum);

        for (ArrayList<Integer> failUpdate : failUpdates) {
            mid_sum_fix += failUpdate.get(failUpdate.size() / 2);
        }

        aocDay.recordPart2(mid_sum_fix);

        aocDay.complete();
    }

    /**
     * recursively try to fix an update
     * @param rules rules to apply
     * @param update update to fix
     * @return fixed update
     */
    private static ArrayList<Integer> fixUpdate(ArrayList<Rule> rules, ArrayList<Integer> update) {
        for (Rule rule : rules) {
            update = rule.fixUpdate(update);
        }

        boolean pass = true;

        for (Rule rule : rules) {
            pass &= rule.checkUpdate(update);
        }

        if (pass) {
            return update;
        } else {
            return fixUpdate(rules, update);
        }
    }
}
