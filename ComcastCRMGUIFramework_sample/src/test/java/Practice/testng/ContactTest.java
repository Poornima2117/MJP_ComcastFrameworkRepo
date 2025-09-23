package Practice.testng;

import org.testng.annotations.Test;

public class ContactTest {
	@Test
	public void createContactTest() {
		System.out.println("ececute createContact with -->HDFC");
	}
	
	@Test(dependsOnMethods = "createContactTest")
	public void modifyContactTest() {
		System.out.println("execute modifyContactTest-->HDFC-->ICICI");
	}
    @Test(dependsOnMethods = "modifyContactTest")
    public void deleteContactTest() {
	System.out.println("ececute deleteContactTest ICICI");
}
}
