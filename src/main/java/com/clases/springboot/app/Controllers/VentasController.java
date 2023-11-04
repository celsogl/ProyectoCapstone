package com.clases.springboot.app.Controllers;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.clases.springboot.app.Models.Entity.Venta;

import com.clases.springboot.app.Models.service.IVentasService;


@Controller
@SessionAttributes("ventas")
public class VentasController {
    @Autowired
    private IVentasService ventasService;


    @RequestMapping(value = "/listaventas", method = RequestMethod.GET)
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventasService.findAll());
        return "/Ventas/listarVentas";
    }

    @RequestMapping(value = "/createventas")
    public String crearVentas(Map<String, Object> model) {
        Venta ventas = new Venta();
        model.put("ventas", ventas);
        return "/Ventas/registrarVentas";
    }

    @RequestMapping(value = "/createventas", method = RequestMethod.POST)
    public String guardarVentas(Venta ventas) {
        ventasService.save(ventas);
        return "redirect:listaproductos";
    }

    @RequestMapping(value = "/formventas/{id}")
    public String editarVentas(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Venta ventas = null;

        if (id > 0) {
            ventas = ventasService.findById(id);
        } else {
            return "redirect:/listaventas";
        }
        model.put("ventas", ventas);

        return "/Ventas/registrarVentas";
    }

    @RequestMapping(value = "/eliminarventas/{id}")
    public String eliminarVentas(@PathVariable(value = "id") Long id) {
        if (id > 0)
            ventasService.deleteById(id);
        return "redirect:/listaventas";
    }



}
