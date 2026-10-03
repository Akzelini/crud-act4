/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud4;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JC
 */
public class alumnorepository {

    private final Path archivo;

    public alumnorepository() {

        archivo = Path.of("alumnos.txt");

        try {

            if (!Files.exists(archivo)) {
                Files.createFile(archivo);
                System.out.println("Archivo alumnos.txt creado.");
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al crear el archivo: "
                    + e.getMessage()
            );
        }
    }

    // CREAR
    public boolean agregarAlumno(Alumno alumno) {

        try {

            if (buscarPorId(alumno.getId()) != null) {
                return false;
            }

            Files.writeString(
                    archivo,
                    alumno.toString()
                    + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );

            return true;

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar el alumno: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // LEER TODOS
    public List<String> obtenerTodos() {

        try {

            return Files.readAllLines(archivo);

        } catch (IOException e) {

            System.out.println(
                    "Error al leer el archivo: "
                    + e.getMessage()
            );

            return new ArrayList<>();
        }
    }

    // BUSCAR POR ID
    public String buscarPorId(int id) {

        try {

            List<String> lineas =
                    Files.readAllLines(archivo);

            for (String linea : lineas) {

                String[] partes =
                        linea.split(" - ", 2);

                int idArchivo =
                        Integer.parseInt(partes[0].trim());

                if (idArchivo == id) {
                    return linea;
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al buscar el alumno: "
                    + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error en el formato del archivo."
            );
        }

        return null;
    }

    // ACTUALIZAR
    public boolean actualizarAlumno(
            int id,
            String nuevoNombre) {

        try {

            List<String> lineas =
                    Files.readAllLines(archivo);

            boolean encontrado = false;

            for (int i = 0; i < lineas.size(); i++) {

                String[] partes =
                        lineas.get(i).split(" - ", 2);

                int idArchivo =
                        Integer.parseInt(partes[0].trim());

                if (idArchivo == id) {

                    lineas.set(
                            i,
                            id + " - " + nuevoNombre
                    );

                    encontrado = true;

                    break;
                }
            }

            if (encontrado) {

                Files.write(archivo, lineas);

                return true;
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al actualizar: "
                    + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error en el formato del archivo."
            );
        }

        return false;
    }

    // ELIMINAR
    public boolean eliminarAlumno(int id) {

        try {

            List<String> lineas =
                    Files.readAllLines(archivo);

            boolean encontrado = false;

            for (int i = 0; i < lineas.size(); i++) {

                String[] partes =
                        lineas.get(i).split(" - ", 2);

                int idArchivo =
                        Integer.parseInt(partes[0].trim());

                if (idArchivo == id) {

                    lineas.remove(i);

                    encontrado = true;

                    break;
                }
            }

            if (encontrado) {

                Files.write(archivo, lineas);

                return true;
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al eliminar: "
                    + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error en el formato del archivo."
            );
        }

        return false;
    }
}