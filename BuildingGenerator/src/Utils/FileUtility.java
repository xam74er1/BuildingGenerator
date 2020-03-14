package Utils;

import java.io.File;

public class FileUtility {
	
	public static boolean createFileIfNotExiste(String path) {
		
		File f = new File(path);
		
		if(!f.exists()) {
			f.mkdirs();
			return true;
		}
		
		return false;
	}

}
