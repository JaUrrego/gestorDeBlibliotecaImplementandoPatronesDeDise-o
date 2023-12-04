package co.edu.poli.biblioteca.model;

import javax.swing.JOptionPane;

public class PrestamoLibro implements Comando {
    private Usuario usuario;
    private Libro libro;

    public PrestamoLibro(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
    }

    @Override
    public void ejecutar() {
    	long tiempoInicial = System.currentTimeMillis();
        if (!libro.estaPrestado()) {
            libro.prestar();
       
            JOptionPane.showMessageDialog(null,usuario.getNombre() + " ha tomado prestado el libro: " + libro.getTitulo());
        } else {
       
            JOptionPane.showMessageDialog(null,"El libro " + libro.getTitulo() + " ya está prestado.");
        }
        long tiempoFinal = System.currentTimeMillis();
        long tiempoTranscurrido = tiempoFinal - tiempoInicial;
        System.out.println("El método de PRESTAMO tomó " + tiempoTranscurrido + " milisegundos en ejecutarse.");
        
    }
}
