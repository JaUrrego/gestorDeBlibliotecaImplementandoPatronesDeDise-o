package co.edu.poli.biblioteca.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Biblioteca {
    private List<Libro> inventario = new ArrayList<>();
    private List<Observador> observadores = new ArrayList();
    private List<Usuario> usuarios = new ArrayList();

    public void agregarLibro(Libro nuevoLibro) {
        inventario.add(nuevoLibro);
        notificarUsuarios(nuevoLibro);
        
    }

    public void registrarUsuario(Usuario usuario) {
        observadores.add(usuario);
    }

    public void notificarUsuarios(Libro libro) {
    	long tiempoInicial = System.currentTimeMillis();
        for (Observador observador : observadores) {
            observador.actualizar(libro);
        }
        long tiempoFinal = System.currentTimeMillis();
        long tiempoTranscurrido = tiempoFinal - tiempoInicial;
        System.out.println("El método tomó " + tiempoTranscurrido + " milisegundos en ejecutarse.");
    }

    public List<Libro> buscarLibros(String criterio, EstrategiaBusqueda estrategia) {
    	
        return estrategia.buscar(inventario, criterio);
    }
    public List<Libro> getInventario() {
        return inventario;
    }
    public void prestarLibro(Usuario usuario, Libro libro){
        PrestamoLibro prestamo = new PrestamoLibro(usuario, libro);
        prestamo.ejecutar();
      }
      
      public void devolverLibro(Usuario usuario, Libro libro){
        DevolucionLibro devolucion = new DevolucionLibro(usuario, libro);
        devolucion.ejecutar();
      }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}