import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // create class instances
        Rooms rooms = new Rooms();
        Swapping sw = new Swapping();

        // read scores from text file
        try {
            rooms.readData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // assigning rooms to students
        rooms.setassigning();

        // calculating room compatibility scores
        rooms.calcRoomCompatibilityArray();

        // calculating and concluding final results
        sw.FinalResults(rooms);
        sw.Output();

        // executing simulated annealing swaps
        sw.swaprounds(rooms);
        
        System.out.println(Arrays.deepToString(rooms.getAllassigning()));

    }
}
