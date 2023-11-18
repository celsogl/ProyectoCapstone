package com.clases.springboot.app.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;


import com.clases.springboot.app.Models.Entity.Delivery;
import com.clases.springboot.app.Models.service.IDeliveryService;
import com.clases.springboot.app.Models.service.IProductosService;

@Controller
@SessionAttributes("delivery")
public class DeliveryController {

    @Autowired
    private IDeliveryService deliveryService;

	@Autowired
	private IProductosService productosService;

     @RequestMapping(value = "/listadelivery", method = RequestMethod.GET)
	public String listarDelivery(Model model) {
		model.addAttribute("delivery", deliveryService.findAll());
		return "/Delivery/listarDelivery";
	}

	@RequestMapping(value = "/ordenadelivery", method = RequestMethod.GET)
	public String listarOrdena(Model model) {
		model.addAttribute("productos", productosService.findAll());
		return "/OrdenaDelivery/ordenaDelivery";
	}


	 @RequestMapping(value = "/formdelivery/{id}")
    public String editarVentas(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Delivery delivery = null;

        if (id > 0) {
            delivery = deliveryService.findById(id);
        } else {
            return "redirect:/listadelivery";
        }
        model.put("delivery", delivery);

        return "/Delivery/registrarDelivery";
    }




}
