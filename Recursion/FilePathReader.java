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
		return getFilesInFolder(new File(path));
	}

	private static List<String> getFilesInFolder(File file) {
		List<String> files = new ArrayList<String>();
		if (file.isFile()) {
			files.add(file.getAbsolutePath());
		}

		if (file.isDirectory()) {
			File[] filesInFolderArray = file.listFiles();
			if (filesInFolderArray == null) {
				return files;
			}

			List<File> filesInFolder = Arrays.asList(filesInFolderArray);

			for (File fileInFolder : filesInFolder) {
				files.addAll(getFilesInFolder(fileInFolder));
			}
		}
		return files;
	}
}
