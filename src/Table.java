import java.util.LinkedList;
import java.util.StringJoiner;

public class Table {
    private LinkedList<Node> table;
    private LinkedList<Node> inflectionTable;

    public Table () {
        table = new LinkedList<>();
        inflectionTable = new LinkedList<>();
    }

    public void addData (int time, boolean predicate) {
        Node node = new Node(time, predicate);
        if (table.size() == 0 || table.getLast().predicate != predicate) {
            inflectionTable.addLast(node);
        }
        table.add(node);
    }

    public void calculateTrueStreaks() {
        Node previous = inflectionTable.getFirst();
        for (Node node : table) {
            if (node.predicate && !inflectionTable.contains(node)) {
                node.trueStreak = previous.trueStreak + (node.time - previous.time);
            }
            else {
                node.trueStreak = 0;
            }
            previous = node;
        }
    }

    public void calculateFalseStreakBeforeTurningTrue() {
        Node previous = inflectionTable.getFirst();
        for (Node node : table) {
            if (!node.predicate) {
                node.falseStreakBeforeTurningTrue = Integer.MIN_VALUE;
            }
            else if (inflectionTable.contains(node)) {
                int index = inflectionTable.indexOf(node);
                if (index == 0) {
                    node.falseStreakBeforeTurningTrue = Integer.MIN_VALUE;
                }
                else {
                    node.falseStreakBeforeTurningTrue = node.time - inflectionTable.get(index - 1).time;
                }
            }
            else {
                node.falseStreakBeforeTurningTrue = previous.falseStreakBeforeTurningTrue;
            }
            previous = node;
        }
    }

    public String toString () {
        StringJoiner sj = new StringJoiner("\n", "{", "}");
        for (Node node : table) {
            sj.add(node.toString());
        }
        return sj.toString();
    }

    public Node calculateAtTime (int time) {
        Node first = table.getFirst();
        Node node = null;
        if (time < first.time) {
            node = new Node(time, first.predicate);
            node.trueStreak = 0;
            node.falseStreakBeforeTurningTrue = Integer.MIN_VALUE;
        }
        else {
            Node previous = null;
            for (Node current : table) {
                if (time == current.time) {
                    return current;
                }
                else if (time > current.time) {
                    previous = current;
                }
                else {
                    break;
                }
            }
            if (previous != null) {
                node = new Node(time, previous.predicate);

                if (previous.predicate) {
                    node.trueStreak = previous.trueStreak + (node.time - previous.time);
                    node.falseStreakBeforeTurningTrue = previous.falseStreakBeforeTurningTrue;
                }

                else {
                    node.trueStreak = 0;
                    node.falseStreakBeforeTurningTrue = Integer.MIN_VALUE;
                }
            }
        }
        return node;
    }

    public void calculateFireTimesArrivalDeparture (int minFalse, int minTrue, int currentTime) {
        System.out.println("Fire Times For Arrival And Departure Rules");
        for (Node node : inflectionTable) {
            if (node.predicate) {
                if (node.time + minTrue > currentTime) {
                    break;
                }
                Node resultNode = calculateAtTime(node.time + minTrue);
                if (resultNode.trueStreak == minTrue && resultNode.falseStreakBeforeTurningTrue >= minFalse) {
                    System.out.println(resultNode);
                }
            }
        }
        System.out.println();
    }

    public void calculateFireTimesWhileAtWhileNotAt (int minTrue, int minTimeSinceLastFire, int currentTime) {
        System.out.println("Fire Times For While At And While Not At Rules");
        for (Node node : inflectionTable) {
            if (node.predicate) {
                int increment = minTrue;
                do {
                    if (node.time + increment > currentTime) {
                        break;
                    }
                    Node resultNode = calculateAtTime(node.time + increment);
                    if (resultNode != null && resultNode.predicate && resultNode.trueStreak >= minTrue) {
                        System.out.println(resultNode);
                        increment += minTimeSinceLastFire;
                    }
                    else {
                        break;
                    }
                } while (true);
            }
        }
        System.out.println();
    }


}
