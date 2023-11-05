package tn.esprit.spring;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.controllers.CourseRestController;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class CourseTests {

	@InjectMocks
	private CourseRestController courseController;

	@Mock
	private CourseServicesImpl courseServices;

	@Mock
	private ICourseRepository courseRepository;

	@Test
	public void testAddCourse() {
		Course course = new Course();
		when(courseServices.addCourse(course)).thenReturn(course);

		Course addedCourse = courseController.addCourse(course);

		assertThat(addedCourse).isEqualTo(course);
		verify(courseServices, times(1)).addCourse(course);
	}
	@Test
	public void testGetAllCourses() {
		List<Course> courses = new ArrayList<>();
		when(courseServices.retrieveAllCourses()).thenReturn(courses);

		List<Course> retrievedCourses = courseController.getAllCourses();

		assertThat(retrievedCourses).isEqualTo(courses);
		verify(courseServices, times(1)).retrieveAllCourses();
	}

	@Test
	public void testUpdateCourse() {
		Course course = new Course();
		when(courseServices.updateCourse(course)).thenReturn(course);

		Course updatedCourse = courseController.updateCourse(course);

		assertThat(updatedCourse).isEqualTo(course);
		verify(courseServices, times(1)).updateCourse(course);
	}

	@Test
	public void testGetCourseById() {
		Long numCourse = 1L;
		Course course = new Course();
		when(courseServices.retrieveCourse(numCourse)).thenReturn(course);

		Course retrievedCourse = courseController.getById(numCourse);

		assertThat(retrievedCourse).isEqualTo(course);
		verify(courseServices, times(1)).retrieveCourse(numCourse);
	}

	@Test
	public void testRetrieveCourseById_NotFound() {
		Long numCourse = 1L;
		when(courseServices.retrieveCourse(numCourse)).thenReturn(null);

		Course retrievedCourse = courseController.getById(numCourse);

		assertThat(retrievedCourse).isNull();
		verify(courseServices, times(1)).retrieveCourse(numCourse);
	}

}
