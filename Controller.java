import java.util.List;

public abstract class Controller {
	protected List<Course> courseList;



	public void printCourses() {
		System.out.print("Course List: ");
		for(Course c : courseList) {
			System.out.printf("[%s] ",c);
		}
		System.out.println();
	}

}
