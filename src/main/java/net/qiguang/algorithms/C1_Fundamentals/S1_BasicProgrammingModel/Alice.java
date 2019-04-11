package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;


import java.util.Scanner;

public class Alice {

    public static void main(String[] args) {
        final int attempts = 9000; //hardcoded amount of times we will check to empirically calculate probabilities
        Scanner sc = new Scanner(System.in); //read data using Scanner
        System.out.println("Enter how much people should be at the party: ");
        System.out.println("Number should be > 2");
        int n = sc.nextInt(); //read N
        int countTimesFullSpreaded = 0; //Ho many times all people heard the rumor
        int peopleReached = 0; //How many people in all attempt heard the rumor
        for (int i = 0; i < attempts; i++) {
            boolean guests[] = new boolean[n]; // Alice is zero element, Bob is 1, all other guests - from 2 to N
            guests[1] = true; //Bob already heard
            boolean alreadyHeard = false; //Condition to exit while loop
            int nextPerson = -1; //our random next person
            int prevPerson = -1;
            int currentPerson = 1; //we start from Bob
            while (!alreadyHeard) {
                nextPerson = 1 + (int) (Math.random() * (n - 1)); //randomize next person
                while (nextPerson == currentPerson || nextPerson == prevPerson) //if it's true then we just randoming till we get other person
                    nextPerson = 1 + (int) (Math.random() * (n - 1));
                if (guests[nextPerson]) //if guest already heard
                {
                    if (rumorSpreaded(guests)) //if all people heard
                        countTimesFullSpreaded++;
                    peopleReached = peopleReached + countPeopleReached(guests); //how many people we get at all
                    alreadyHeard = true; // our condition is true
                }
                guests[nextPerson] = true; //now nextperson heard rumor
                prevPerson = currentPerson;
                currentPerson = nextPerson; //current person now is nextperson
            }
        }
        //For bigger N we will have less empirical probability
        System.out.println("Empirical probability that everyone will hear rumor except Alice in " + attempts + " attempts: " +
                (double) countTimesFullSpreaded / attempts);
        System.out.println("Average amount of people that rumor reached is: " + peopleReached / attempts);
    }

    public static int countPeopleReached(boolean arr[]) {
        int counter = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i])
                counter++;
        return counter;
    }

    public static boolean rumorSpreaded(boolean arr[]) {
        for (int i = 1; i < arr.length; i++)
            if (!arr[i])
                return false;
        return true;
    }


}