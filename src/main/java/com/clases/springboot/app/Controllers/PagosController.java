package com.clases.springboot.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.clases.springboot.app.Models.service.IPagosService;

@Controller
@SessionAttributes("pagos")
public class PagosController {
    @Autowired
    private IPagosService pagosService;

    @RequestMapping(value = "/listapagos", method = RequestMethod.GET)
	public String listarProductos(Model model) {
		model.addAttribute("pagos", pagosService.findAll());
		return "/Pagos/listarPagos";
	}

}
