package co.edu.poli.biblioteca.model;



public class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(Libro libro) {
        System.out.println("Hola, " + nombre + "! El libro '" + libro.getTitulo() + "' está disponible en la biblioteca.");
    }

    public String getNombre() {
        return nombre;
    }
}

