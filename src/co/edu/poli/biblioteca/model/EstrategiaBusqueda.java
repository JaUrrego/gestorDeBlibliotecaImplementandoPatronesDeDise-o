package co.edu.poli.biblioteca.model;

import java.util.List;



public interface EstrategiaBusqueda {
	
    List<Libro> buscar(List<Libro> inventario, String criterio);
}