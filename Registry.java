/**
 * Jenny Long
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


// for (String id : students.key
public class Registry
{
	//student id as key for students 
	//course code as key for courses
   private Map <String,Student>  students = new TreeMap<String,Student>();
   private TreeMap<String, ActiveCourse> courses  = new TreeMap <String, ActiveCourse>(String.CASE_INSENSITIVE_ORDER);
   
   public Registry() throws FileNotFoundException , IOException
   
   {
	   File readFile = new File("students.txt");
	   Scanner  scanLine =  new Scanner (readFile); // scan the file
	   
	   
	   while (scanLine.hasNextLine())
	   {
		   String line = scanLine.nextLine(); // scans each line
		   
		   Scanner scanWord = new Scanner(line); //scan each word from the line
		   String name = scanWord.next();
		   String id = scanWord.next();
		   
		   Student student = new Student (name,id); // new student object
		   
		   students.put(id, student); //add to student tree map
		   
		   scanWord.close();
	   }
	 scanLine.close();
	  
	   
	   
		
		
	  // ADDING COURSES TO COURSE TREE MAP
	   
	 	//array list of students
	   ArrayList<Student> list = new ArrayList<Student>();
	   
	   
	   // Add some active courses with students
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   
	   // add students to students lists
	   list.add(students.get("25347")); list.add(students.get("34562")); 
	
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   
	   // add course to student list of credit courses
	   students.get("25347").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   students.get("34562").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   

	   // CPS511
	   
	   list.clear();
	   
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
	 
	   // add students to students lists
	   list.add(students.get("38467")); list.add(students.get("57643")); 
	   
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));
	   
	// add course to student list of credit courses
	   students.get("38467").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   students.get("57643").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
	   
	   
	   
	   // CPS643
	   list.clear();
	   
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
	   
	   // add students to students lists
	   list.add(students.get("46532")); list.add(students.get("98345")); 
	  
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	  
		// add course to student list of credit courses
	   students.get("46532").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   students.get("98345").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
	   
	   
	   
	   // CPS706
	   list.clear();
	   
	     courseName = "Computer Networks";
	     courseCode = "CPS706";
	     descr = "Learn about Computer Networking";
	     format = "3Lec 1Lab";
	     
	     // add students to students lists
		   list.add(students.get("38467")); 
		   
	     courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	    
	  // add course to student list of credit courses
		   students.get("38467").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	     
	    
		   
		   
		   // CPS616
	     
	     list.clear();
	     courseName = "Algorithms";
	     courseCode = "CPS616";
	     descr = "Learn about Algorithms";
	     format = "3Lec 1Lab";
	     
	     
	  // add students to students lists
		   list.add(students.get("25347"));  
		   
	     courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	     
	     // add course to student list of credit courses
		   students.get("25347").addCourse(courseName,courseCode,descr,format,"W2020", 0);
   
   }
   
   
   //get courses treemap
   public TreeMap<String, ActiveCourse> getCourseTM ()
   {
	   return courses;
   }
   
   //add new student
   public boolean addNewStudent(String name, String id)
   {
	   if (findStudent(id) != null) return false;
	   
	   Student s = new Student( name,id);
	   
	   students.put(id,s);
	   return true;
   }
   
  
   
   
   // remove student
   public boolean removeStudent(String studentId)
   {
     
	   if(findStudent(studentId) != null)
	   {
		   students.remove(studentId);
		  return true;
	   }
	 return false;
   }
  
   
  //prints all students
   public void printAllStudents()
   {
	   Set <String> keySet = students.keySet();
	   Iterator<String> iterator = keySet.iterator();
	   while (iterator.hasNext())
	   {
		   String key = iterator.next();
		   Student value = students.get(key);
		   
		   System.out.println(value);
		  
		   
	   }
   }
   
   // this returns the student object
   private Student findStudent(String id)
   {
	   
	   if(students.containsKey(id))
		{
		   
		return students.get(id);
		 }
	   return null;
				   
	}
			   
     

   //returns the active course

   private ActiveCourse findCourse(String code)
   {
	   if (courses.containsKey(code))
	   {
	     return courses.get(code);
	   }
     return null;
   }
   
   
   public void addCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   if (s.takenCourse(courseCode)) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   if (ac.enrolled(studentId)) return;
			   
	   ac.students.add(s);
	   s.addCourse(ac.getName(),ac.getCode(),ac.getCourseDescription(),ac.getFormat(),ac.getSemester(),0);
   }
   
  
   public void dropCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.remove(studentId);
	   s.removeActiveCourse(courseCode);
   }
   
   
   public void printActiveCourses()
   {
      Set<String> keySet = courses.keySet();
      Iterator<String> iterator = keySet.iterator();
      
      while(iterator.hasNext())
      {
    	  String key = iterator.next();
    	  
    	  ActiveCourse value = courses.get(key);
	     System.out.println(value.getDescription());
      }
   }
   
   public void printClassList(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printClassList();
   }
   

   
   public void sortCourseByName(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortByName();
   }
   
   public void sortCourseById(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortById();	   
   }
   
   public void printGrades(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printGrades();
   }
   
   public void printStudentCourses(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printActiveCourses();
   }
   
   public void printStudentTranscript(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printTranscript();
   }
   
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   s.setGrade(courseCode, grade);
	   

   }
}
