package boletoreal;

import java.io.FileOutputStream;
import java.awt.Color;
import org.openpdf.text.*;
import org.openpdf.text.pdf.*;

public class GeradorPdf {

    public static void gerarPdf(Boleto b) {
        Document doc = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("boleto_" + b.getBanco() + ".pdf"));
            doc.open();

            Color corBanco;
            String nomeExibicao;

            switch (b.getBanco()) {
                case "341":
                    corBanco = new Color(236, 112, 0); 
                    nomeExibicao = "ITAÚ UNIBANCO S.A.";
                    break;
                case "237":
                    corBanco = new Color(204, 9, 47); 
                    nomeExibicao = "BRADESCO S.A.";
                    break;
                case "260":
                    corBanco = new Color(138, 5, 190); 
                    nomeExibicao = "NUBANK";
                    break;
                default:
                    corBanco = Color.BLACK;
                    nomeExibicao = "BANCO DESCONHECIDO";
            }

            PdfPTable header = new PdfPTable(3);
            header.setWidthPercentage(100);
            header.setWidths(new float[]{2.0f, 0.7f, 4f});

            PdfPCell cBanco = new PdfPCell(new Phrase(nomeExibicao, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, corBanco)));
            cBanco.setBorderWidthBottom(2);
            cBanco.setPaddingBottom(8);
            header.addCell(cBanco);

            PdfPCell cCod = new PdfPCell(new Phrase(b.getBanco() + "-9", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            cCod.setBorderWidthBottom(2);
            cCod.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cCod.setPaddingBottom(8);
            header.addCell(cCod);

            PdfPCell cLinha = new PdfPCell(new Phrase(b.getLinhaDigitavel(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11)));
            cLinha.setBorderWidthBottom(2);
            cLinha.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cLinha.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cLinha.setPaddingBottom(8);
            header.addCell(cLinha);
            doc.add(header);

            PdfPTable corpo = new PdfPTable(4);
            corpo.setWidthPercentage(100);

            corpo.addCell(cel("Local de Pagamento", "PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO", 3));
            corpo.addCell(cel("Vencimento", b.getDataVencimento(), 1));
            
            corpo.addCell(cel("Cedente / Beneficiário", b.getCedente(), 3));
            corpo.addCell(cel("Agência/Código Cedente", b.getAgencia() + " / " + b.getConta(), 1));

            corpo.addCell(cel("Data do Documento", b.getDataEmissao(), 1));
            corpo.addCell(cel("Espécie Doc.", "DM", 1));
            corpo.addCell(cel("Aceite", "N", 1));
            corpo.addCell(cel("Nosso Número", b.getNossoNumero(), 1));

            corpo.addCell(cel("Uso do Banco", "", 1));
            corpo.addCell(cel("Carteira", "09", 1));
            corpo.addCell(cel("Moeda", "R$", 1));
            corpo.addCell(cel("Valor do Documento", "R$ " + b.getValor(), 1));

            corpo.addCell(cel("Pagador / Sacado", b.getPagador(), 4));

            doc.add(corpo);

            doc.add(new Paragraph(" "));
            PdfContentByte cb = writer.getDirectContent();
            Barcode128 barcode = new Barcode128();
            barcode.setCode(b.getLinhaDigitavel().replaceAll("[^0-9]", ""));
            Image img = barcode.createImageWithBarcode(cb, null, null);
            img.setAlignment(Element.ALIGN_CENTER);
            img.scalePercent(180f); 
            doc.add(img);

            doc.close();
            System.out.println("PDF gerado: boleto_" + b.getBanco() + ".pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PdfPCell cel(String t, String v, int col) {
        PdfPCell c = new PdfPCell();
        c.setColspan(col);
        c.addElement(new Phrase(t, FontFactory.getFont(FontFactory.HELVETICA, 7))); 
        c.addElement(new Phrase(v, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11))); 
        c.setPaddingTop(5);
        c.setPaddingBottom(12); 
        return c;
    }
}