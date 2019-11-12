import java.util.Random;

public class Swapping {
    
    private double temp = 400.0;
    private double mincomp;
    private double maxcomp;
    private double avgcomp = 0.0;
    private int incrmnt = 0;
    private int trial = 0;
    private int swaps = 0;
    private int attemptG = 20000;
    private int Goal = 2000;
    

    public void FinalResults(Rooms abc) {
        abc.calcRoomCompatibilityArray();
        double dataArray[] = abc.getCompatiroom();
        double tempA = 0.0;
        double tempB = 0.0;
        mincomp = 100.0;
        maxcomp = 0.0;
        int numRooms = abc.getroomnum();

        for (int i = 0; i < numRooms; i++) {
            tempB = dataArray[i];
            if (tempB < mincomp) {
                mincomp = dataArray[i];
            }
            if (tempB > maxcomp) {
                maxcomp = dataArray[i];
            }
            tempA += tempB;
        }
        avgcomp = tempA / numRooms;
    }

    public void Output() {
        System.out.format("Iteration = %4s Temp = %7.3f  Attempt = %5s Swaps = %4s Min = %6.4f Max = %6.4f Average = %6.4f %n", incrmnt, temp,
                trial, swaps, mincomp,maxcomp, avgcomp );
    }

    public void swaprounds(Rooms abc) {
        Swaps(abc);
        incrmnt++;
        FinalResults(abc);
        Output();
        temp = .95 * temp;
        while (swaps > 0) {
            Swaps(abc);
            incrmnt++;
            FinalResults(abc);
            Output();
            temp = .95 * temp;
        }
    }
    public void Swaps(Rooms abc) {
        trial = 0;
        swaps = 0;
        while (trial < attemptG && swaps < Goal) {
            // randomly selecting 2 students from different suites 
            Random ran = new Random();
            int roomOne = ran.nextInt(abc.getroomnum());
            int roomTwo = ran.nextInt(abc.getroomnum());
            int personOne = ran.nextInt(abc.getrmnum());
            int personTwo = ran.nextInt(abc.getrmnum());
            while (roomOne == roomTwo) roomTwo = ran.nextInt(abc.getroomnum());

            // storing original ientity in arrays(local)
            int arrOne[] = abc.getassigning(roomOne).clone();
            int arrTwo[] = abc.getassigning(roomTwo).clone();

            // calculating the average compatibility score before swapping

            double oldScore = (abc.calcScore(arrOne) + abc.calcScore(arrTwo)) / 2;

            // swap room mates in the local arrays
            int temp = arrOne[personOne];
            arrOne[personOne] = arrTwo[personTwo];
            arrTwo[personTwo] = temp;
            double newScore = (abc.calcScore(arrOne) + abc.calcScore(arrTwo)) / 2;

            // calculating the  probability og swap
            double delta = newScore - oldScore;
            double prob = Math.exp(delta / temp);
            double random = ran.nextDouble();
            if (delta > 0) {
                abc.newassigning(arrOne, roomTwo);
                abc.newassigning(arrTwo, roomOne);
                swaps++;
                trial++;
            } else if (prob > random) {
                // probability swap
                abc.newassigning(arrOne, roomTwo);
                abc.newassigning(arrTwo, roomOne);
                swaps++;
                trial++;
            } else {
                trial++;
            }
        }
    }





}

