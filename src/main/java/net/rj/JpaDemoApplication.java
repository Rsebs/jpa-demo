package net.rj;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.rj.model.Categoria;
import net.rj.model.Vacante;
import net.rj.repository.CategoriasRepository;
import net.rj.repository.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{

	@Autowired
	private CategoriasRepository repoCategorias;
	@Autowired
	private VacantesRepository repoVacantes;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	public void run(String... args) throws Exception {
		guardarVacante();
	}
	
	private void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de Matematicas");
		vacante.setDescripcion("Escuela primaria solicita profesor para curso de Matematicas");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.0);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");
		vacante.setDetalles("<h1>Los requisitos para profesor de Matematicas</h1>");
		
		Categoria cat = new Categoria();
		cat.setId(15);
		
		vacante.setCategoria(cat);
		
		repoVacantes.save(vacante);
	}

	private void buscarVacantes() {
		List<Vacante> lista = repoVacantes.findAll();
		for (Vacante v : lista) {
			System.out.println(v.getId() + " " + v.getNombre() + " -> " + v.getCategoria().getNombre());
		}
	}
	
	// Create
	private void guardar() {
		Categoria cat = new Categoria();
		
		// No asignamos un id porque es nuestra llave primaria AUTOINCREMENTABLE
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		
		repoCategorias.save(cat);
		
		System.out.println(cat);
	}
	
	// Read
	private void buscarPorId(Integer id) {
		Optional<Categoria> optional = repoCategorias.findById(id);
		
		// Validamos si el objeto existe
		if (optional.isPresent()) {
			System.out.println(optional);
		} else {
			System.out.println("Categoria no encontrada");
		}
		
	}
	
	// Update
	private void modificar(Integer id) {
		Optional<Categoria> optional = repoCategorias.findById(id);
				
		// Validamos si el objeto existe
		if (optional.isPresent()) {
			Categoria catTmp = optional.get(); // Se recupera el objeto
			// Realizamos los cambios
			catTmp.setNombre("Ing. Software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			
			// Posteriormente se guarda para ser actualizado
			repoCategorias.save(catTmp);
			
			System.out.println(optional);
		} else {
			System.out.println("Categoria no encontrada");
		}
	}

	// Delete
	private void eliminar() {
		int idCategoria = 1;
		repoCategorias.deleteById(idCategoria);
	}
	
	// count
	private void conteo() {
		long count = repoCategorias.count();
		System.out.println("Total de categorias: " + count);
	}
	
	// deleteAll
	private void eliminarTodos() {
		repoCategorias.deleteAll();
	}
	
	// findById
	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = repoCategorias.findAllById(ids);
		
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	// findAll
	private void buscarTodos() {
		Iterable<Categoria> categorias = repoCategorias.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	// existsById
	private void existeId(Integer id) {
		boolean existe = repoCategorias.existsById(id);
		System.out.println("La categoria existe: " + existe);
	}
	
	// saveAll
	private void guardarTodas() {
		 repoCategorias.saveAll(getListaCategoria());
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
	
	// findAll - JPA
	private void buscarTodosJpa() {
		List<Categoria> categorias = repoCategorias.findAll();
		for (Categoria c : categorias) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	// deleteAllInBatch - JPA
	private void borrarTodoEnBloque() {
		repoCategorias.deleteAllInBatch();
	}
	
	// findAll [Ordenados por un campo] - JPA
	private void buscarTodosOrdenados() {
		List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre").descending());
		for (Categoria c : categorias) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	// findAll [Con Paginación] - JPA
	private void buscarTodosPaginacion() {
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5));
		System.out.println("Total Registros: " + page.getTotalElements());
		System.out.println("Total Páginas: " + page.getTotalPages());
		for (Categoria c : page.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	// findAll [Con Paginación y Ordenados] - JPA
	private void buscarTodosPaginacionOrdenados() {
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5, Sort.by("nombre")));
		for (Categoria c : page.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
}
