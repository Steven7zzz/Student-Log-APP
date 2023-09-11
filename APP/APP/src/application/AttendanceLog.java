package application;
import java.io.*;
import java.util.*;

public class AttendanceLog {
	ArrayList<Log> logs = new ArrayList<Log>();

	public void load_log(String filename) {
		Scanner infile = null;
		try {
			infile = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(0);
		}

		Log l;

		while (infile.hasNextLine()) {
			String line = infile.nextLine();
			StringTokenizer token = new StringTokenizer(line, ", ");

			String last = token.nextToken();
			String first = token.nextToken();
			String time = token.nextToken();
			String date = token.nextToken();
			l = new Log(first, last, time, date);
			logs.add(l);
		}

		infile.close();
	}

	public List<String> print_collection() {
		
		List<String> ll = new ArrayList<String>();
		System.out.println("** This is the entire Collection Data currently stored **");
		
	    HashMap<String, ArrayList<Log>> logsByStudent = new HashMap<>();
	    for (Log log : logs) {
	        String studentName = log.getFullName();
	        if (!logsByStudent.containsKey(studentName)) {
	            logsByStudent.put(studentName, new ArrayList<Log>());
	        }
	        logsByStudent.get(studentName).add(log);
	    }
	    
	    for (String studentName : logsByStudent.keySet()) {
	    	String s = "";
	        System.out.print(studentName + " [");
	        s += (studentName + " [");
	        ArrayList<Log> studentLogs = logsByStudent.get(studentName);
	        for (int i = 0; i < studentLogs.size(); i++) {
	            System.out.print("'" + studentLogs.get(i).getTimeAndDate() + "'");
	            s += ("'" + studentLogs.get(i).getTimeAndDate() + "'");
	            if (i < studentLogs.size() - 1) {
	                System.out.print(", ");
	                s += (", "); 
	            }
	        }
	        System.out.println("]");
	        s+= ("]");
	        
	        ll.add(s);
	    }
	    
	    return ll;
	}
	
	public String print_count() {
		System.out.println("Log count: "+logs.size());
		return "Log count: " + logs.size();
	}
	
	public ArrayList<Log> getLogs() {
		return this.logs;
	}
	
	public void setLogs(ArrayList<Log> l) {
		this.logs = l;
	}
}
