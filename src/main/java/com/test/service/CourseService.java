package com.test.service;

import org.springframework.stereotype.Service;

import com.test.model.Course;


@Service
public interface CourseService {
	
	
	Course getCoursebyId(Integer courseId);
	

	
	

}
