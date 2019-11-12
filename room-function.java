import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Rooms {
    int students = 200;
    private int roomnum = 50;
    private int rmnum = 4;

    private int[][] assigning = new int[roomnum][rmnum];
    private double [][] compatibility = new double[students][students];
    private double[] Compatiroom = new double[roomnum];


    // read text file for score of people
    public void readData() throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("scores.txt")));
        while(sc.hasNextLine()) {
            for (int i=0; i<compatibility.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    compatibility[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
    }

    // assign rooms to students
    public void setassigning(){
        int p = 0;
        for (int r = 0; r < roomnum; r++){
            for (int m = 0; m < rmnum; m++){
                assigning[r][m] = p;
                p++;
            }
        }
    }

    // calculate compatibility score
    public double calccompScore(int[] room, int roommateID) {
        double totalCompat = 0;
        double indScore;
        for (int i = 0; i < rmnum; i++) {
            totalCompat += compatibility[room[roommateID]][room[i]];
        }
        indScore = totalCompat / (rmnum - 1);
        return indScore;
    }

    // calculating average compatibility of each room
    public double calcScore(int[] room) {
        double totalCompat = 0;
        double roomScore;

        for (int i = 0; i < 4; i++) {
            totalCompat += calccompScore(room, i);
        }
        roomScore = totalCompat / rmnum;
        return roomScore;
    }

    // assigning compatibility scores to different arrays
    public void calcRoomCompatibilityArray() {
        for (int i = 0; i < roomnum; i++) {
            Compatiroom[i] = calcScore(assigning[i]);
        }
    }

    public int getroomnum() {
        return roomnum;
    }
    public int getrmnum() {
        return rmnum;
    }

    public int[] getassigning(int room) {
        return assigning[room];
    }

    public void newassigning(int[] newassigning, int room) {
        this.assigning[room] = newassigning.clone();
    }

    public double[] getCompatiroom() {
        return Compatiroom;
    }
    public int[][] getAllassigning() {
        return assigning;
    }
}
