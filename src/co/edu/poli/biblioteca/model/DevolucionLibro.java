package co.edu.poli.biblioteca.model;

import javax.swing.JOptionPane;

public class DevolucionLibro implements Comando {
    private Usuario usuario;
    private Libro libro;

    public DevolucionLibro(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
    }

    @Override
    public void ejecutar() {
        if (libro.estaPrestado()) {
            libro.devolver();
          
            JOptionPane.showMessageDialog(null, usuario.getNombre() + " ha devuelto el libro: " + libro.getTitulo());
        } else {
        	JOptionPane.showMessageDialog(null, "El libro " + libro.getTitulo() + " no está prestado.");
       
        }
    }
}