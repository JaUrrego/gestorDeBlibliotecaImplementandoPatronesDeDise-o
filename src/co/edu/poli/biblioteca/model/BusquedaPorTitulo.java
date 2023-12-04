package co.edu.poli.biblioteca.model;

import java.util.ArrayList;
import java.util.List;



public class BusquedaPorTitulo implements EstrategiaBusqueda {
    @Override
    public List<Libro> buscar(List<Libro> inventario, String criterio) {
    	long tiempoInicial = System.currentTimeMillis();
    	 
    	    
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : inventario) {
            if (libro.getTitulo().contains(criterio)) {
                resultados.add(libro);
            }
        }
        long tiempoFinal = System.currentTimeMillis();
  	  	long tiempoTranscurrido = tiempoFinal - tiempoInicial;
        System.out.println("El método tomó " + tiempoTranscurrido + " milisegundos en ejecutarse.");
        return resultados;
    }
}