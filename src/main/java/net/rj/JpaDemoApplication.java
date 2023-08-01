package net.rj;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.rj.model.Categoria;
import net.rj.repository.CategoriasRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{

	@Autowired
	private CategoriasRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	public void run(String... args) throws Exception {
		
	}

	// Create
	private void guardar() {
		Categoria cat = new Categoria();
		
		// No asignamos un id porque es nuestra llave primaria AUTOINCREMENTABLE
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		
		repo.save(cat);
		
		System.out.println(cat);
	}
	
	// Read
	private void buscarPorId(Integer id) {
		Optional<Categoria> optional = repo.findById(id);
		
		// Validamos si el objeto existe
		if (optional.isPresent()) {
			System.out.println(optional);
		} else {
			System.out.println("Categoria no encontrada");
		}
		
	}
	
	// Update
	private void modificar(Integer id) {
		Optional<Categoria> optional = repo.findById(id);
				
		// Validamos si el objeto existe
		if (optional.isPresent()) {
			Categoria catTmp = optional.get(); // Se recupera el objeto
			// Realizamos los cambios
			catTmp.setNombre("Ing. Software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			
			// Posteriormente se guarda para ser actualizado
			repo.save(catTmp);
			
			System.out.println(optional);
		} else {
			System.out.println("Categoria no encontrada");
		}
	}

	// Delete
	private void eliminar() {
		int idCategoria = 1;
		repo.deleteById(idCategoria);
	}
	
	// count
	private void conteo() {
		long count = repo.count();
		System.out.println("Total de categorias: " + count);
	}
	
	// deleteAll
	private void eliminarTodos() {
		repo.deleteAll();
	}
	
	// findById
	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = repo.findAllById(ids);
		
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	// findAll
	private void buscarTodos() {
		Iterable<Categoria> categorias = repo.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	// existsById
	private void existeId(Integer id) {
		boolean existe = repo.existsById(id);
		System.out.println("La categoria existe: " + existe);
	}
	
	// saveAll
	private void guardarTodas() {
		 repo.saveAll(getListaCategoria());
	}
	
	// Método que regresa una lista de 3 Categorias
	private List<Categoria> getListaCategoria() {
		List<Categoria> lista = new LinkedList<>();
		
		// Categoria 1
		Categoria cat1 = new Categoria();
		cat1.setNombre("Programador de Blockchain");
		cat1.setDescripcion("Trabajos relacionados con Bitcoin y Criptomonedas");

		// Categoria 2
		Categoria cat2 = new Categoria();
		cat2.setNombre("Soldador/Pintura");
		cat2.setDescripcion("Trabajos relacionados con soldadura, pintura y enderezado");
		
		// Categoria 3
		Categoria cat3 = new Categoria();
		cat3.setNombre("Ingeniero Industrial");
		cat3.setDescripcion("Trabajos relacionados con Ingenieria industrial");
		
		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);
		return lista;
	}
	
}
