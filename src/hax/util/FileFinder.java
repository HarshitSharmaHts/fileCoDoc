package hax.util;

import java.io.File;
import java.util.ArrayList;

/*!
 * a class to provide a kind of search that is
 * able to search all the files belonging to
 * specific type or a specific keyword.
 */

public class FileFinder
{

	public static ArrayList<String> stringArrayList = new ArrayList<>();            //!< A String ArrayList stringArrayList to hold the absolute path of all found files.

	/**
	 *
	 * static method returns void, this method is capable of finding files with name in
	 * source directory. e.g.. if KEYWORD xyz is used than all files containing xyz in
	 * it's name will be fetched and if *.xyz is used as filename than all files with
	 * .xyz extension will be fetched.
	 *
	 * @param sourcePath a String which contains the absolute path of directory, where to search KEYWORD.
	 * @param fileName a String which is actually a KEYWORD.
	 */
	public static void fileFinder(String sourcePath,String fileName)
	{
		boolean flag = false;
		if(fileName.contains("*."))
			flag = true;
		File root = new File(sourcePath);
		if(!root.exists()) {
			return;
		}
			File [] list_file = root.listFiles();

		if(list_file == null)
			return;

		for(File var : list_file)
		{
			if(var.isDirectory())
			{
				fileFinder(var.getAbsolutePath(),fileName);
			}
			else
			{
				String file_name=var.getName();
				if(flag)
				{
					if(file_name.endsWith(getExtension(fileName)))
						stringArrayList.add(var.getAbsolutePath());
				}
				else if(file_name.toLowerCase().contains(fileName.toLowerCase()))
					stringArrayList.add(var.getAbsolutePath());
			}
		}
	}

	/**
	 *
	 * a method which returns String,
	 * returned String will contain the extension of file.
	 *
	 * @param str a String of File Name.
	 * @return String, extension of file.
	 */
	private static String getExtension(String str)
	{
		int lastIndex = str.lastIndexOf('.');
		String extension = "";
		for(int i = lastIndex ; i < str.length() ; i ++)
			extension += str.charAt(i);
		return extension;
	}
}