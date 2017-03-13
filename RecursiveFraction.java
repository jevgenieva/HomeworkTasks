import java.util.Scanner;

public class RecursiveFraction {

	public static void main(String[] args) {
		//print the sum of all the fractions from 1/2 to 1/"2 to the power of an input number"
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a number: ");
		int pow = sc.nextInt();
		
		System.out.println(recursiveFraction(1, pow, 1));
		sc.close();
		
	}
	static double recursiveFraction(double sum, int pow, double fract) {
		if(pow == 0)
			return sum;
		
		fract *= 2;
		sum += 1/fract;
		
		return recursiveFraction(sum, --pow, fract);
	}
}
