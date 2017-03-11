package carshop;

public class Demo {

	public static void main(String[] args) {
	
		try {
			Car car = new Car();
		} catch (InterruptedException e) {
			System.out.println("Production failed!");
		}
	}	
}
