package application;
	
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws ParseException {
		launch(args);

		System.out.println(
				"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
				"0 - Exit program\n"
				+ "(AttendanceLog)\n"
				+ "A - load_log()\n"
				+ "B - print_collection()\n"
				+ "C - print_count()\n"
				+ "\n"
				+ "(StudentRoster)\n"
				+ "D - load_log()\n"
				+ "E - print_collection()\n"
				+ "F - print_count()\n"
				+ "\n"
				+ "(AttendanceApp)\n"
				+ "G - list_students_not_in_class()\n"
				+ "H - list_all_times_checking_in_and_out()\n"
				+ "I - list_all_times_checked_in()\n"
				+ "J - list_students_late_to_class()\n"
				+ "K - get_first_student_to_enter()\n"
				+ "L - print_attendance_data_for_student()\n"
				+ "M - is_present()\n"
				+ "N - list_all_students_checked_in()\n"
				+ "O - list_all_students_checked_in_before()\n"
				+ "P - list_students_attendance_count()\n"
				+ "Q - get_first_student_to_enter()\n"
				+ "R - print_query_list()\n"
				+ "S - print_count()\n"
				);
		
		Boolean running = true;
		Scanner scan = new Scanner(System.in);
		List<String> ll = new ArrayList<String>();
		StudentRoster roster = new StudentRoster();
	    AttendanceLog log = new AttendanceLog();
	    AttendanceApp app = new AttendanceApp();
		
	    while (running) {
	        System.out.println("Enter a command");
	        String in = scan.nextLine();
	        
	        if (in.equals("A")) {
	        	System.out.println("Please enter the file path for log:");
	        	in = scan.nextLine();
	        	log.load_log(in);
	        	app.setSwipe(in);
	        }
	        else if (in.equals("B")) {
	        	if (log.getLogs().size()!=0) {
	        		log.print_collection();
	        	}
	        	else {
	        		System.out.println("Please load log first:");
	        	}
	        }
	        else if (in.equals("C")) {
	        	if (log.getLogs().size()!=0) {
	        		log.print_count();
	        	}
	        	else {
	        		System.out.println("Please load log first:");
	        	}
	        }
	        else if (in.equals("D")) {
	        	System.out.println("Please Enter the File Path for roster:");
	        	in = scan.nextLine();
	        	roster.load_roster(in);
	        	app.setRoster(in);
	        }
	        else if (in.equals("E")) {
	        	if (roster.getStudents().size()!=0) {
	        		roster.print_collection();
	        	}
	        	else {
	        		System.out.println("Please load roster first:");
	        	}
	        }
	        else if (in.equals("F")) {
	        	if (roster.getStudents().size()!=0) {
	        		roster.print_count();
	        	}
	        	else {
	        		System.out.println("Please load roster first:");
	        	}
	        }
	        ///Users/steven/Desktop/Attendance_Collection/src/data
	        else if (in.equals("G")) {
	        	ll = app.list_students_not_in_class();
	        }
	        else if (in.equals("H")) {
	        	System.out.println("Please enter student's name:");
	        	in = scan.nextLine();
	        	ll = app.list_all_times_checking_in_and_out(in);
	        }       
	        else if (in.equals("I")) {
	        	ll = app.list_all_times_checked_in();
	        }
	        else if (in.equals("J")) {
	        	System.out.println("Please enter a time:");
	        	in = scan.nextLine();
	        	ll = app.list_students_late_to_class(in);
	        }
	        else if (in.equals("K")) {
	        	System.out.println("Please enter a date:");
	        	in = scan.nextLine();
	        	ll.clear();
	        	ll.add(app.get_first_student_to_enter(in));
	        }
	        else if (in.equals("L")) {
	        	System.out.println("Please enter student's name:");
	        	in = scan.nextLine();
	        	app.print_attendance_data_for_student(in);
	        }
	        else if (in.equals("M")) {
	        	System.out.println("Please enter student's name:");
	        	in = scan.nextLine();
	        	System.out.println("Please enter a date:");
	        	String d = scan.nextLine();
	        	app.is_present(in, d);
	        } //
	        else if (in.equals("N")) {
	        	System.out.println("Please enter a date:");
	        	in = scan.nextLine();
	        	ll = app.list_all_students_checked_in(in);
	        }
	        else if (in.equals("O")) {
	        	System.out.println("Please enter a date:");
	        	in = scan.nextLine();
	        	System.out.println("Please enter a time:");
	        	String d = scan.nextLine();
	        	ll = app.list_all_students_checked_in_before(in, d);
	        }
	        else if (in.equals("P")) {
	        	System.out.println("Please enter a count:");
	        	in = scan.nextLine();
	        	ll = app.list_students_attendance_count(Integer.parseInt(in));
	        }
	        else if (in.equals("Q")) {
	        	System.out.println("Please enter a date:");
	        	in = scan.nextLine();
	        	ll.clear();
	        	ll.add(app.get_first_student_to_enter(in));
	        }
	        else if (in.equals("R")) {
	        	app.print_query_list(ll);
	        }
	        else if (in.equals("S")) {
	        	app.print_count(ll);
	        }
	        else if (in.equals("0")) {
	            running = false;
	            System.out.println("Program ended");
	        } 
	        else {
	            System.out.println("Invalid command. Please try again.");
	        }
	    }
	    
	    scan.close();
	}
		
}
