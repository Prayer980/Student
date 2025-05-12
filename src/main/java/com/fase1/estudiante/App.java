package com.fase1.estudiante;

import java.util.Scanner;

import com.fase1.estudiante.service.StudentService;

public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			var service = new StudentService();
			
			while (true) {
				System.out.println("""
			            ===== MENÚ =====
			            1. Agregar estudiante
			            2. Listar estudiantes
			            3. Buscar por nombre
			            4. Filtrar por nota mínima
			            5. Calcular promedio
			            6. Salir
			            Elige una opción:
			            """);
				
				var option = scanner.nextLine();
				
				switch (option) {
					case "1" -> {
						System.out.println("Nombre: ");
						var name = scanner.nextLine();
						System.out.print("Edad: ");
						var age = Integer.parseInt(scanner.nextLine());
						System.out.print("Nota: ");
			            var grade = Double.parseDouble(scanner.nextLine());
			            service.addStudent(name, age, grade);
			            System.out.println("Estudiante agregado.");
					}
					case "2" -> service.getAllStudents().forEach(System.out::println);
					case "3" -> {
						System.out.println("Escribir nombre: ");
						var name = scanner.nextLine();
						var student = service.findByName(name);
						student.ifPresentOrElse(
								System.out::println,
								() -> System.out.println("Estudiante no encontrado"));
					}
					case "4" -> {
						System.out.println("Escribir nota: ");
						var grade = Double.parseDouble(scanner.nextLine());
						var result = service.filterByGrade(grade);
						result.forEach(System.out::println);
					}
					case "5" -> System.out.printf("Media : ", service.averageGrade());
					case "6" -> {
						System.out.println("Cerrar");
						return;
					}
					default -> System.out.println("Opción no válida.");
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
    }
}
