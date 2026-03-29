package boletoreal;

import java.io.FileOutputStream;
import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.pdf.Barcode128;
import org.openpdf.text.pdf.PdfContentByte;
import org.openpdf.text.Image;

public class GeradorPdf {

    public static void gerarPdf(Boleto b) {
        Document doc = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("boleto_completo.pdf"));
            doc.open();

            doc.add(new Paragraph("BOLETO BANCÁRIO"));
            doc.add(new Paragraph("--------------------------------------------------"));
            doc.add(new Paragraph("Banco: " + b.getBanco()));
            doc.add(new Paragraph("Agência: " + b.getAgencia()));
            doc.add(new Paragraph("Conta: " + b.getConta()));
            doc.add(new Paragraph("Valor: R$ " + b.getValor()));
            doc.add(new Paragraph("Pagador: " + b.getPagador()));
            doc.add(new Paragraph("--------------------------------------------------"));
            doc.add(new Paragraph("Linha Digitável:"));
            doc.add(new Paragraph(b.getLinhaDigitavel()));
            doc.add(new Paragraph(" ")); // Espaço em branco

            // Geração do Código de Barras
            PdfContentByte cb = writer.getDirectContent();
            Barcode128 barcode128 = new Barcode128();
            barcode128.setCode(b.getLinhaDigitavel().replaceAll(" ", "")); // Remove espaços para o código
            Image image = barcode128.createImageWithBarcode(cb, null, null);
            doc.add(image);

            doc.close();
            System.out.println("✅ PDF gerado com sucesso: boleto_completo.pdf");

        } catch (Exception e) {
            System.err.println("❌ Erro ao gerar o PDF: " + e.getMessage());
        }
    }
}