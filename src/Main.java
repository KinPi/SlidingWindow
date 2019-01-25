public class Main {

    public static void main (String [] args) {
        Table table = new Table();
//        table.addData(0, false);
//        table.addData(5, false);
//        table.addData(10, true);
//        table.addData(15, true);
//        table.addData(20, true);
//        table.addData(30, false);
//        table.addData(33, true);

        table.addData(0, true);
        table.addData(5, true);
        table.addData(10, true);
        table.addData(20, false);


//        table.addData(0, true);
//        table.addData(3, true);
//        table.addData(10, true);
//        table.addData(12, false);
//        table.addData(16, false);
//        table.addData(18, true);
//        table.addData(27, true);
//        table.addData(29, true);
//        table.addData(31, false);
//        table.addData(44, true);
//        table.addData(48, true);
//        table.addData(59, false);
//        table.addData(61, true);
//        table.addData(65, false);

        table.calculateTrueStreaks();
        table.calculateFalseStreakBeforeTurningTrue();
        System.out.println(table);

        System.out.println();

//        for (int i = 0; i <= 70; i++) {
//            System.out.println(table.calculateAtTime(i));
//        }

        table.calculateFireTimesArrivalDeparture(5, 3, 70);


        table.calculateFireTimesWhileAtWhileNotAt(3, 5, 70);
    }
}
