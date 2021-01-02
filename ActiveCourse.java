import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
   public  ArrayList<Student> students; // map id to name
   private String             semester;
   private int lectureDuration;
   private int lectureStart;
   private String lectureDay;

   
  public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
	   lectureDay = "";
	   lectureStart = 0;
	   lectureDuration = 0;
   }
   
 // overrides default constructor
  public ActiveCourse()
  {
	  super();
  }
  
  
   public int getLectureDuration()
   {	
	   return lectureDuration;
   }
   
   public void setLectureDuration(int duration)
   {
	   this.lectureDuration = duration;
   }
   
   public int getLectureStart()
   {
	   return lectureStart;
   }
   
   public void setLectureStart(int start)
   {
	   this.lectureStart = start;
   }
   
   public String getLectureDay()
   {
	   return lectureDay;
   }
   
   public void setLectureDay(String day)
   {
	   this.lectureDay = day;
   }
   
   
   public String getSemester()
   {
	   return semester;
   }
   
   public void printClassList()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println(students.get(i).toString());
	   }
   }
   
   public void printGrades()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   Student s = students.get(i);
		   System.out.println(s.getId() + " " + s.getName() + " " + s.getGrade(getCode()));
	   }
   }
   
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() +  "\n";
   }
   
   public String getCourseDescription()
   {
	   return getDescr();
   }
    
   public double getGrade(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		   {
			   return students.get(i).getGrade(getCode());
		   }
	   }
	   return 0;
   }
   
   public boolean enrolled(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		     return true;
	   }
	   return false;
   }
   
   
   // Helper method for LectureTimeCollision exception
   public boolean scheduleHelper(String lectureDay, int lectureStart,int lectureDuration)
   {
	   
	   if (this.lectureDay.equalsIgnoreCase(lectureDay))
	   {
		   
		   for (int i = lectureStart;i <lectureStart + lectureDuration*100;i++) //loops through  lecture parameters
		   {
			   for (int j = this.lectureStart; i< this.lectureStart + this.lectureDuration *100;j++) // loops through scheduled lecture parameters 
			   {
				   if (i ==j)
					   return true;
			   }
		   }
	   }
	return false;
		
   }
   
   
   public void remove(String id)
   {
	   for (int j = 0; j < students.size(); j++)
	   {
   		   Student s = students.get(j);
   		   if (s.getId().equals(id))
   		   {
   		     students.remove(j);
   		     return;
   		   }
 	   }
    }
   
      
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }
   
   private class NameComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getName().compareTo(b.getName());	  
   	}
   }
   
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   
   private class IdComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getId().compareTo(b.getId());	  
   	}
   }
}
