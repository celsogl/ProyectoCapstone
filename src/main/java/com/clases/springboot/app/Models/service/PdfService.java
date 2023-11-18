package com.clases.springboot.app.Models.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.clases.springboot.app.Models.Entity.Pagos;
import com.clases.springboot.app.Models.Entity.Pedido;
import com.clases.springboot.app.Models.Entity.PedidoProducto;
import com.clases.springboot.app.Models.Entity.Venta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

@Service
public class PdfService {

    public ByteArrayInputStream generateInvoice(Venta pedido, Pagos pagos) {
        Document document = new Document(PageSize.LETTER);
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12); // Define la fuente en negrita
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hora = new SimpleDateFormat("HH:hh");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Image image = Image.getInstance("logo.jpg"); // Reemplaza con la ruta correcta
            image.scaleToFit(200f, 100f); // Ajusta el tamaño según sea necesario
            image.setAlignment(Image.ALIGN_CENTER); // Alinea la imagen al centro
            document.add(image);

            Paragraph nombreRest = new Paragraph("RESTAURANTE ASTURIAS");
            nombreRest.setAlignment(Element.ALIGN_CENTER); // Alineación al centro
            document.add(nombreRest);

            Paragraph rucRest = new Paragraph("R.U.C.: 20209541590");
            rucRest.setAlignment(Element.ALIGN_CENTER);
            document.add(rucRest);

            Paragraph direcRest = new Paragraph("Jr. Francisco Pizarro Nro. 739");
            direcRest.setAlignment(Element.ALIGN_CENTER);
            document.add(direcRest);

            Paragraph ciudadRest = new Paragraph("La Libertad - Trujillo - Perú");
            ciudadRest.setAlignment(Element.ALIGN_CENTER);
            document.add(ciudadRest);

            Paragraph telfRest = new Paragraph("Telf: 044-258100 ");
            telfRest.setAlignment(Element.ALIGN_CENTER);
            document.add(telfRest);

