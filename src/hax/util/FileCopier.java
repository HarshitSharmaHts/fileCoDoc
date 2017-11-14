package hax.util;

import java.io.*;

/*!
 * a class to provide the utility of copying files from one place to another,
 * to our application.
 */

public class FileCopier
{

	private static FileInputStream fin;                     //!< A FileInputStream fin to hold the source file.
	private static FileOutputStream fout;                   //!< A FileOutputStream fout to hold the destination file.


	/**
	 *
	 * static methos returns void, this method
	 * copy a source file into destination file.
	 *
	 * @param src a string which contains source file absolute path.
	 * @param dest a string which contains destination file absolute path.
	 */
	static void copier(String src, String dest)
	{
		File file = new File(src);
		File nf = new File(dest);
		byte b[] = new byte[(int)file.length()];

		try
		{
			fin = new FileInputStream(file);
			try
			{
				fin.read(b);
				try
				{
					fout = new FileOutputStream(nf);
					try
					{
						fout.write(b);
						fout.flush();
					}
					catch(IOException ex)
					{
						System.out.println(ex.getMessage());
					}
				}
				catch(FileNotFoundException ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					try
					{
						fout.close();
					}
					catch(IOException ex)
					{
						System.out.println(ex.getMessage());
					}
				}
			}
			catch(IOException ex)
			{
				System.out.println(ex.getMessage());
			}

		}
		catch(FileNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			try
			{
				fin.close();
			}
			catch(IOException ex)
			{
				System.out.println(ex.getMessage());
			}
		}
	}
}