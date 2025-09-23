package Practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateCntact_DP_Test {
	@Test(dataProvider = "getData")
	public void createContact(String firstname , String lastname) {
		System.out.println("Firstname :"+ firstname +", Lastname:"+ lastname);
		
	}
	@DataProvider
	public Object[] [] getData() {
		Object[] [] objArr = new Object[3] [2];
		objArr[0] [0] = "Poornima";
		objArr[0] [1] = "hr";
		
		objArr[1] [0] = "Sahana";
		objArr[1] [1] = "Sh";

		objArr[2] [0] = "Nisarga";
		objArr[2] [1] = "Ni";
		
		return objArr;
	}


}
