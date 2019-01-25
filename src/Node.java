import java.util.StringJoiner;

public class Node {
    public int time;
    public boolean predicate;
    public int streak;
    public int displacement;

    public Node (int time, boolean predicate) {
        this.time = time;
        this.predicate = predicate;
    }

    public String toString () {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        sj.add("Time: " + time);
        sj.add("Predicate: " + predicate);
        sj.add("Streak: " + streak);
        sj.add("Displacement: " + displacement);
        return sj.toString();
    }
}
