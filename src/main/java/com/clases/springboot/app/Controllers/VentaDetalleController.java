package com.clases.springboot.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.clases.springboot.app.Models.service.IVentaDetalleService;

@Controller
@SessionAttributes("ventaDetalle")
public class VentaDetalleController {

    @Autowired
    private IVentaDetalleService ventaDetalleService;
    
    
    @RequestMapping(value = "/listadetalleventa", method = RequestMethod.GET)
    public String listarDetalleVentas(Model model) {
        model.addAttribute("ventas", ventaDetalleService.findAll());
        return "/Ventas/ventaDetalle";
    }
}
