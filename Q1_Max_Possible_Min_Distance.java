package March_06_Assignment;

import java.util.Arrays;
import java.util.Scanner;

public class Q1_Max_Possible_Min_Distance
{
    // Function to check if it's possible to place cows with a minimum distance of 'mid' between them
    boolean isPossible(int stall[], int cow, int mid, int n) {
        int cowCount = 1; // Number of cows placed, initially 1
        int lastPos = stall[0]; // Last position where cow was placed
        for (int i = 0; i < n; i++) {
            if (stall[i] - lastPos >= mid) { // If the current stall can accommodate a cow with 'mid' distance from the last cow
                cowCount++; // Increase the count of cows placed
                if (cowCount == cow) { // If all cows are placed
                    return true; // It's possible to place all cows with 'mid' distance between them
                }
                lastPos = stall[i]; // Update the last position where cow was placed
            }
        }
        return false; // It's not possible to place all cows with 'mid' distance between them
    }

    // Function to find the maximum possible minimum distance between cows
    public int aggresiveCow(int stall[], int cow) {
        Arrays.sort(stall); // Sort the stalls
        int n = stall.length; // Number of stalls
        int start = 0; // Starting position
        int end = stall[n - 1]; // Ending position
        int mid = start + (end - start) / 2; // Calculate mid
        int ans = -1; // Initially, the answer is -1
        while (start <= end) { // Binary search for the maximum possible minimum distance
            if (isPossible(stall, cow, mid, n)) { // If it's possible to place cows with 'mid' distance between them
                ans = mid; // Update the answer
                start = mid + 1; // Update start to search for larger distances
            } else {
                end = mid - 1; // Update end to search for smaller distances
            }
            mid = start + (end - start) / 2; // Recalculate mid
        }
        return ans; // Return the maximum possible minimum distance
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner object for input
        System.out.println("Enter the number of stalls:");
        int n = scanner.nextInt(); // Number of stalls
        int[] stall = new int[n]; // Array to store the positions of stalls
        System.out.println("Enter the positions of stalls:");
        for (int i = 0; i < n; i++) { // Input positions of stalls
            stall[i] = scanner.nextInt();
        }
        System.out.println("Enter the number of cows:");
        int cow = scanner.nextInt(); // Number of cows
        Q1_Max_Possible_Min_Distance maxPossibleMinDistance = new Q1_Max_Possible_Min_Distance(); // Object of the class
        int result = maxPossibleMinDistance.aggresiveCow(stall, cow); // Find the maximum possible minimum distance
        System.out.println("The maximum possible minimum distance between cows is: " + result);
    }
}


