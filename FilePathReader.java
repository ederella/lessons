package recursion;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilePathReader {

	public static void main(String... strings) {

		List<String> files = getFilesInFolder("C:\\Users\\Public");
		
		for (String filePath : files) {
			System.out.println(filePath);
		}
	}

	private static List<String> getFilesInFolder(String path) {
		File folder = new File(path);
		List<String> files = new ArrayList<String>();
	
		getFilesInFolder(folder, files);
		
		return files;
	}
	
	private static void getFilesInFolder(File file, List<String> files) {
		if(file.isFile()) {
			 files.add(file.getAbsolutePath());
		}
		
		if(file.isDirectory()) {
			File[] filesInFolderArray = file.listFiles();
			if(filesInFolderArray == null)
				return;
			
			List<File> filesInFolder = Arrays.asList(filesInFolderArray);
			
			for (File fileInFolder : filesInFolder) {
				getFilesInFolder(fileInFolder, files);
			}
		}
	}
}
