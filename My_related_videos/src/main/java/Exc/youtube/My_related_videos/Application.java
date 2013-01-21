package Exc.youtube.My_related_videos;

import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.regex.*;
/**
 * This Application search related video from www.youtube.com
 * and shows in the following format:
 * <p>
 * 	- Url(related video), Title video and views.
 */
public class Application {

	static String[] Arr_line = new String[5000];
	static String[] relations_URL = new String[100];
	static String[] relations_Title = new String[100];
	static String[] relations_Views = new String[100];
	
	//private static final Logger Logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		
		System.out.println("Enter Url video from youtube. ");
		System.out.println("For example: http://www.youtube.com/watch?v=SdavEW2vSis");
		
		Scanner scan = new Scanner(System.in);
		String url_str = scan.nextLine();
		scan.close();
		/*
		 * Validation of the entered URL
		 */
		url_str = checkURL(url_str);
		
		/*
		 * Scan and read entered URL
		 */
		read_scan(url_str);
	}// end main
	
	static void read_scan(String url_str){
		int i;
		int index;	
		int j;
		Pattern pat;
		Matcher mat;
		boolean flag;
		String pointer;

		i = 0;
		j = 0;
		index = 0;
		flag = false;
		pointer = "related-video yt-uix-contextlink  yt-uix-sessionlink";
		try {
			URL url = new URL(url_str);
			BufferedReader buf = new BufferedReader(new InputStreamReader(url.openStream()));
			
			//Line-by passing on the data 
			while ((Arr_line[i] = buf.readLine()) != null){
				pat = Pattern.compile(pointer);
				mat = pat.matcher(Arr_line[i]);
				if(mat.find()){
					flag=true;
					GetURL(Arr_line[i],index);
					index++;
					}
				if(j==5)
					GetTitle(Arr_line[i],index-1);
				if (j==6){
					GetViews(Arr_line[i], index-1);
					j=0;
					flag=false;
				}
				if (flag) j++;
				i++;
			}
			buf.close();
			
		} catch (MalformedURLException e) {
			System.out.println("URL does not exist.");
		} catch (IOException e) {
			System.out.println("Wrong URL. Check whether the URL.");
		}
		
	}//end void read_url()
/**
 *  Function definition video links in the specified line
 */
 static void GetURL(String Incoming, int index){
	 	int chars;
		int start = 21;
		int stop = 41;
		char b[]=new char[20];

		Incoming.getChars(start, stop, b, 0);
		relations_URL[index]="http://www.youtube.com";
		for (chars=0; chars<20; chars++)
			relations_URL[index] = relations_URL[index]+ b[chars]; 
		System.out.print((index+1) + ") URL : "+relations_URL[index]);
				
 } //End GetURL()
 
 /**
  * Function definition video titles
  */
 static void GetTitle(String Incoming, int index){
	 	
	 	int chars;
	 	int start;
		int stop;
		char b[]=new char[600];
		
		String double_quotes="\"";
		start = Incoming.indexOf("title=");
		stop = Incoming.lastIndexOf("</span>");
		Incoming.getChars(start, stop, b, 0);
		relations_Title[index]="";
		chars = 7;
		while(b[chars]!=double_quotes.charAt(0)){
			
			relations_Title[index] = relations_Title[index]+ b[chars]; 
			chars++;
			if (relations_Title[index].length()==50){
				String[] word=relations_Title[index].split(" ");
				relations_Title[index]="";
				for (int i=0;i<word.length-1;i++)
					relations_Title[index]+= word[i] + " "; 
				b[chars]=double_quotes.charAt(0);	
			}
		}
		
		System.out.print("       Title : "+relations_Title[index]);
}// End GetALT()
 
 
 /** 
  * Function definition video views 
  */
 static void GetViews(String Incoming, int index){
	 	int chars;
		int start;
		int stop;
	
		char b[]=new char[50];
		start = Incoming.indexOf(";")+1;
		stop = Incoming.lastIndexOf("</span>");
		Incoming.getChars(start, stop, b, 0);
		relations_Views[index]="";
		for (chars=0; chars<b.length; chars++)
			relations_Views[index] = relations_Views[index]+ b[chars]; 
		System.out.println("        Views : "+relations_Views[index]);
				
} //End GetURL()
 
	/**
	 * Function checking the entered URL and its accessories to the youtube.com 
	 */
 	static String checkURL(String url){
		
		Pattern pat;
		Matcher mat;
		String pattern_Url = "http://www.youtube.com/watch\\?v=(.){11}";
		pat = Pattern.compile(pattern_Url);
		mat = pat.matcher(url);
		if (mat.find()){
			System.out.println("Ok...Let's go.");
			return url;
		}
		else{
			return url = "";
			}
	}// End checkURL()
	
}// End class Application
