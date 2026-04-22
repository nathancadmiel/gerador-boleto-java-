package boletoreal;

import java.io.FileOutputStream;
import java.awt.Color;
import org.openpdf.text.*;
import org.openpdf.text.pdf.*;

public class GeradorPdf {

    public static void gerarPdf(Boleto b) {
        Document doc = new Document(PageSize.A4);
        doc.setMargins(30, 30, 30, 30);

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("boleto_" + b.getBanco() + ".pdf"));
            doc.open();

            Color corPrincipal = Color.BLACK;
            String nomeBanco = "BANCO";

            if (b.getBanco().equals("260")) {
                corPrincipal = new Color(138, 5, 190);
                nomeBanco = "Nu Pagamentos S.A.";
            } else if (b.getBanco().equals("237")) {
                corPrincipal = new Color(204, 9, 47);
                nomeBanco = "BRADESCO S.A.";
            } else if (b.getBanco().equals("341")) {
                corPrincipal = new Color(236, 112, 0);
                nomeBanco = "ITAÚ UNIBANCO S.A.";
            }

            Font fLabel = FontFactory.getFont(FontFactory.HELVETICA, 7);
            Font fDado = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font fBanco = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, corPrincipal);

            PdfPTable header = new PdfPTable(3);
            header.setWidthPercentage(100);
            header.setWidths(new float[]{2f, 0.7f, 4f});

            PdfPCell cLogo = new PdfPCell(new Phrase(nomeBanco, fBanco));
            cLogo.setBorderWidthBottom(2);
            cLogo.setPaddingBottom(5);
            header.addCell(cLogo);

            PdfPCell cCod = new PdfPCell(new Phrase(b.getBanco() + "-9", fDado));
            cCod.setHorizontalAlignment(Element.ALIGN_CENTER);
            cCod.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cCod.setBorderWidthBottom(2);
            cCod.setPaddingBottom(5);
            header.addCell(cCod);

            PdfPCell cLinha = new PdfPCell(new Phrase(b.getLinhaDigitavel(), fDado));
            cLinha.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cLinha.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cLinha.setBorderWidthBottom(2);
            cLinha.setPaddingBottom(5);
            header.addCell(cLinha);

            doc.add(header);

            PdfPTable corpo = new PdfPTable(4);
            corpo.setWidthPercentage(100);

            corpo.addCell(cel("Local de Pagamento", "PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO", 3, fLabel, fDado));
            corpo.addCell(cel("Vencimento", b.getDataVencimento(), 1, fLabel, fDado));

            corpo.addCell(cel("Beneficiário", b.getCedente() + " - CPF/CNPJ: " + b.getCpfCedente(), 3, fLabel, fDado));
            corpo.addCell(cel("Agência / Código Beneficiário", b.getAgencia() + " / " + b.getConta(), 1, fLabel, fDado));

            corpo.addCell(cel("Endereço Beneficiário", b.getEnderecoCedente(), 4, fLabel, fDado));

            corpo.addCell(cel("Data do Doc.", b.getDataEmissao(), 1, fLabel, fDado));
            corpo.addCell(cel("Nosso Número", b.getNossoNumero(), 1, fLabel, fDado));
            corpo.addCell(cel("Espécie Moeda", "R$", 1, fLabel, fDado));
            corpo.addCell(cel("Valor do Documento", "R$ " + String.format("%.2f", b.getValor()), 1, fLabel, fDado));

            PdfPCell cPagador = new PdfPCell();
            cPagador.setColspan(4);
            cPagador.setPadding(5);
            cPagador.addElement(new Phrase("Pagador", fLabel));
            cPagador.addElement(new Phrase(b.getPagador() + " - " + b.getCpfPagador(), fDado));
            cPagador.addElement(new Phrase(b.getEnderecoPagador(), fLabel));

            corpo.addCell(cPagador);

            doc.add(corpo);

            doc.add(new Paragraph(" "));

            PdfContentByte cb = writer.getDirectContent();
            BarcodeInter25 barcode = new BarcodeInter25();

            String codigoLimpo = b.getLinhaDigitavel().replaceAll("[^0-9]", "");
            if (codigoLimpo.length() >= 44) {
                barcode.setCode(codigoLimpo.substring(0, 44));
            } else {
                barcode.setCode(codigoLimpo);
            }

            Image img = barcode.createImageWithBarcode(cb, null, null);
            img.setAlignment(Element.ALIGN_CENTER);
            img.scalePercent(120f);
            doc.add(img);

            doc.close();
            System.out.println("Arquivo PDF criado com sucesso: boleto_" + b.getBanco() + ".pdf");

        } catch (Exception e) {
            System.err.println("Erro ao gerar PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static PdfPCell cel(String label, String valor, int col, Font fl, Font fd) {
        PdfPCell c = new PdfPCell();
        c.setColspan(col);
        c.addElement(new Phrase(label, fl));
        c.addElement(new Phrase(valor, fd));
        c.setPadding(5);
        return c;
    }
}