            PdfPTable table = new PdfPTable(1); // 1 columna
            table.setWidthPercentage(100); // La tabla ocupa el 100% del ancho de la página
            PdfPCell cell = new PdfPCell(new Paragraph("------------------------------------------------"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Alineación al centro
            cell.setBorder(PdfPCell.NO_BORDER); // Sin bordes
            // Añadir la celda a la tabla
            table.addCell(cell);
            // Añadir la tabla al documento
            document.add(table);

            // Crear un párrafo para el título "Factura de Pedido"
            
            if (pagos.getDni() != null ) {
                Paragraph dni = new Paragraph("BOLETA ELECTRÓNICA", boldFont);
                dni.setAlignment(Element.ALIGN_CENTER); // Alineación al centro
                document.add(dni);

                Paragraph boleta = new Paragraph("B0001 - 0000"+ pedido.getId().toString());
                boleta.setAlignment(Element.ALIGN_CENTER);
                document.add(boleta);
            } else{

                Paragraph factura = new Paragraph("FACTURA ELECTRÓNICA", boldFont);
                factura.setAlignment(Element.ALIGN_CENTER); // Alineación al centro
                document.add(factura);

                Paragraph fact = new Paragraph("F0001 - 0000"+ pedido.getId().toString());
                fact.setAlignment(Element.ALIGN_CENTER);
                document.add(fact);
             }
            
            PdfPTable table2 = new PdfPTable(1); // 1 columna
            table2.setWidthPercentage(100); // La tabla ocupa el 100% del ancho de la página
            // Crear una celda para la segunda línea de guiones
            PdfPCell cell2 = new PdfPCell(new Paragraph("------------------------------------------------"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER); // Alineación al centro
            cell2.setBorder(PdfPCell.NO_BORDER); // Sin bordes
            // Añadir la celda a la tabla
            table2.addCell(cell2);
            // Añadir la tabla al documento
            document.add(table2);

            String formattedDate = sdf.format(pedido.getFechaVenta());

            String horas = hora.format(pedido.getFechaVenta());


            Paragraph fecha = new Paragraph("Fecha: " + formattedDate + "           Hora: " + horas);
            fecha.setAlignment(Element.ALIGN_CENTER);
            document.add(fecha);

            
            if (pagos.getDni()!= null) {
            Paragraph nombreCliente = new Paragraph("CLIENTE: " + pagos.getNombreCliente());
            nombreCliente.setAlignment(Element.ALIGN_CENTER);
            document.add(nombreCliente);
            
            Paragraph documentoCliente = new Paragraph("DNI: " + pagos.getDni().toString());
            documentoCliente.setAlignment(Element.ALIGN_CENTER);
            document.add(documentoCliente);
            }else{
                Paragraph razonSocial = new Paragraph("RAZON SOCIAL: " + pagos.getRazonSocial());
                razonSocial.setAlignment(Element.ALIGN_CENTER);
                document.add(razonSocial);
                
                Paragraph rucRazon = new Paragraph("R.U.C.: " + pagos.getRuc().toString());
                rucRazon.setAlignment(Element.ALIGN_CENTER);
                document.add(rucRazon);
            }
            
            


            PdfPTable table3 = new PdfPTable(1); // 1 columna
            table3.setWidthPercentage(100); // La tabla ocupa el 100% del ancho de la página
            // Crear una celda para la segunda línea de guiones
            PdfPCell cell3 = new PdfPCell(new Paragraph("-------------------------------------------------------------"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER); // Alineación al centro
            cell3.setBorder(PdfPCell.NO_BORDER); // Sin bordes
            // Añadir la celda a la tabla
            table3.addCell(cell3);
            // Añadir la tabla al documento
            document.add(table3);



            // Añadir la línea de "CANT----------DESCRIPCION----------TOTAL"
            Paragraph cantDescTotal = new Paragraph("CANT          DESCRIPCIÓN          TOTAL");
            cantDescTotal.setAlignment(Element.ALIGN_CENTER); // Opcional, si deseas centrar esta línea también
            document.add(cantDescTotal);

            PdfPTable table4 = new PdfPTable(1); // 1 columna
            table4.setWidthPercentage(100); // La tabla ocupa el 100% del ancho de la página
            // Crear una celda para la segunda línea de guiones
            PdfPCell cell4 = new PdfPCell(new Paragraph("-------------------------------------------------------------"));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER); // Alineación al centro
            cell4.setBorder(PdfPCell.NO_BORDER); // Sin bordes
            // Añadir la celda a la tabla
            table4.addCell(cell4);
            // Añadir la tabla al documento
            document.add(table4);

            
            // Recorremos cada detalle y lo agregamos al PDF
            


                    for (Pedido pedidos : pedido.getIdPedido()) {
                        for (PedidoProducto producto: pedidos.getPedidoProductos()) {
                           
                            String nombreProducto = producto.getProducto().getNombre();
                    
                            //Aquí, utiliza nombreProducto para crear tu Paragraph
                            double precioUnitario = producto.getProducto().getPrecio();
                            int cantidad = producto.getCantidad();
                            double costoTotal = precioUnitario * cantidad;

                            // Formatea el costo total a dos decimales
                            String costoTotalFormateado = String.format("%.2f", costoTotal);

                            // Crea el párrafo con la cantidad, nombre del producto y el costo total formateado
                            Paragraph menu = new Paragraph(
                                cantidad + "                  " + nombreProducto + "          " + " S/. " + costoTotalFormateado
                            );

                            // Establece la alineación y añade el párrafo al documento
                            menu.setAlignment(Element.ALIGN_CENTER);
                            document.add(menu);
                        }
                    }
          
            
            
    
            
            PdfPTable table5 = new PdfPTable(1); // 1 columna
            table5.setWidthPercentage(100); // La tabla ocupa el 100% del ancho de la página
            // Crear una celda para la segunda línea de guiones
            PdfPCell cell5 = new PdfPCell(new Paragraph("-------------------------------------------------------------"));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER); // Alineación al centro
            cell5.setBorder(PdfPCell.NO_BORDER); // Sin bordes
            // Añadir la celda a la tabla
            table5.addCell(cell5);
            // Añadir la tabla al documento
            document.add(table5);
            
            double montoVenta = pagos.getMonto().doubleValue();
            double valorOpGravada = montoVenta - (montoVenta * 0.18);
            String opGravadaTexto = String.format("OP.GRAVADA: S/. %.2f", valorOpGravada);

            Paragraph opgravada = new Paragraph("                                 " + opGravadaTexto);
            opgravada.setAlignment(Element.ALIGN_CENTER);
            document.add(opgravada);


            double igvValor = montoVenta * 0.18;
            String igvTexto = String.format(" IGV(18%%): S/. %.2f", igvValor);

            Paragraph igv = new Paragraph("                                          " + igvTexto);
            igv.setAlignment(Element.ALIGN_CENTER);
            document.add(igv);


            Paragraph totalaPagar = new Paragraph("                                               TOTAL: S/. " + montoVenta);
            totalaPagar.setAlignment(Element.ALIGN_CENTER);
            document.add(totalaPagar);

            PdfPTable table6 = new PdfPTable(1); // 1 columna
            table6.setWidthPercentage(100); // La tabla ocupa el 100% del ancho de la página
            // Crear una celda para la segunda línea de guiones
            PdfPCell cell6 = new PdfPCell(new Paragraph("-------------------------------------------------------------"));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER); // Alineación al centro
            cell6.setBorder(PdfPCell.NO_BORDER); // Sin bordes
            // Añadir la celda a la tabla
            table6.addCell(cell6);
            // Añadir la tabla al documento
            document.add(table6);

            Paragraph despedida = new Paragraph("¡Esperamos verlo pronto de nuevo!");
            despedida.setAlignment(Element.ALIGN_CENTER);
            document.add(despedida);



            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
