import java.util.StringJoiner;

public class Node {
    public int time;
    public boolean predicate;
    public int trueStreak;
    public int falseStreakBeforeTurningTrue;

    public Node (int time, boolean predicate) {
        this.time = time;
        this.predicate = predicate;
    }

    public String toString () {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        sj.add("Time: " + time);
        sj.add("Predicate: " + predicate);
        sj.add("True Streak: " + trueStreak);
        sj.add("False Streak Before Turning True: " + falseStreakBeforeTurningTrue);
        return sj.toString();
    }
}
