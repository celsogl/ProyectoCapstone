package com.clases.springboot.app.Controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clases.springboot.app.Models.Entity.Productos;
import com.clases.springboot.app.Models.service.IProductosService;
import com.clases.springboot.app.Models.service.UploadFileService;


@Controller
@SessionAttributes("productos")
public class ProductosController{
	
	@Autowired
	private IProductosService productosService;

	@Autowired
	private UploadFileService upload;

	


	@RequestMapping(value = "/listaproductos", method = RequestMethod.GET)
	public String listarProductos(Model model) {
		model.addAttribute("productos", productosService.findAll());
		return "/Productos/listProductos";
	}

	@RequestMapping(value = "/createproductos")
	public String crearProducto(Map<String, Object> model) {
		Productos productos = new Productos();
		model.put("productos", productos);
		return "/Productos/registrarProductos";
	}

	@RequestMapping(value = "/createproductos", method = RequestMethod.POST)
	public String guardar(Productos productos,@RequestParam(name = "file", required = false) MultipartFile imagen,
			RedirectAttributes attribute) throws IOException {

			if(productos.getId()==null){
				if (imagen != null && !imagen.isEmpty()) {
					String contentType = imagen.getContentType();
					if (contentType != null && contentType.startsWith("image/")) {
						String nombreImagen = upload.saveImage(imagen);
						productos.setImagen(nombreImagen);
					} else {
						// El archivo no es una imagen válida, establece una imagen predeterminada
						productos.setImagen("imagen_predeterminada.jpg"); // Reemplaza con el nombre de tu imagen predeterminada
					}
				} else {
					// No se proporcionó ninguna imagen, establece una imagen predeterminada
					productos.setImagen("imagen_predeterminada.jpg"); // Reemplaza con el nombre de tu imagen predeterminada
				}
			}
			productosService.save(productos);

		return "redirect:/listaproductos";
	}


	@RequestMapping(value = "/formproductos/{id}")
	public String editarProducto(@PathVariable(value = "id") Long id, Map<String, Object> model,@RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
		Productos productos = null;
		if (id > 0) {
			productos = productosService.findbyId(id);
		}
		else {
			return "redirect:/listaproductos";
		}
		model.put("productos", productos);

		return "/Productos/registrarProductos";
	}



}
