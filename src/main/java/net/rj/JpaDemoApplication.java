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
		encontrarPorIds();
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
	
	// Count
	private void conteo() {
		long count = repo.count();
		System.out.println("Total de categorias: " + count);
	}
	
	// Delete all
	private void eliminarTodos() {
		repo.deleteAll();
	}
	
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
}
