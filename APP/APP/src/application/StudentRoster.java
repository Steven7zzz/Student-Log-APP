package application;

import java.io.*;
import java.util.*;

public class StudentRoster {
	ArrayList<Student> students = new ArrayList<Student>();
	
	public void load_roster(String filename) {
		Scanner infile = null;
		try {
			infile = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(0);
		}
	
		Student s;

		while (infile.hasNextLine()) {
			String line = infile.nextLine();
			StringTokenizer token = new StringTokenizer(line, ", ");

			String lname = token.nextToken();
			String fname = token.nextToken();
			s = new Student(fname, lname);
			students.add(s);
		}

		infile.close();
	}
	
	public List<String> print_collection() {
		List<String> ll = new ArrayList<String>();
		System.out.println("**** Those students on roster ****");
		Iterator <Student> iter = students.iterator();
		while (iter.hasNext()) {
			Student s = iter.next();
            System.out.println(s.getLastName()+", "+s.getFirstName());
            ll.add(s.getLastName()+", "+s.getFirstName());
        }
		
		return ll;
	}
	
	public String print_count() {
		System.out.println("Student count: "+students.size());
		return "Student count: "+students.size();
	}
	
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	
	public void setStudents(ArrayList<Student> s) {
		this.students = s;
	}
}
