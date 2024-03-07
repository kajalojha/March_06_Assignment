package March_06_Assignment;

import java.util.Scanner;

public class Q2_Book_Alocation
{
    // Function to check if it's possible to allocate chapters such that
    // each day's reading time is less than or equal to 'mid'
    boolean isPossible(int[] time, int chapter, int days, int mid) {
        int chaptercount = 1;
        int pagesum = 0;

        // Iterate through each chapter
        for (int i = 0; i < chapter; i++) {
            // Check if adding the current chapter's time exceeds 'mid'
            if (pagesum + time[i] <= mid) {
                pagesum += time[i]; // If not, add the chapter's time to the total
            } else {
                chaptercount++; // If adding the current chapter's time exceeds 'mid', move to the next day
                // If the number of days exceeds the given 'days', or if the current chapter's time exceeds 'mid',
                // it's not possible to allocate chapters
                if (chaptercount > days || time[i] > mid) {
                    return false;
                }
                pagesum = time[i]; // Start a new day with the current chapter's time
            }
            // If the number of days exceeds the given 'days', it's not possible to allocate chapters
            if (chaptercount > days) {
                return false;
            }
        }
        return true; // If all chapters are allocated successfully, return true
    }

    // Function to allocate chapters to minimize the maximum time a day's reading
    // It uses binary search to find the minimum possible maximum time
    public int allocateChapter(int[] time, int chapter, int days) {
        int start = 0;
        int sum = 0;

        // Calculate the total time required to read all chapters
        for (int i = 0; i < chapter; i++) {
            sum += time[i];
        }
        int end = sum;
        int ans = -1;
        int mid = start + (end - start) / 2;

        // Binary search loop
        while (start <= end) {
            // Check if it's possible to allocate chapters with 'mid' as maximum time
            if (isPossible(time, chapter, days, mid)) {
                ans = mid; // Update the answer if possible
                end = mid - 1; // Try to minimize the maximum time further
            } else {
                start = mid + 1; // If not possible, increase the maximum time
            }
            mid = start + (end - start) / 2; // Update the mid for the next iteration
        }
        return ans; // Return the minimum possible maximum time
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of days:");
        int days = scanner.nextInt();
        System.out.println("Enter the number of chapters:");
        int chapter = scanner.nextInt();
        int[] time = new int[chapter];
        System.out.println("Enter time required for each chapter:");
        for (int i = 0; i < chapter; i++) {
            time[i] = scanner.nextInt();
        }
        Q2_Book_Alocation bookAllocation = new Q2_Book_Alocation();
        int result = bookAllocation.allocateChapter(time, chapter, days);
        System.out.println("Minimum possible maximum time per day: " + result);

    }
}
