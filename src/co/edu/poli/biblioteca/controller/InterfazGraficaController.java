package co.edu.poli.biblioteca.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.edu.poli.biblioteca.model.BusquedaPorTitulo;
import co.edu.poli.biblioteca.model.EstrategiaBusqueda;
import co.edu.poli.biblioteca.dao.UsuarioDAO;
import co.edu.poli.biblioteca.model.Biblioteca;
import co.edu.poli.biblioteca.model.BusquedaPorAutor;
import co.edu.poli.biblioteca.model.Libro;
import co.edu.poli.biblioteca.model.Usuario;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;

public class InterfazGraficaController {
	@FXML
	private TextField tinputtitulo;
	@FXML
	private TextField inputautor;
	@FXML
	private TextField inputCategoria;
	@FXML
	private TextArea outputbbusqueda;
	@FXML
	private TextField inputbautor;
	@FXML
	private TextField inputbtitulo;
	@FXML
	private Button anadirbtn;
	@FXML
	private Button buecarbtn;
	@FXML
	private ComboBox users;
	@FXML
	private ComboBox libros;
	@FXML
	private ComboBox usuariosdevolucion;
	@FXML
	private ComboBox librodevolucion;
	@FXML
	private TextArea outputprestamo;
	@FXML
	private Button prestarbtn;
	@FXML
	private Button devolverbtn;

    @FXML
    private Button cargueDeUsers;
    @FXML
    private TextArea outputbbusqueda1;

	// Event Listener on Button[#anadirbtn].onAction
	List<Libro> Libros = new ArrayList<>();
	UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<Usuario> usuarios = usuarioDAO.getUsuariosFromDatabase();
    List<Libro> libreria = usuarioDAO.getLibrosFromDatabase();
	Biblioteca biblioteca = new Biblioteca();
	EstrategiaBusqueda estrategia = new BusquedaPorAutor();
	EstrategiaBusqueda estrategia2 = new BusquedaPorTitulo();
	List<String> test = new ArrayList<>();
	
	@FXML
	public void agregar(ActionEvent event) {
		
		Libros.add(new Libro(tinputtitulo.getText(), inputautor.getText(), inputCategoria.getText()));
		
		for (Libro libro : Libros) {
            biblioteca.agregarLibro(libro);
        }
	
	}
	
	@FXML
	public void buscar(ActionEvent event) {
		  List<Libro> resultadosBusqueda= biblioteca.buscarLibros(inputbautor.getText(), estrategia);
		  String resultados = "";
		  if (!resultadosBusqueda.isEmpty()) {

			    for(Libro resultado : resultadosBusqueda) {
			      resultados += resultado.getAutor() + ": " + resultado.getTitulo() + "\n";
			    }

			  } else {
			    resultados = "No se encontraron resultados.";
			  }
		  outputbbusqueda.setText(resultados);
		  
		  
	}
	 @FXML
	    void buscar2(ActionEvent event) {
		 List<Libro> resultadosBusqueda= biblioteca.buscarLibros(inputbtitulo.getText(), estrategia2);
		  String resultados = "";
		  if (!resultadosBusqueda.isEmpty()) {

			    for(Libro resultado : resultadosBusqueda) {
			      resultados += resultado.getTitulo() + ": " + resultado.getAutor() + "\n";
			    }

			  } else {
			    resultados = "No se encontraron resultados.";
			  }
		  outputbbusqueda1.setText(resultados);

	    }
	
	@FXML
	public void prestar(ActionEvent event) {
		 String nombreUsuarioSeleccionado = (String) users.getValue(); 
		 String tituloLibroSeleccionado = (String) libros.getValue(); 

		    if (nombreUsuarioSeleccionado != null && tituloLibroSeleccionado != null) {
		        Usuario usuarioSeleccionado = obtenerUsuarioPorNombre(nombreUsuarioSeleccionado); 
		        Libro libroSeleccionado = obtenerLibroPorTitulo(tituloLibroSeleccionado);
		        if (usuarioSeleccionado != null && libroSeleccionado != null) {
		            biblioteca.prestarLibro(usuarioSeleccionado, libroSeleccionado); 
		            outputprestamo.setText(usuarioSeleccionado.getNombre() + " ha tomado prestado el libro: " + libroSeleccionado.getTitulo());
		        } else {
		            outputprestamo.setText("No se encontró al usuario o al libro seleccionado.");
		        }
		    } else {
		        outputprestamo.setText("Por favor, selecciona un usuario y un libro.");
		    }
		}

		
		private Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
		    for (Usuario usuario : usuarios) {
		        if (usuario.getNombre().equals(nombreUsuario)) {
		            return usuario;
		        }
		    }
		    return null; 
		}

		
		private Libro obtenerLibroPorTitulo(String tituloLibro) {
		    for (Libro libro : libreria) {
		        if (libro.getTitulo().equals(tituloLibro)) {
		            return libro;
		        }
		    }
		    return null;
	}
	
	@FXML
	public void devolver(ActionEvent event) {
		String nombreUsuarioSeleccionado = (String) usuariosdevolucion.getValue();
		 String tituloLibroSeleccionado = (String) librodevolucion.getValue(); 
		 if (nombreUsuarioSeleccionado != null && tituloLibroSeleccionado != null) {
		        Usuario usuarioSeleccionado = obtenerUsuarioPorNombre1(nombreUsuarioSeleccionado); 
		        Libro libroSeleccionado = obtenerLibroPorTitulo1(tituloLibroSeleccionado); 

		        if (usuarioSeleccionado != null && libroSeleccionado != null) {
		            biblioteca.devolverLibro(usuarioSeleccionado, libroSeleccionado); 
		            outputprestamo.setText(usuarioSeleccionado.getNombre() + " ha devuelto el libro: " + libroSeleccionado.getTitulo());
		        } else {
		            outputprestamo.setText("No se encontró al usuario o al libro seleccionado.");
		        }
		    } else {
		        outputprestamo.setText("Por favor, selecciona un usuario y un libro.");
		    }
		}

		
		private Usuario obtenerUsuarioPorNombre1(String nombreUsuario) {
		    for (Usuario usuario : usuarios) {
		        if (usuario.getNombre().equals(nombreUsuario)) {
		            return usuario;
		        }
		    }
		    return null; 
		}

	
		private Libro obtenerLibroPorTitulo1(String tituloLibro) {
		    for (Libro libro : libreria) {
		        if (libro.getTitulo().equals(tituloLibro)) {
		            return libro;
		        }
		    }
		    return null;
	}

    @FXML
    void mostrarUsuarios(ActionEvent event) {
    	   	
    	for (Usuario usuario : usuarios) {
            biblioteca.registrarUsuario(usuario);
            users.getItems().add(usuario.getNombre());
            usuariosdevolucion.getItems().add(usuario.getNombre());
        }
    	for(Libro libro: libreria) {
    		biblioteca.agregarLibro(libro);
    		libros.getItems().add(libro.getTitulo());
    		librodevolucion.getItems().add(libro.getTitulo());
    	};
    	
    

    }
}
