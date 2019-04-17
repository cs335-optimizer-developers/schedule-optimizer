package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 
 * Class to read in information from Wheaton's catalog, to generate
 * .cur and .det files.
 * 
 * This file is not used at runtime, only to generate files that will
 * be read in at runtime.
 * 
 * @author Naissa Charles
 *
 */

public class OnlineCourseParser {

	public static void main(String[] args) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter("./src/io/descriptions.csv"));
  		
		System.out.println(new File(".").getAbsoluteFile());
	    File input = new File("./src/io/links.dat");
	    System.out.println(input.exists());
		Scanner scan = new Scanner(input);	
		
		while(scan.hasNextLine()) {
			String link = scan.nextLine();
			courseDescrip(writer, link);
		}
		
	    writer.close();
		scan.close();
		
	}
	
	
	public static void courseDescrip (BufferedWriter writer, String info) {
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect(info).get();
			
			String title = doc.title();
			
			System.out.println ("Title : " + title );
			
						
			String html = "<div <p class = courseblocktitle noindent> <strong></strong> </p> ";
			
			Document docs = Jsoup.parseBodyFragment(html);
			
			@SuppressWarnings("unused")
			Element body = docs.body(); 
			
			Elements descriptions = doc.getElementsByClass("courseblockdesc noindent");
			Elements metadata = doc.getElementsByClass("courseblocktitle noindent");

			for (int i = 0; i < descriptions.size(); i++) {
				Element datum = metadata.get(i);
				String s = datum.text();
				s = s.replaceAll(",", "");
				s = s.replaceAll("\"", "");
				s = s.replaceFirst(" ", "");
				s = s.replaceFirst(" ", "");
				s = s.replace('.', ',');
				s = s.replaceAll(",,", ",");
				writer.append(s);
				
				Element description = descriptions.get(i);
				String d = description.text().replaceAll("\"","");
				writer.append("," + "\"" + d + "\"" + '\n');
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}