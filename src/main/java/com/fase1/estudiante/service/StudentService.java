package com.fase1.estudiante.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fase1.estudiante.model.Student;

public class StudentService {
	
	private final List<Student> students = new ArrayList<>();
	
	public void addStudent(String nombre, int edad, double nota) {
		students.add(new Student(nombre, edad, nota));
	}
	
	public List<Student> getAllStudents() {
		return students;
	}
	
	public Optional<Student> findByName(String nombre) {
		return students.stream()
				.filter(s -> s.getNombre().equalsIgnoreCase(nombre))
				.findFirst();
	}
	
	public List<Student> filterByGrade(double minNota) {
		return students.stream()
				.filter(s -> s.getNota() >= minNota)
				.collect(Collectors.toList());
	}
	
	public double averageGrade() {
		return students.stream()
				.mapToDouble(s -> s.getNota())
				.average()
				.orElse(0.0);
				
	}

}
