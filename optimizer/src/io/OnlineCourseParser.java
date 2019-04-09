package io;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;




public class OnlineCourseParser {

//import java.io.IOException;


public static class CourseParser {
	/*throw exception (declaration)*/
	public static void main(String[] args) throws IOException {
		
		/*Document courseAZ = null;
		
		
			courseAZ = Jsoup.connect("https://catalog.wheaton.edu/azindex/").get();
				
				
			String titles = courseAZ.title();
				
			System.out.println ("Title : " + titles ); */
		
			File input = new File ("majorlinks.txt");
			
			Document courseAZ = Jsoup.parse(input, "majors", " ");
			
			Elements links = courseAZ.select("a[href]");
			
			for (Element link: links) {
				
				//method call to courseDescrip. Will look like courseDescrip(links.attr("href").toString());
				//courseDescrip(links.attr("href").toString());
							//maybe make a links.attr("href").toString() variable to keep clean
				
				System.out.println("\nlink : " + link.attr("href"));
				System.out.println("Text : " + link.text());
			}
			
			//Document example = null;

			//example = Jsoup.connect(links.attr("href").toString()).get();
		}
	
	
		public static void courseDescrip (String info) {
			
			Document doc = null;
			
			try {
				doc = Jsoup.connect(info).get();
				
				String title = doc.title();
				
				System.out.println ("Title : " + title );
				
							
				String html = "<div <p class = courseblocktitle noindent> <strong></strong> </p> ";
				
				Document docs = Jsoup.parseBodyFragment(html);
				
				Element body = docs.body(); 
				
				Elements paragraphs = doc.getElementsByClass("courseblockdesc noindent");
				for (Element paragraph : paragraphs ) {
					System.out.println("\n descriptions: " + paragraph.text());
					
				}
				
				Elements paragraphs1 = doc.getElementsByClass("courseblocktitle noindent");
				for (Element paragraph : paragraphs1 ) {
					System.out.println("\n course name,number,and credits: " + paragraph.text());
					 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
	}
}


		
