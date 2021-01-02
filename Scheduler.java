/**
 * Jenny Long
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Scheduler 
{
    
	
	TreeMap<String,ActiveCourse> schedule;
	
		
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	  
	}
	

	

	public void setDayAndTime(String courseCode, String day, int startTime, int duration) 
			throws UnknownCourseException, InvalidDayException,InvalidDurationException,InvalidTimeException,LectureTimeCollisionException
	{
		
		//create a new active course object here
		ActiveCourse A = new ActiveCourse();
		
		   if (!schedule.containsKey(courseCode))
		   {
		    throw new UnknownCourseException ("the given courseCode cannot be found");
		     
		   }
		   	A = schedule.get(courseCode) ; 
		   	
		   	if (!(day.equalsIgnoreCase("Mon")|| day.equalsIgnoreCase("Tue") || day.equalsIgnoreCase("Wed")|| day.equalsIgnoreCase( "Thur") || day.equalsIgnoreCase("Fri")))
		   	{
		   		throw new InvalidDayException ("Invalid Day \n the string parameter should be \"Mon\" , \"Tue\" , \"Wed\", \"Thur\" or \"Fri\" ");
		   	}
		   	else 
		   	{ A.setLectureDay(day);}
		   	
		     
		    if (startTime < 800 || (startTime + duration*100)> 1700)
			{
			throw new InvalidTimeException ("Start time should not be less than 0800 and End time should not be greater than 1700");
			}
		    	
		    else 
		    { A.setLectureStart(startTime);}
		    
		    
		    
		    if(duration < 1 || duration > 3)
		    {
		    	throw new InvalidDurationException ( "lecture duration should be 1,2, or 3 hours");
		    }
		    else 
		    { A.setLectureDuration(duration);}
		     
		  
		    //iterates through schedule course map
		   Set<String> keySet = schedule.keySet();
		   Iterator <String> iterator = keySet.iterator();
		  
		   while (iterator.hasNext())
		   {
			   String key = iterator.next(); //go through the keys (courseCode)
			   
			   ActiveCourse value = schedule.get(key); //get the value (active course object)
			   
			   if(value.scheduleHelper(day, startTime, duration) && !key.equalsIgnoreCase(courseCode)) // helper function in active course
			    {
			    	throw new LectureTimeCollisionException ("Lecture time overlaps with another scheduled course");
			    }
			   
			
		   }
		   
		  
		    
	    
	}
	
	
	
	public void clearSchedule(String courseCode)
	{
		ActiveCourse A = new ActiveCourse(); 
		if (schedule.containsKey(courseCode))
			A = schedule.get(courseCode);
		//set A to one of the instance in schedule
		{
		A.setLectureDay("");
		A.setLectureDuration(0);
		A.setLectureStart(0);
	
		}
	}
	
	
	
	
	public void printSchedule()
	{
		 
		final int ROW = 9;
		final int COLUMN = 5;
		
		
		String [] time1 = 
			{
				"0800",
				"0900",
				"1000",
				"1100",
				"1200",
				"1300",
				"1400",
				"1500",
				"1600",
					
			};
		
		List<String> timeString = Arrays.asList(time1);
		List<Integer> time = new ArrayList<>();
		
		
		//CONVERTING time List from STRING TO INTEGER
		// so we can compare time list to startTime
		for(String number : timeString)
		{
			time.add(Integer.valueOf(number));
		}
		
		// 0800  0900 1000 1100 1200 1300 1400 1500 1600 
		//	0      1    2    3    4    5    6    7    8
		
		
		String [] date1 = 
			{
			    "MON","TUE","WED","THUR","FRI",	
			//	  0		1	  2		3      4
			};
		
		//convert date array to list
		List<String> date = Arrays.asList(date1);
		
	
		String[][] lecture = 
			{      //   0        1      2      3      4
					{"    ",	"   ","   ", "    ", "   "},
					{"    ",	"   ","   ", "    ", "   "},
					{"    ",	"   ","   ", "    ", "   "},
					{"    ",	"   ","   ", "    ", "   "},
					{"    ",	"   ","   ", "    ", "   "},
					{"    ",	"   ","   ", "    ", "   "},
					{"    ",	"   ","   ", "    ", "   "},
					{"    ",	"   ","   ", "    ", "   "},
					{"    ",	"   ","   ", "    ", "   "},
					
					
			};
	
		
		//iterates through schedule course map
		   Set<String> keySet = schedule.keySet();
		   Iterator <String> iterator = keySet.iterator();
		  
		   while (iterator.hasNext())
		   {
			   String key = iterator.next(); //go through the keys (courseCode)
			   
			   ActiveCourse value = schedule.get(key); //get the value (active course object)
			   
			  String day = value.getLectureDay();
			  int duration= value.getLectureDuration();
			  int startTime= value.getLectureStart();
			 

			  
			  //get the index for WEEKDAY - COLUMN
			  // checks if date in schedule matches weekday
			  
			  
			  if(containsCaseInsensitive(day,date))
			  {
				 int DayIndex = date.lastIndexOf(day.toUpperCase());
				 
				 
				// System.out.println("Day index" +DayIndex);
			  
				  //n gets the index for time - ROW
				  if (time.contains(startTime))
				  {
					  int TimeIndex = time.lastIndexOf(startTime);
			  
			  
					//  System.out.println("time index" +TimeIndex);
					  
					  //DEPENDING ON THE DURATION 
					  if (duration == 1)
					  {
						  lecture[TimeIndex][DayIndex] = key;
						  
						  
					  }
					  else if (duration == 2)
					  {
						  lecture[TimeIndex][DayIndex] = key;
						  lecture[TimeIndex+1][DayIndex] = key;
					  }
					  else if (duration == 3)
					  {
						  lecture[TimeIndex][DayIndex] = key;
						  lecture[TimeIndex+1][DayIndex] = key;
						  lecture[TimeIndex+2][DayIndex] = key;
					  }
					  
		
			  
					  
						
						}

				  }
		
				  
			  }
	

		   // PRINT OUT THE WEEKDAY LABEL 
		   
		   System.out.println("      " + "Mon     " + "Tue     " + "Wed     " +"Thur    " + "Fri");
			
			// PRINTS OUT THE COURSES FROM THE SCHEDULE 
			
			for (int row = 0; row<ROW;row++)
			{
				//prints out the times column
				System.out.printf("%-6s",time1[row]);
				
				
				for (int column = 0; column <COLUMN;column++)
				{
			
					System.out.printf("%-8s", lecture[row][column]);
					
				}
				
				System.out.println();
			  
	   }
		
		
		
	}
	
	
	/**This method check if the array contains a string
	 *  while ignoring the case in the array
	 * @param s
	 * @param l
	 * @return
	 */
	
	public boolean containsCaseInsensitive(String s, List<String> l)
	{
		for (String string :l)
		{
			if(string.equalsIgnoreCase(s))
			{
				return true;
			}
		}
		return false;
	}
	
}

