//Pazuzu Jindrich
//Reads from metro url and extracts bus numbers, bus routes, and bus stops

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Bus_Routes {
	 public static void main(String[] args) throws Exception {
		 Scanner reader = new Scanner(System.in);
		 
		//create connection
        URLConnection transit = new URL("https://www.communitytransit.org/busservice/schedules/").openConnection();
        transit.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
       
        BufferedReader in = new BufferedReader(new InputStreamReader(transit.getInputStream()));
        String inputLine = "";
        String text = "";
        while ((inputLine = in.readLine()) != null) {
            text += inputLine + "\n";

        }
        in.close();
        //close connection

/*
 * User input first letter of destination
 * Program displays all viable locations and corresponding bus numbers
 */
        System.out.println("Please enter a letter that your destinations start with: ");
        String destn = reader.next();
        Pattern pttn_destn = Pattern.compile("<hr id=\".*\" />\\s*<h3>(" + destn + ".*)</h3>\\s*"
        		+ "((<div class=\".*\">\\s*<div class=\".*\">\\s*<strong>\\s*<a href=\".*\"( class=&quot;text-success&quot;)?>.*</a>\\s*</strong>\\s*</div>\\s*"
        		+ "<div class=\".*\">.*</div>\\s*</div>\\s*)*)");
        Matcher matcher = pttn_destn.matcher(text);
        while(matcher.find()) {
            System.out.println("Destination: " + matcher.group(1));
            String string = matcher.group(2);
           // System.out.println(matcher.group(2));
            
            Pattern pttn_bus = Pattern.compile("<div class=\".*\">\\s*<div class=\".*\">\\s*<strong>\\s*<a href=\".*\"( .*)?>(.*)</a>"
            		+ "\\s*</strong>\\s*</div>\\s*"
            		+ "<div class=\".*\">.*</div>\\s*</div>\\s*");
            Matcher bus_matcher = pttn_bus.matcher(string);
            while(bus_matcher.find()) {
            	System.out.println("Bus Number: " + bus_matcher.group(2));
            }
            
            System.out.println("+++++++++++++++++++++++++++++++++++");
	    }	 
	 
       
/***********************************************************************************************************************************************************************************************/

        
 /*
  * User enters route ID
  * Program outputs link to bus schedule/route
  * Program outputs all bus stops
  */
        System.out.println("Please enter route ID as a string: ");
        String link = "https://www.communitytransit.org/busservice/schedules/route/" + reader.next();
        System.out.println("The link to your route is: " + link + "\n"); 
        
        
        //create connection
        URLConnection route = new URL(link).openConnection();
        route.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        
        BufferedReader input = new BufferedReader(new InputStreamReader(route.getInputStream()));
        String inputLine2 = "";
        String text2 = "";
        while ((inputLine2 = input.readLine()) != null) {
            text2 += inputLine2 + "\n";

        }
        in.close();
        //close connection
	 

        Pattern pttn1 = Pattern.compile("<thead>\\s*<tr>\\s*<td colspan=\".*\">\\s*"
        		+ "<h2>Weekday<small>(.*)</small></h2>\\s*"
        		+ "</td>\\s*</tr>\\s*"
        		+ "<tr>\\s*\\s*((<th class=\".*\">\\s*"
        		+ "<span class=\".*\">\\s*"
        		+ "<i class=\".*\"></i>\\s*"
        		+ "<strong class=\".*\">.*</strong>\\s*"
        		+ "</span>\\s*"
        		+ "<p>(.*)</p>\\s*</th>\\s*)*)");
        Matcher m2 = pttn1.matcher(text2);
        while(m2.find()) {
        	int num = 1;
        	System.out.println("Destination: " + m2.group(1));
        	String string2 = m2.group(2);
        	Pattern pttn2 = Pattern.compile("<th class=\".*\">\\s*"
        		+ "<span class=\".*\">\\s*"
        		+ "<i class=\".*\"></i>\\s*"
        		+ "<strong class=\".*\">.*</strong>\\s*"
        		+ "</span>\\s*"
        		+ "<p>(.*)</p>\\s*</th>\\s*");
        	Matcher m3 = pttn2.matcher(string2);
        	while(m3.find()) {
        		System.out.println("Stop Number " + num + " is: " + m3.group(1));
        		num++;
        	}
            System.out.println("+++++++++++++++++++++++++++++++++++");
        }
	 }
}
/***********************************************************************************************************************************************************************************************/