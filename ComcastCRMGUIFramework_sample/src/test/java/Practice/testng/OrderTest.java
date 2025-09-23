package Practice.testng;

import org.testng.annotations.Test;

public class OrderTest {
	@Test
	public void createOrderTest() {
		System.out.println("Execute createOrderTest==>123");
	}
@Test(dependsOnMethods = "createOrderTest")
public void billingAnorder() {
	System.out.println("Execute bilingAnorderTest==>123");
}
}
