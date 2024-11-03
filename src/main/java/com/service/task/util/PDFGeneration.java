package com.service.task.util;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.service.task.dto.InvoiceRequest;

@Component
public class PDFGeneration {
	
	public String generateInvoicePdf(InvoiceRequest request) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("invoice.pdf"));
			
			document.open();
			
			float [] firstColumnWidths = {1F, 1F};
			float [] secondColumnWidths = {2F, 1F, 1F, 1F};
			//header row
			PdfPTable headerTable = new PdfPTable(2);
			headerTable.setWidthPercentage(100);
			headerTable.setWidths(firstColumnWidths);
			
			Paragraph sellerParagraph = new Paragraph();
			sellerParagraph.setIndentationLeft(20f);
			sellerParagraph.add(Chunk.NEWLINE);
			sellerParagraph.add("Seller:");
			sellerParagraph.add(Chunk.NEWLINE);
			sellerParagraph.add(request.getSeller());
			sellerParagraph.add(Chunk.NEWLINE);
			sellerParagraph.add(request.getSellerAddress());
			sellerParagraph.add(Chunk.NEWLINE);
			sellerParagraph.add("GSTIN: " + request.getSellerGstin());
			sellerParagraph.add(Chunk.NEWLINE);
			sellerParagraph.setSpacingAfter(20f);
			
			PdfPCell sellerCell = new PdfPCell();
			sellerCell.addElement(sellerParagraph);
            sellerCell.setBorder(Rectangle.BOX);
            headerTable.addCell(sellerCell);
			
            Paragraph buyerParagraph = new Paragraph();
            buyerParagraph.setIndentationLeft(20f);
            buyerParagraph.add(Chunk.NEWLINE);
            buyerParagraph.add("Buyer:");
            buyerParagraph.add(Chunk.NEWLINE);
            buyerParagraph.add(request.getBuyer());
			buyerParagraph.add(Chunk.NEWLINE);
			buyerParagraph.add(request.getBuyerAddress());
			buyerParagraph.add(Chunk.NEWLINE);
			buyerParagraph.add("GSTIN: " + request.getBuyerGstin());
			buyerParagraph.add(Chunk.NEWLINE);
			buyerParagraph.setSpacingAfter(20f);
            
            PdfPCell buyerCell = new PdfPCell();
            buyerCell.addElement(buyerParagraph);
            buyerCell.setBorder(Rectangle.BOX);
            headerTable.addCell(buyerCell);
            
            document.add(headerTable);
            
            //item details row
            PdfPTable itemsTable = new PdfPTable(4);
            itemsTable.setWidthPercentage(100);
            itemsTable.setWidths(secondColumnWidths);
            
            PdfPCell itemscell1 = new PdfPCell();
            itemscell1.addElement(paragraphAlignment("Item"));
            itemsTable.addCell(itemscell1);
            
            PdfPCell itemscell2 = new PdfPCell();
            itemscell2.addElement(paragraphAlignment("Quantity"));
            itemsTable.addCell(itemscell2);
            
            PdfPCell itemscell3 = new PdfPCell();
            itemscell3.addElement(paragraphAlignment("Rate"));
            itemsTable.addCell(itemscell3);
            
            PdfPCell itemscell4 = new PdfPCell();
            itemscell4.addElement(paragraphAlignment("Amount"));
            itemsTable.addCell(itemscell4);
            
            List<InvoiceRequest.Item> items = request.getItems();
            for (InvoiceRequest.Item item : items) {
            	itemscell1 = new PdfPCell();
            	itemscell2 = new PdfPCell();
            	itemscell3 = new PdfPCell();
            	itemscell4 = new PdfPCell();
            	itemscell1.addElement(paragraphAlignment(item.getName()));
                itemsTable.addCell(itemscell1);
                itemscell2.addElement(paragraphAlignment(item.getQuantity()));
                itemsTable.addCell(itemscell2);
                itemscell3.addElement(paragraphAlignment(String.valueOf(item.getRate())));
                itemsTable.addCell(itemscell3);
                itemscell4.addElement(paragraphAlignment(String.valueOf(item.getAmount())));
                itemsTable.addCell(itemscell4);
            }

            document.add(itemsTable);
			document.close();
			return "invoice.pdf";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Error generating invoice";
		}
	}
	//
	 public Paragraph paragraphAlignment(String item) {
		 Paragraph paragraph = new Paragraph();
         paragraph.setAlignment(Element.ALIGN_CENTER);
         paragraph.setSpacingAfter(10f);
         paragraph.add(item);
         return paragraph;
	 }
}
