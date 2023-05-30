package genericLibraries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/**
	 * This method generates random number within the limit
	 * @param limit
	 * @return 
	 */
	
	public int generateRandomNum(int limit) {
		Random random = new Random();
		return random.nextInt(limit);
	}
	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yy_hh_sss");
		return sdf.format(date);
	}

}
