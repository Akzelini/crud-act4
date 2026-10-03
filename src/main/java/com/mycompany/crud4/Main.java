package com.mycompany.crud4;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.List;
import java.util.Scanner;

/**
 *
 * @author JC
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        alumnorepository repository = new alumnorepository();

        int opcion;

        do {

            System.out.println();
            System.out.println("==================================");
            System.out.println("   GESTION DE ALUMNOS - JAVA NIO");
            System.out.println("==================================");
            System.out.println("1. Registrar nuevo alumno");
            System.out.println("2. Ver todos los alumnos");
            System.out.println("3. Buscar alumno por ID");
            System.out.println("4. Actualizar nombre de alumno");
            System.out.println("5. Eliminar alumno");
            System.out.println("6. Salir");
            System.out.println("==================================");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:

                    System.out.println();
                    System.out.println("--- REGISTRAR ALUMNO ---");

                    System.out.print("Ingrese ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese nombre completo: ");
                    String nombre = scanner.nextLine();

                    Alumno alumno = new Alumno(id, nombre);

                    if (repository.agregarAlumno(alumno)) {
                        System.out.println("Alumno registrado con exito.");
                    } else {
                        System.out.println("Error: ese ID ya existe.");
                    }

                    break;

                case 2:

                    System.out.println();
                    System.out.println("--- LISTA DE ALUMNOS ---");

                    List<String> alumnos = repository.obtenerTodos();

                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                    } else {
                        for (String linea : alumnos) {
                            System.out.println(linea);
                        }
                    }

                    break;

                case 3:

                    System.out.println();
                    System.out.println("--- BUSCAR ALUMNO ---");

                    System.out.print("Ingrese ID: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();

                    String resultado = repository.buscarPorId(idBuscar);

                    if (resultado != null) {
                        System.out.println("Alumno encontrado:");
                        System.out.println(resultado);
                    } else {
                        System.out.println("No existe un alumno con ese ID.");
                    }

                    break;

                case 4:

                    System.out.println();
                    System.out.println("--- ACTUALIZAR ALUMNO ---");

                    System.out.print("Ingrese ID del alumno: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();

                    String alumnoActual =
                            repository.buscarPorId(idActualizar);

                    if (alumnoActual != null) {

                        System.out.println("Informacion actual:");
                        System.out.println(alumnoActual);

                        System.out.print("Ingrese el nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();

                        if (repository.actualizarAlumno(
                                idActualizar, nuevoNombre)) {

                            System.out.println(
                                    "Alumno actualizado correctamente."
                            );
                        }

                    } else {

                        System.out.println(
                                "No existe un alumno con ese ID."
                        );
                    }

                    break;

                case 5:

                    System.out.println();
                    System.out.println("--- ELIMINAR ALUMNO ---");

                    System.out.print("Ingrese ID del alumno: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();

                    String alumnoEliminar =
                            repository.buscarPorId(idEliminar);

                    if (alumnoEliminar != null) {

                        System.out.println(
                                "Alumno encontrado: " + alumnoEliminar
                        );

                        System.out.print(
                                "¿Esta seguro de eliminarlo? (s/n): "
                        );

                        String confirmacion = scanner.nextLine();

                        if (confirmacion.equalsIgnoreCase("s")) {

                            if (repository.eliminarAlumno(idEliminar)) {
                                System.out.println(
                                        "Alumno eliminado correctamente."
                                );
                            }

                        } else {

                            System.out.println("Operacion cancelada.");
                        }

                    } else {

                        System.out.println(
                                "No existe un alumno con ese ID."
                        );
                    }

                    break;

                case 6:

                    System.out.println();
                    System.out.println("Programa finalizado.");

                    break;

                default:

                    System.out.println(
                            "Opcion no valida."
                    );
            }

        } while (opcion != 6);

        scanner.close();
    }
}