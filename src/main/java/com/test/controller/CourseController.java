package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Course;
import com.test.service.CourseService;

@Controller
@RequestMapping("/courses")

/**
 * 
 * @author LXH
 *
 */
public class CourseController {

	private CourseService courseService;
	private static Logger log = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	// /courses/view?courseId=2
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {
		log.debug("in viewCourse ,courseid=" + courseId);
		System.out.println("courseid==>" + courseId);
		Course course = courseService.getCoursebyId(courseId);
		model.addAttribute(course);
		System.out.println("course==>" + course);
		System.out.println("model===>" + model);
		return "course_overview";
	}

	// /courses/view2/123
	@RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
	public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> model) {
		log.debug("in viewCourse ,courseid=" + courseId);
		System.out.println("courseid==>" + courseId);
		Course course = courseService.getCoursebyId(courseId);
		model.put("course", course);
		System.out.println("course==>" + course);
		System.out.println("model===>" + model);
		return "course_overview";
	}

	// /courses/view2?courseId=123
	@RequestMapping("/view3")
	public String viewCourse3(HttpServletRequest requestf) {
		Integer courseId = Integer.valueOf(requestf.getParameter("courseId"));
		Course course = courseService.getCoursebyId(courseId);
		requestf.setAttribute("course", course);
		return "course_overview";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
	public String createCourse() {
		return "course_admin/edit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String doSave(@ModelAttribute Course course) {
		// 在此进行业务操作，比如数据库持久化
		course.setCourseId(123);
		return "redirect:view2/" + course.getCourseId();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showUploadPage(@ModelAttribute Course course) {
		return "course_admin/file";
	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {

		if (!file.isEmpty()) {
			log.debug("Process file: {}", file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(),
					new File("c:\\temp\\imooc\\", System.currentTimeMillis() + file.getOriginalFilename()));
		}

		return "success";
	}

	// /courses/123
	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public @ResponseBody Course getCourseInJson(@PathVariable Integer courseId) {
		return courseService.getCoursebyId(courseId);
	}

	// /courses/jsontype?123
	@RequestMapping(value = "/jsontype/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<Course> getCourseInJson2(@PathVariable Integer courseId) {
		Course course = courseService.getCoursebyId(courseId);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
}
