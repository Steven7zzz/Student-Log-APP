package application;

import java.time.LocalTime;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class AttendanceApp {
	StudentRoster roster = new StudentRoster();
	AttendanceLog log = new AttendanceLog();
	
	public AttendanceApp(String rosterFilename, String swipedataFilename) {
		roster.load_roster(rosterFilename);
		log.load_log(swipedataFilename);
    }
	
	public AttendanceApp() {
	}

	
	public void setRoster(String rosterFilename) {
		roster.getStudents().clear();
		
		roster.load_roster(rosterFilename);
	}
	
	public void setSwipe(String swipeFilename) {
		if (log != null) {
			log.getLogs().clear();
		}
		
		log.load_log(swipeFilename);
	}
	
	public StudentRoster getRoster() {
		return this.roster;
	}
	
	public AttendanceLog getSwipe() {
		return this.log;
	}
	
	public List<String> list_students_not_in_class() {
		System.out.println("****** Students missing in class *************");
		
	    ArrayList<String> absentStudents = new ArrayList<String>();
	    HashSet<String> presentStudents = new HashSet<String>();

	    for (Log l : log.getLogs()) {
	        presentStudents.add(l.getFullName());
	    }
	    
	    for (Student s : roster.getStudents()) {
	        if (!presentStudents.contains(s.getFullName())) {
	            absentStudents.add(s.getFullName());
	        }
	    }
	    
	    print_query_list(absentStudents);
	    print_count(absentStudents);
	    
	    return absentStudents;
	}
	
	
	public List<String> list_all_times_checking_in_and_out(String studentName) {
		ArrayList<String> s = new ArrayList<String>();
		System.out.println("****** List all swipe in and out for a student *******");
		Iterator <Log> iter = log.getLogs().iterator();
		while(iter.hasNext()) {
			if (iter.next().getFullName().equals(studentName)) {
				s.add(studentName + ", " + iter.next().getTimeAndDate());
			}
		}	
		
		print_query_list(s);
	    print_count(s);
	    
		return s;
	}
	
	// --------------------------------------------------------------------------------
	
	public List<String> list_all_times_checked_in() {
		ArrayList<String> s = new ArrayList<String>();
	    Iterator<Log> iter = log.getLogs().iterator();
	    Set<String> checkedInStudents = new HashSet<>();
	    System.out.println("****** Check in times for all students who attended***");
	    while (iter.hasNext()) {
	        Log currentLog = iter.next();
	        String studentName = currentLog.getFullName();
	        if (!checkedInStudents.contains(studentName)) {
	            s.add(currentLog.getPrint());
	            checkedInStudents.add(studentName);
	        }
	    }
	    
	    print_query_list(s);
	    print_count(s);
	    
	    return s;
	}

	
	// --------------------------------------------------------------------------------
	
	public List<String> list_students_late_to_class(String timestamp) {
		ArrayList<String> s = new ArrayList<String>();
		ArrayList<Log> logs = new ArrayList<Log>();
	    Iterator<Log> iter = log.getLogs().iterator();
	    Set<String> checked = new HashSet<>();
	    while (iter.hasNext()) {
	        Log currentLog = iter.next();
	        String studentName = currentLog.getFullName();
	        if (!checked.contains(studentName)) {
	            logs.add(currentLog);
	            checked.add(studentName);
	        }
	    }
		
		LocalTime time = LocalTime.parse(timestamp);
	    Set<String> checkedInStudents = new HashSet<>();
	    System.out.println("****** Students that arrived late ********************");
	    for (Log log : logs) {
	        String studentName = log.getFullName();
	        LocalTime logTime = LocalTime.parse(log.getSwipeTime());
	        
	        if (logTime.isAfter(time)) {
	            s.add(log.getPrint());
	            checkedInStudents.add(studentName);
	        }
	    }
	    
	    print_query_list(s);
	    print_count(s);
	    
	    return s;
	}

	// --------------------------------------------------------------------------------

	public String get_first_student_to_enter(String specificDate) throws ParseException {
	    
		System.out.println("**** First student to enter on " + specificDate + " ****");
		
	    List<Log> logsOnSpecificDate = new ArrayList<Log>();
	    for (Log log : log.getLogs()) {
	        if ( log.getSwipeDate().replaceAll("\\s+","").equals(specificDate)) {
	            logsOnSpecificDate.add(log);
	        }
	    }

	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    
	    if (!logsOnSpecificDate.isEmpty()) {
	        Log firstLog = logsOnSpecificDate.get(0);
	        Date firstTime = dateFormat.parse(firstLog.getSwipeTime());
	        

	        for (Log log : logsOnSpecificDate) {
	            Date logTime = dateFormat.parse(log.getSwipeTime());
	            if (logTime.before(firstTime)) {
	                firstTime = logTime;
	                firstLog = log;
	            }
	        }
	        System.out.println(firstLog.getFullName());
	        return firstLog.getFullName();
	    } 
	    else {
	    	System.out.println("No student attended on this date");
	        return null; 
	    }
	}


	// --------------------------------------------------------------------------------
	
	public List<String> print_attendance_data_for_student(String fullName) {
	    ArrayList<Log> logsForStudent = new ArrayList<Log>();
	    
	    for (Log log : this.getSwipe().getLogs()) {
	        if (log.getFullName().equals(fullName)) {
	            logsForStudent.add(log);
	        }
	    }
	    List<String> ll = new ArrayList<String>();
	    String s = "";
	    
	    if (logsForStudent.isEmpty()) {
	        System.out.println("No student of this name in the attendance");
	        ll.add("No student of this name in the attendance");
	    } 
	    else {
	        System.out.println("********* Looking up Student Attendance Data ***********");
	        
	        System.out.print(fullName + " [");
	        s += fullName + " [";
	        
	        for (int i = 0; i < logsForStudent.size(); i++) {
	            System.out.print("'" + logsForStudent.get(i).getTimeAndDate() + "'");
	            s += "'" + logsForStudent.get(i).getTimeAndDate() + "'";
	            if (i < logsForStudent.size() - 1) {
	                System.out.print(", ");
	                s += ", ";
	            }
	        }
	        System.out.println("]");
	        s += "]";
	        ll.add(s);
	    }
	    return ll;
	}
	
	// --------------------------------------------------------------------------------
	
	public boolean is_present(String studentName, String date) {
		System.out.println("**** Looking to see if Student was present on date ****");
		Iterator <Log> iter = log.getLogs().iterator();
		while (iter.hasNext()) {
			Log l = iter.next();
			if (l.getFullName().equals(studentName) && l.getSwipeDate().equals(date)) {
				System.out.println(true);
				return true;
			}
		}
		System.out.println(false);
		return false;
	}
	
	// --------------------------------------------------------------------------------
	
	
	public List<String> list_all_students_checked_in(String date) {
		List<String> s = new ArrayList<String>();
		System.out.println("**** Students present on this date ****");
		Iterator <Log> iter = log.getLogs().iterator();
		Set<String> checkedInStudents = new HashSet<>();
		while (iter.hasNext()) {
			Log l = iter.next();
			if (l.getSwipeDate().equals(date)) {
				if (!checkedInStudents.contains(l.getFullName())) {
					s.add(l.getFullName());
					checkedInStudents.add(l.getFullName());
				}
			}
		}
		
	    print_query_list(s);
	    print_count(s);
	    
		return s;
	}
	
	// --------------------------------------------------------------------------------
	
	public List<String> list_all_students_checked_in_before(String date, String time) {
		List<String> s = new ArrayList<String>();
		ArrayList<Log> logs = new ArrayList<Log>();
	    Iterator<Log> iter = log.getLogs().iterator();
		Set<String> checkedInStudents = new HashSet<>();
		while (iter.hasNext()) {
			Log l = iter.next();
			if (l.getSwipeDate().equals(date)) {
				if (!checkedInStudents.contains(l.getFullName())) {
					logs.add(l);
				}
			}
		}
		
		LocalTime times = LocalTime.parse(time);
		System.out.println("**** Those present on date & before a time assigned ****");
		Set<String> checked = new HashSet<>();
		for(Log l : logs ){
			LocalTime logTime = LocalTime.parse(l.getSwipeTime());
			if (logTime.isBefore(times)) {
				if (!checked.contains(l.getFullName())) {
					s.add(l.getFullName());
					checked.add(l.getFullName());
				}
			}
		}
		
	    print_query_list(s);
	    print_count(s);
	    
		return s;
	}
	
	// --------------------------------------------------------------------------------
	
	public List<String> list_students_attendance_count(int numberOfDays) {
	    List<String> rosterStudents = new ArrayList<String>();
	    
	    for (Student s : roster.getStudents()) {
	    	rosterStudents.add(s.getFullName());
	    }

	    HashMap<String, Integer> attendanceCountByStudent = new HashMap<>();

	    for (Log log : log.getLogs()) {
	        String studentName = log.getFullName();
	        attendanceCountByStudent.put(studentName, attendanceCountByStudent.getOrDefault(studentName, 0) + 1);
	    }

	    List<String> attended = new ArrayList<String>();

	    for (String studentName : rosterStudents) {
	        int attendanceCount = attendanceCountByStudent.getOrDefault(studentName, 0);
	        if (numberOfDays != 0) {
		        if (attendanceCount >= numberOfDays) {
		        	attended.add(studentName);
		        }
	        }
	        else if (attendanceCount == 0) {
	        	attended.add(studentName);
	        }
	    }
	    
	    System.out.println("**** Those who attended " + numberOfDays + " class/classes ****");

	    print_query_list(attended);
	    print_count(attended);	    
	    
	    return attended;
	}

	// --------------------------------------------------------------------------------

	public void print_query_list (List<String> s) {
		
		if (s.size() == 0) {
			System.out.println("Nothing found");
		}
		
		for (String x : s) {
			System.out.println(x);
		}
	}
	
	public void print_count (List<String> s) {
		System.out.println("There were " + s.size() + " records for this query");
	}
	
}
