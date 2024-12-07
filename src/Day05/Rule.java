package Day05;

import java.util.ArrayList;

public class Rule {
    private final int id;

    private final ArrayList<Integer> after = new ArrayList<>();

    public Rule(int id) {
        this.id = id;
    }

    public int getID() {  return id; }

    public void addRequireAfter(int x) {
        after.add(x);
    }

    /**
     * check if an update passes this rule
     * @param update update to check
     * @return true if pass, false otherwise
     */
    public boolean checkUpdate(ArrayList<Integer> update) {
        boolean failing = false;

        for (int i : update) {
            if (i == id) {
                return !failing;
            } else {
                failing |= after.contains(i);
            }
        }

        return true;
    }

    /**
     * rebuild the update, but put anything that can't be before this id at the end of the update
     * @param update update to fix
     * @return fixed update
     */
    public ArrayList<Integer> fixUpdate(ArrayList<Integer> update) {
        boolean found = false;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = update.size() - 1; i >= 0; i--) {
            if (found && after.contains(update.get(i))) {
                list.addLast(update.get(i));
            } else {
                list.addFirst(update.get(i));
            }

            if (update.get(i) == id) {  found = true; }
        }

        return list;
    }
}
