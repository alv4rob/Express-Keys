package es.urjc.code.dad.xkeys_web.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.code.dad.xkeys_web.model.Carrito;
import es.urjc.code.dad.xkeys_web.model.Producto;
import es.urjc.code.dad.xkeys_web.model.Valoracion;
import es.urjc.code.dad.xkeys_web.service.ProductoService;
import es.urjc.code.dad.xkeys_web.service.ValoracionService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoS;
	
	@Autowired
	private Carrito carrito;
	
	@Autowired
	private ValoracionService valoracionS;

	@PostConstruct
	public void initProducto() {
		
		Producto producto= new Producto(new ArrayList<>(Arrays.asList("cyberps41", "cyberps42", "cyberps43")), "Cyberpunk 2077", 70, "PC", "Accion");
		productoS.save(producto);
		Producto producto2= new Producto(new ArrayList<>(Arrays.asList("fifaps41", "fifaps42", "fifaps413")), "FIFA 21", 60, "PC", "Deporte");
		productoS.save(producto2);
		Producto producto3 =new Producto(new ArrayList<>(Arrays.asList("minecraftps41", "minecraftps42", "minecraftps413")), "Minecraft", 20, "PS4", "Plataformas");
		productoS.save(producto3);
		Producto producto4 =new Producto(new ArrayList<>(Arrays.asList("flightsimu123", "flightsimu456", "minecraftps789")), "Flight Simulator 2020 Deluxe Edition", 90, "PC", "Simulador");
		productoS.save(producto4);
		Producto producto5 =new Producto(new ArrayList<>(Arrays.asList("re32434", "re34566", "re11111")), "Resident Evil 7", 50, "XBOX ONE", "Terror");
		productoS.save(producto5);
		Producto producto6 =new Producto(new ArrayList<>(Arrays.asList("forza7654", "forza7543", "forza7341")), "Forza Motosport 7", 60, "XBOX ONE", "Deporte");
		productoS.save(producto6);
		Producto producto7 =new Producto(new ArrayList<>(Arrays.asList("bus49504", "bus39405", "bus20394")), "Bus Simulator 18", 15, "PC", "Simulador");
		productoS.save(producto7);
		Producto producto8 =new Producto(new ArrayList<>(Arrays.asList("control412341", "control323454", "control094933")), "Control", 15, "XBOX ONE", "Accion");
		productoS.save(producto8);
		
		Valoracion v1 = new Valoracion("Pepe","Muy contento con la compra");
		v1.setProductoH(producto);	
		valoracionS.save(v1);
		Valoracion v2 = new Valoracion("Pepa","Hay un juego en este bug");
		v2.setProductoH(producto);
		valoracionS.save(v2);
		Valoracion v3 = new Valoracion("Pepe","Un poco igual al 20 no?");
		v3.setProductoH(producto2);
		valoracionS.save(v3);
		Valoracion v4 = new Valoracion("Pepo","Mucho susto");
		v4.setProductoH(producto5);	
		valoracionS.save(v4);
		Valoracion v5 = new Valoracion("Pepa","Fui a sobrevolar mi casa y me estrellé sin querer");
		v5.setProductoH(producto4);	
		valoracionS.save(v5);
		Valoracion v6 = new Valoracion("Pepo","Los pasos de cebra están mal pintados");
		v6.setProductoH(producto7);	
		valoracionS.save(v6);
	}
	
	
	
	@PostMapping("/producto/nuevo")
	public String nuevoProducto(Model model, Producto producto) {

		productoS.save(producto);
		return "productoGuardado";
	}
	
	
	@GetMapping("/producto/{id}")
	public String mostrarProducto(Model model, @PathVariable long id) {

		Producto producto = productoS.findById(id);
		model.addAttribute("producto", producto);

		return "mostrarProducto";
	}
	
	
	@GetMapping("/producto/{id}/eliminar")
	public String eliminarProducto(Model model, @PathVariable long id) {

		productoS.deleteById(id);

		return "productoEliminado";
	}
	
	
	@GetMapping("/producto/{id}/anadido")
	public String añadirCarrito(Model model, @PathVariable long id) {
  
		Producto producto = productoS.findById(id);
		
		
		for(int i=0; i<carrito.nCarrito(); i++) {
			
			Producto aux = carrito.getCarrito().get(i);
			if (aux.sonIguales(producto)) {
				return "noAnadidoCarrito";
			}
		}
				
		carrito.añadirAlCarrito(producto);
		return "anadidoCarrito";
    }
	
	
	
}
