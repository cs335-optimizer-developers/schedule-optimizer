package io;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
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
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter("./src/io/descriptions.txt"));
	    BufferedWriter pwriter = new BufferedWriter(new FileWriter("./src/io/prereqs.txt"));
  		
		System.out.println(new File(".").getAbsoluteFile());
	    File input = new File("./src/io/links.dat");
	    System.out.println(input.exists());
		Scanner scan = new Scanner(input);	
		
		while(scan.hasNextLine()) {
			String link = scan.nextLine();
			courseDescrip(pwriter, writer, link);
		}
		
	    writer.close();
	    pwriter.close();
		scan.close();
		
	}
	
	
	public static void courseDescrip (BufferedWriter pwriter, BufferedWriter writer, String info) {
		
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
				writer.append(s + "| ");
				pwriter.append(s.substring(0, s.indexOf(',')) + ", ");
				
				Element description = descriptions.get(i);
				//Nodes prereqs = description.getElementsByClass("bubblelink code");
				boolean hasPrereq = false;
				for(org.jsoup.nodes.Node p: description.childNodes()) {
					if(p instanceof TextNode) {
						if(((TextNode) p).text().contains("Prerequisite:")) {
							hasPrereq = true;
						}
					} else if(p instanceof Element && hasPrereq) {
						String pre = p.attr("title").replaceAll("\\s", "");
						String space = (char)160 + "";
						pwriter.append(pre.replaceAll(space, "") + " ");
					}

				}
				String d = description.text().replaceAll("\"","");
				writer.append(d + '\n');
				pwriter.append("\n");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}