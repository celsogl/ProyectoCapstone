package com.clases.springboot.app.Controllers;

import java.io.ByteArrayInputStream;
import java.util.Date;
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

import com.clases.springboot.app.Models.Entity.Carrito;
import com.clases.springboot.app.Models.Entity.Delivery;
import com.clases.springboot.app.Models.Entity.DetalleCarrito;
import com.clases.springboot.app.Models.Entity.Pagos;
import com.clases.springboot.app.Models.Entity.Pedido;
import com.clases.springboot.app.Models.Entity.PedidoProducto;
import com.clases.springboot.app.Models.Entity.Productos;
import com.clases.springboot.app.Models.Entity.Usuario;
import com.clases.springboot.app.Models.Entity.Venta;
import com.clases.springboot.app.Models.service.IDeliveryService;
import com.clases.springboot.app.Models.service.IPagosService;
import com.clases.springboot.app.Models.service.IPedidoProductoService;
import com.clases.springboot.app.Models.service.IPedidoService;
import com.clases.springboot.app.Models.service.IProductosService;
import com.clases.springboot.app.Models.service.IUsuarioService;

import com.clases.springboot.app.Models.service.IVentasService;
import com.clases.springboot.app.Models.service.PdfService;
import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpSession;

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
    private IDeliveryService deliveryService;

    @Autowired
    private IPagosService pagosService;

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IPedidoProductoService pedidoProductoService;

    Usuario usuario = new Usuario();

    // Datos de la Venta
    Venta venta = new Venta();

    // Datos de Pago
    Pagos pagos = new Pagos();

    // Datos de Delivery
    Delivery delivery = new Delivery();

    // Datos de Pedido
    Pedido pedido = new Pedido();

    @PostMapping("/carrito")
    public String addCart(@RequestParam Long id, Model model, HttpSession session) {

        Optional<Productos> productoOpt = productosService.get(id);

        if (productoOpt.isPresent()) {
            Productos producto = productoOpt.get();
            DetalleCarrito item = new DetalleCarrito();
            item.setProductoId(producto.getId());
            item.setNombre(producto.getNombre());
            item.setCantidad(1);
            item.setPrecio(producto.getPrecio());

            Carrito carrito = (Carrito) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new Carrito();
            }

            double totalCarrito = carrito.getItems().stream()
                    .mapToDouble(i -> i.getPrecio() * i.getCantidad())
                    .sum();

            item.setMotoapagar(totalCarrito);

            // Pasar el total al modelo
            model.addAttribute("totalCarrito", totalCarrito);

            carrito.addItem(item);
            session.setAttribute("carrito", carrito);

        } else {
            return "redirect:/ordenadelivery";
        }

        return "redirect:/mostrarCarrito";
    }

    @GetMapping("/mostrarCarrito")
    public String verCarrito(Model model, HttpSession session) {

        Carrito carrito = (Carrito) session.getAttribute("carrito");

        // Si no hay carrito en la sesión, crea uno nuevo (esto puede variar según tu
        // lógica de negocio)
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        // Calcula el total del carrito
        double totalCarrito = carrito.getItems().stream()
                .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                .sum();

        // Añade el carrito y el total al modelo
        model.addAttribute("carrito", carrito);
        model.addAttribute("totalCarrito", totalCarrito);
        return "/ProcesarVenta/procesarventa"; // Nombre de tu vista del carrito
    }

    @GetMapping("/eliminar/carrito/{id}")
    public String eliminarDelCarrito(@PathVariable Long id, HttpSession session) {
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (carrito != null) {
            carrito.removeItem(id);
        }

        return "redirect:/mostrarCarrito";
    }

    @GetMapping("/resumenPago")
    public String order(Model model, HttpSession session) {

        Carrito carrito = (Carrito) session.getAttribute("carrito");

        // Usuario usuario =usuarioService.findById(1).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica que el usuario esté autenticado antes de obtener su información.
        if (authentication.isAuthenticated()) {
            String username = authentication.getName(); // Obtiene el nombre de usuario

            Usuario usuario = usuarioService.findByUsername(username);

            // Agrega el usuario al modelo
            model.addAttribute("usuario", usuario);

        }

        double totalCarrito = carrito.getItems().stream()
                .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                .sum();

        model.addAttribute("totalCarrito", totalCarrito);
        session.setAttribute("carrito", carrito);
        model.addAttribute("venta", venta);

        return "VentaResumen";
    }

    // guardar la orden

    @PostMapping(value = "/saveOrder")
    public String saveOrder(@RequestParam(value = "dni", required = false) Long dni,
            @RequestParam(value = "ruc", required = false) Long ruc,
            @RequestParam("direccion") String direccion,
            @RequestParam("metodo_pago") String metodo_pago,
            @RequestParam("telefono") String telefono,
            @RequestParam(value = "razonSocial", required = false, defaultValue = "") String razonSocial,
            @RequestParam(value = "nombreCliente", required = false, defaultValue = "") String nombreCliente,
            HttpSession session) {

        Carrito carrito = (Carrito) session.getAttribute("carrito");

        Usuario usuario = usuarioService.findById(1).get();

        double totalCarrito = carrito.getItems().stream()
                .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                .sum();

        // Configurar los detalles de la venta...
        venta.setFechaVenta(new Date());
        venta.setIdUsuario(usuario);
        ventasService.save(venta);

        // Configurar los detalles del pedido...


            pedido.setFechaPedido(new Date());
            pedido.setTipoPedido("Delivery");
            pedido.setVenta(venta); // Asociar la venta con el pedido
            pedidoService.save(pedido);       


       
        for (DetalleCarrito item : carrito.getItems()) {
            PedidoProducto pedidoProducto = new PedidoProducto();
            pedidoProducto.setPedidos(pedido);
            pedidoProducto.setProducto(productosService.findById(item.getProductoId()).orElse(null));
            pedidoProducto.setCantidad(item.getCantidad());
            pedidoProductoService.save(pedidoProducto);
        }  

        // Crear y guardar el Pago

        // Configurar los detalles de pago...

        // Pagos
        if (dni != null && !dni.toString().isEmpty()) {
            pagos.setDni(dni);
            pagos.setNombreCliente(nombreCliente);
        } else {
            pagos.setDni(null);
        }
        if (ruc != null && !ruc.toString().isEmpty()) {
            pagos.setRuc(ruc);
            pagos.setRazonSocial(razonSocial);
        } else {
            pagos.setRuc(null);
        }
        pagos.setMetodo_pago(metodo_pago);
        pagos.setFecha(new Date());
        pagos.setIdVenta(venta);
        pagos.setMonto(totalCarrito);
        pagosService.save(pagos);

        // Crear y guardar la información de Delivery si es necesaria

        // Configurar los detalles de entrega...

        delivery.setPedido(pedido);
        delivery.setEstado("En Preparación");
        delivery.setTelefono(telefono);
        delivery.setDireccion(direccion);
        deliveryService.save(delivery);

        // Limpiar el carrito
        session.removeAttribute("carrito");
        venta = new Venta();
        pagos = new Pagos();
        delivery = new Delivery();
        pedido = new Pedido();
        
       return "redirect:/";
    }

    @Autowired
    private PdfService pdfService;

    @GetMapping("/{idPedido}/descargarFactura")
    public ResponseEntity<ByteArrayResource> descargarFactura(@PathVariable Long idPedido) throws DocumentException {

        Venta pedido = ventasService.findById(idPedido);

        Pagos pagos = pagosService.findById(idPedido);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayInputStream byteArrayInputStream = pdfService.generateInvoice(pedido, pagos);
        ByteArrayResource byteArrayResource = new ByteArrayResource(byteArrayInputStream.readAllBytes());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename= factura_" + idPedido + ".pdf")
                .body(byteArrayResource);
    }

}
