package spark1.spark.hackerrank.test1;

import java.util.Scanner;

public class Solution {
	
	static int solveMeFirst(int a, int b) {
        return a + b;
   }

   
 public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        int a;
        a = in.nextInt();
        int b;
        b = in.nextInt();
        int sum;
        sum = solveMeFirst(a, b);
        System.out.println("SolveMeFirst - Result : " + sum);
   }
}
