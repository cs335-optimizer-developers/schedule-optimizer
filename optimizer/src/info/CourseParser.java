package info;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class CourseParser {
	
	public static void main(String[] args) {
		Document doc = null;
		
		try {
			doc = Jsoup.connect("https://catalog.wheaton.edu/course-descriptions/csci/").get();
			
			String title = doc.title();
			
			System.out.println ("Title : " + title );
			
			/*Elements links = doc.select("a[href]");
			
			for (Element link: links) {
				System.out.println("\nlink : " + link.attr("href"));
				System.out.println("Text : " + link.text());
				
				
			}*/
			
			//String html = "<div <p class = courseblocktitle noindent> <strong></strong> </p> ";
			
			//Document docs = Jsoup.parseBodyFragment(html);
			
			//Element body = docs.body();
			
			Elements paragraphs = doc.getElementsByClass("courseblockdesc noindent");
			for (Element paragraph : paragraphs ) {
				System.out.println("\n descriptions: " + paragraph.text());
				
			}
			
			
			Elements paragraphs1 = doc.getElementsByClass("courseblocktitle noindent");
			for (Element paragraph : paragraphs1 ) {
				System.out.println("\n course name, number, and credits: " + paragraph.text());
				 
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
