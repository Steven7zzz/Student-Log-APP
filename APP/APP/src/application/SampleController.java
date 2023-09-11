package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class SampleController {
	
	AttendanceLog log = new AttendanceLog();
	StudentRoster roster = new StudentRoster();
	List<String> ll = new ArrayList<String>();
	AttendanceApp app = new AttendanceApp();
	
	@FXML public TextArea outputs;
	@FXML public Button g;
	@FXML public Button h;
	@FXML public Button i;
	@FXML public Button j;
	@FXML public Button k;
	@FXML public Button l;
	@FXML public Button m;
	@FXML public Button n;
	@FXML public Button o;
	@FXML public Button p;
	@FXML public Button q;
	@FXML public Button r;
	@FXML public Button s;
	@FXML public TextField input;
	
	
    public void initialize() {
    	Button[] buttons = {g,h,i,j,k,l,m,n,o,p,q,r,s};
    	
        for (Button b : buttons) {
        	b.setDisable(true);
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (log.getLogs().size() != 0 && roster.getStudents().size() != 0) {
                for (Button b : buttons) {
                	b.setDisable(false);
                }
            } else {
                for (Button b : buttons) {
                	b.setDisable(true);
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

	
	public void exit(ActionEvent e) {
		Platform.exit();
	}
	
	public void print() {
        if (ll.size() == 0) {
        	outputs.appendText("Nothing Found\n");
        	outputs.appendText("There were 0 records for this query\n");
        }
        else {
	        for (String x : ll) {
	        	outputs.appendText(x + "\n");
	        }
	        outputs.appendText("There were " + ll.size() + " records for this query\n");
        }
	}
	
	public void a(ActionEvent e) {
		outputs.clear();
	    outputs.appendText("Please Enter Log File Path\n");
	    
	    input.setOnAction(event -> {
	        String in = input.getText();
	        
			if (log != null) {
				log.getLogs().clear();
			}
			
		    log.load_log(in);
		    app.setSwipe(in);
		    
		    outputs.appendText("Load Success\n");
		    
		    input.clear();
	    });
	    
	}
	
	public void b(ActionEvent e) {
		outputs.clear();
	    outputs.appendText("Print Log Collection\n");
	    ll.clear();
	    ll = log.print_collection();
	    for (String x : ll){
	    	outputs.appendText(x + "\n");
	    }
	}
	
	public void c(ActionEvent e) {
		outputs.clear();
	    outputs.appendText("Print Log Count\n");
	    outputs.appendText(log.print_count() + "\n");
	}
	
	public void d(ActionEvent e) {
		outputs.clear();
	    outputs.appendText("Please Enter Roster File Path\n");
	    
	    input.setOnAction(event -> {
	        String in = input.getText();
	        
			if (roster != null) {
				roster.getStudents().clear();
			}
			
		    roster.load_roster(in);
		    app.setRoster(in);
		    
		    outputs.appendText("Load Success\n");
		    
		    input.clear();
	    });
	}
	
	public void e(ActionEvent e) {
		outputs.clear();
	    outputs.appendText("Print Roster Collection\n");
	    ll.clear();
	    ll = roster.print_collection();
	    for (String x : ll){
	    	outputs.appendText(x + "\n");
	    }
	}
	
	public void f(ActionEvent e) {
		outputs.clear();
	    outputs.appendText("Print Roster Count!\n");
	    outputs.appendText(roster.print_count() + "\n");
	}
	
	public void g(ActionEvent e) {
		outputs.clear();
		outputs.appendText("****** Students missing in class *************\n");
		ll.clear();
	    ll = app.list_students_not_in_class();
	}
	
	public void h(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter student's name:\n");
    	
    	ll.clear();
    	
	    input.setOnAction(event -> {
	        String in = input.getText();
	     
	        ll = app.list_all_times_checking_in_and_out(in);        
	        outputs.appendText("****** List all swipe in and out for a student *******\n");  

		    input.clear();
	    });

	}
	
	public void i(ActionEvent e) {
		outputs.clear();
	    outputs.appendText("****** Check in times for all students who attended***\n");
	    
	    ll.clear();
	    	     
	    ll = app.list_all_times_checked_in();      
	}
	
	public void j(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter a time:\n");
    	
    	ll.clear();
    	
	    input.setOnAction(event -> {
	        String in = input.getText();
	     
	        ll = app.list_students_late_to_class(in);     
	        outputs.appendText("****** Students that arrived late ********************\n");  

		    input.clear();
	    });
	}
	
	public void k(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter a date:\n");
    	
    	ll.clear();
    	
	    input.setOnAction(event -> {
	        String in = input.getText();
	     
	        try {
				ll.add(app.get_first_student_to_enter(in));
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
	        
	        outputs.appendText("**** First student to enter on " + in + " ****\n");  

		    input.clear();
	    });
	}
	
	public void l(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter a name:\n");
    	
    	ll.clear();
    	
	    input.setOnAction(event -> {
	        String in = input.getText();
	     
	        ll = app.print_attendance_data_for_student(in);     
	        outputs.appendText("********* Looking up Student Attendance Data ***********\n");  

		    input.clear();
	    });
	}
	
	public void m(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter a name:\n");
    	
    	ll.clear();
    	
	    input.setOnAction(event -> {
	        String name = input.getText();
		    input.clear();
		    
		    outputs.appendText("Please enter a date:\n");
		    
		    input.setOnAction(eventtwo -> {
			    String date = input.getText();
			    input.clear();
			    
			    outputs.appendText("**** Looking to see if Student was present on date ****\n");
			    
			    if (app.is_present(name, date)) {
			    	outputs.appendText("True\n");
			    }
			    else {
			    	outputs.appendText("False\n");
			    }
		    });
 
	    });
	}
	
	public void n(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter a date:\n");
    	
    	ll.clear();
    	
	    input.setOnAction(event -> {
	        String in = input.getText();
	     
	        ll = app.list_all_students_checked_in(in);  
	        outputs.appendText("**** Students present on this date ****\n");  

		    input.clear();
	    });
	}
	
	public void o(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter a date:\n");
    	
    	ll.clear();

	    input.setOnAction(event -> {
	        String date = input.getText();
		    input.clear();
		    
		    outputs.appendText("Please enter a time:\n");
		    
		    input.setOnAction(eventtwo -> {
			    String time = input.getText();
			    input.clear();
			    
			    outputs.appendText("**** Those present on date & before a time assigned ****\n");
			    
			    ll = app.list_all_students_checked_in_before(date, time);
		    });
		    
	    });
	}
	
	public void p(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter a count:\n");
    	
    	ll.clear();
    	
	    input.setOnAction(event -> {
	        String in = input.getText();
	     
	        ll = app.list_students_attendance_count(Integer.parseInt(in));
	        outputs.appendText("**** Those who attended " + in + " class/classes ****\n");  

		    input.clear();
	    });
	}
	
	public void q(ActionEvent e) {
		outputs.clear();
    	outputs.appendText("Please enter a date:\n");
    	
    	ll.clear();
    	
	    input.setOnAction(event -> {
	        String in = input.getText();
	     
	        try {
				ll.add(app.get_first_student_to_enter(in));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	        outputs.appendText("**** First student to enter on " + in + " ****\n");  

		    input.clear();
	    });
	}
	
	public void r(ActionEvent e) {
	    
	    if(ll.size() == 0) {
	    	outputs.appendText("Nothing Found\n");
	    }
	    else {
		    for(String x : ll) {
		    	outputs.appendText(x + "\n");
		    }
	    }
	}
	
	public void s(ActionEvent e) {
	    outputs.appendText("There were " + ll.size() + " records for this query\n");
	}
}


/*
/Users/steven/Desktop/Attendance_Collection/src/data/dataAllShow1stClassInandOut.txt
*/