package com.clases.springboot.app.Controllers;


import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clases.springboot.app.Models.Entity.Delivery;
import com.clases.springboot.app.Models.Entity.Pagos;
import com.clases.springboot.app.Models.Entity.Pedido;
import com.clases.springboot.app.Models.Entity.Productos;
import com.clases.springboot.app.Models.Entity.Usuario;
import com.clases.springboot.app.Models.Entity.Venta;
import com.clases.springboot.app.Models.Entity.ventaDetalle;
import com.clases.springboot.app.Models.service.IDeliveryService;
import com.clases.springboot.app.Models.service.IPagosService;
import com.clases.springboot.app.Models.service.IProductosService;
import com.clases.springboot.app.Models.service.IUsuarioService;
import com.clases.springboot.app.Models.service.IVentaDetalleService;
import com.clases.springboot.app.Models.service.IVentasService;
import com.clases.springboot.app.Models.service.PdfService;


@Controller
@RequestMapping("/")
public class CarritoController {
    
    @Autowired
    private IProductosService productosService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IVentasService ventasService;

    @Autowired
    private IVentaDetalleService ventaDetalleService;

    @Autowired
    private IDeliveryService deliveryService;

    @Autowired
    private IPagosService pagosService;


    

    //Almacenar los detalles de la Venta
    List<ventaDetalle> detalles = new ArrayList<ventaDetalle>();
    
    //Datos de la Venta
    Venta venta = new Venta();

    //Datos de Pago
    Pagos pagos = new Pagos();

    //Datos de Delivery
     Delivery delivery = new Delivery();

   

     @PostMapping("/carrito")
public String addCart(@RequestParam Long id, Model model) {
    Optional<Productos> productoOptional = productosService.get(id);

    if (!productoOptional.isPresent()) {
        // Manejo del error o retorno.
        return "errorPage"; // o redirige a una página de error, según lo que desees.
    }

    Productos productos = productoOptional.get();

    // Comprobar si el producto ya está en el carrito
    Optional<ventaDetalle> productoExistenteOpt = detalles.stream()
            .filter(p -> p.getIdProductos().getId().equals(productos.getId()))
            .findFirst();

    if (productoExistenteOpt.isPresent()) {
        // Si el producto ya está en el carrito, incrementa su cantidad y actualiza el precio total
        ventaDetalle productoExistente = productoExistenteOpt.get();
        productoExistente.setCantidad(productoExistente.getCantidad() + 1);
        productoExistente.setTotalapagar(productoExistente.getPrecioproducto() * productoExistente.getCantidad());
    } else {
        // Si el producto no está en el carrito, lo añade
        ventaDetalle nuevoDetalle = new ventaDetalle();
        nuevoDetalle.setCantidad(1);
        nuevoDetalle.setPrecioproducto(productos.getPrecio());
        nuevoDetalle.setIdProductos(productos);
        nuevoDetalle.setTotalapagar(productos.getPrecio()); // Ya que la cantidad es 1, el total es el precio del producto

        detalles.add(nuevoDetalle);
    }

    // Actualizar el total de la venta
    double sumTotal = detalles.stream().mapToDouble(dt -> dt.getTotalapagar()).sum();
    venta.setMontoventa(sumTotal);

    // Añadir al modelo para la vista
    model.addAttribute("carrito", detalles);
    model.addAttribute("venta", venta);

    return "/ProcesarVenta/procesarventa";
}

    //Quitar producto del carrito
    @GetMapping("/eliminar/carrito/{id}")
    public String deleteProductCarrito(@PathVariable Long id, Model model){
        List<ventaDetalle> ordenesNuevas = new ArrayList<ventaDetalle>();

        for(ventaDetalle ventaDetalle: detalles ){
            if(ventaDetalle.getIdProductos().getId()!=id){
                ordenesNuevas.add(ventaDetalle);
            }
        }
        //ponemos la nueva lista con los productos restantes
        detalles = ordenesNuevas;

        double sumaTotal=0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getPrecioproducto()).sum();
        
        venta.setMontoventa(sumaTotal);

        model.addAttribute("carrito", detalles);
        model.addAttribute("venta", venta);

        
        return "/ProcesarVenta/procesarventa";
    }

     @GetMapping("/mostrarCarrito")
    public String getCarrito(Model model){
        model.addAttribute("carrito", detalles);
        model.addAttribute("venta", venta);

        return "/ProcesarVenta/procesarventa";
    }

    
    @GetMapping("/resumenPago")
    public String order (Model model){
     
      // Usuario usuario =usuarioService.findById(1).get();
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    // Verifica que el usuario esté autenticado antes de obtener su información.
    if (authentication.isAuthenticated()) {
        String username = authentication.getName(); // Obtiene el nombre de usuario
        // Ahora, puedes usar el nombre de usuario para buscar el ID del usuario en tu base de datos.
        // Supongamos que tienes un servicio llamado usuarioService que busca el ID por nombre de usuario.
        Usuario usuario = usuarioService.findByUsername(username);
        
        // Agrega el usuario al modelo
        model.addAttribute("usuario", usuario);
    }


        model.addAttribute("carrito", detalles);
        model.addAttribute("venta", venta);

        return "VentaResumen";
    }

    // guardar la orden

    @PostMapping(value = "/saveOrder")
	public String saveOrder( @RequestParam("direccion") String direccion,
    @RequestParam("metodo_pago") String metodo_pago,
    @RequestParam("telefono") String telefono) {
		
        Date fechaCreacion = new Date();
		venta.setFecha(fechaCreacion);
		
         // Usuario usuario =usuarioService.findById(1).get();
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    // Verifica que el usuario esté autenticado antes de obtener su información.
    if (authentication.isAuthenticated()) {
        String username = authentication.getName(); // Obtiene el nombre de usuario

        Usuario usuario = usuarioService.findByUsername(username);

        venta.setIdUsuario(usuario);
    }

		
		ventasService.save(venta);
		
		//guardar detalles
		for (ventaDetalle dt:detalles) {
			dt.setIdVenta(venta);
			ventaDetalleService.save(dt);
		}

        //Pagos
        pagos.setIdVenta(venta);
        pagos.setMetodo_pago(metodo_pago);
        pagosService.save(pagos);

        //DELIVERY
        delivery.setDireccion(direccion);
        delivery.setTelefono(telefono);
        delivery.setEstado("En Preparación");
        delivery.setIdVenta(venta);
        deliveryService.save(delivery);
		
		///limpiar lista y orden
		venta = new Venta();
        pagos = new Pagos();
        delivery = new Delivery();
		detalles.clear();
		
		return "redirect:/";
	}    


     @Autowired
    private PdfService pdfService;


/* 
    @GetMapping("/{idPedido}/descargarFactura")
    public ResponseEntity<ByteArrayResource> descargarFactura(@PathVariable Long idPedido) {

        ventaDetalle pedido = ventaDetalleService.findById(idPedido);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayInputStream byteArrayInputStream = pdfService.generateInvoice(pedido);
        ByteArrayResource byteArrayResource = new ByteArrayResource(byteArrayInputStream.readAllBytes());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=factura_" + idPedido + ".pdf")
                .body(byteArrayResource);
    }
*/





}
