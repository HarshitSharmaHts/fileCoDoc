package hax.util;

import java.io.*;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/*!
 *
 * this class uses an API known as iText, to convert text int PDF.
 * class is used to write input file data(text) into PDF file.
 *
 */

public class PDF
{
	/**
	 *
	 * this method is used to open each and every file
	 * using arraylist and write its text into PDF.
	 *
	 * @param stringArrayList a ArrayList of String that contains the
	 *                        absolute path of files from which data has to be written.
	 * @param desc a String of destination file name.
	 */
	public static void convertTextToPDF(ArrayList<String> stringArrayList, String desc)
	{
		Font font1 = new Font();
		font1.setSize(14);
		font1.setStyle(Font.BOLDITALIC);


		Font font2 = new Font();
		font2.setSize(10);
		font2.setStyle(Font.NORMAL);

		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(desc));
			doc.open();

			for (String src : stringArrayList) {
				String meta ="prog : " + new File(src).getName() +"\n";
				String line ="_________________________________________________________________________\n";
				doc.add(new Paragraph(meta,font1));
				doc.add(new Paragraph(line,font2));
				try {
					FileReader fr = new FileReader(src);
					BufferedReader br = new BufferedReader(fr);
					String text;

					while ((text = br.readLine()) != null) {
						doc.add(new Paragraph(text,font2));
					}
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				doc.add(new Paragraph(line,font2));

			}
		}
		catch(DocumentException ex )
		{
			System.out.println(ex.getMessage());
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		}
		doc.close();
	}
}