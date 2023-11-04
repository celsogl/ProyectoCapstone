package com.clases.springboot.app.Models.service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.clases.springboot.app.Models.Entity.ventaDetalle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PdfService {


  

    public ByteArrayInputStream generateInvoice(List<ventaDetalle> detallesVenta) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
    
            // Aquí agregarás el contenido de tu factura
            document.add(new Paragraph("------------------------------------------------"));
            document.add(new Paragraph("Factura de Pedido"));
            
            // Asumiendo que todos los detalles pertenecen a la misma venta:
            document.add(new Paragraph("Fecha: " + detallesVenta.get(0).getIdVenta().getFecha().toString()));
            document.add(new Paragraph("Mozo: " + detallesVenta.get(0).getIdVenta().getIdUsuario().getNombre().toString()));
            document.add(new Paragraph("CANT----------DESCRIPCION----------TOTAL"));
            
            // Recorremos cada detalle y lo agregamos al PDF
            for(ventaDetalle detalle : detallesVenta) {
                document.add(new Paragraph(detalle.getCantidad() + "     " + detalle.getIdProductos().getNombre().toString() + "     " + detalle.getPrecioproducto()));
            }
            
            document.add(new Paragraph("Total a pagar S/.  " + detallesVenta.get(0).getIdVenta().getMontoventa().doubleValue()));
    
            document.close();
        } catch (DocumentException e) {
            // Manejar error
        }
    
        return new ByteArrayInputStream(out.toByteArray());
    }

}
