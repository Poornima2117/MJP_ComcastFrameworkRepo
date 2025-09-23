package Practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_Dp_Test {
	public class CreateCntact_DP_Test {
		@Test(dataProvider = "getData")
		public void createContact(String firstname , String lastname , long phonenumber) {
			System.out.println("Firstname :"+ firstname +", Lastname:"+ lastname +",phoneumber :"+phonenumber);
			
		}
		@DataProvider
		public Object[] [] getData() {
			Object[] [] objArr = new Object[3] [3];
			objArr[0] [0] = "Poornima";
			objArr[0] [1] = "MJ";
			objArr[0] [2] = 6362298166l;
			
			objArr[1] [0] = "Sahana";
			objArr[1] [1] = "Sh";
			objArr[1] [2] = 636229846l;

			objArr[2] [0] = "Nisarga";
			objArr[2] [1] = "Ni";
			objArr[2] [2] = 6362298187l;
			
			return objArr;
		}
	}

}
