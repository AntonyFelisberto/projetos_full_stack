package com.projetooficina.sistemaoficinaback.bytecontrol;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.projetooficina.sistemaoficinaback.dto.DadosOrcamentoDTO;
import com.projetooficina.sistemaoficinaback.exception.DataFormation;
import com.projetooficina.sistemaoficinaback.model.Materiais;
import com.projetooficina.sistemaoficinaback.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@AllArgsConstructor
public class GeneratePDF {

    public ByteArrayOutputStream generatePdf(String html, Usuario usuario) {
        PdfWriter pdfWriter = null;
        ByteArrayOutputStream documentoPDF = new ByteArrayOutputStream();
        Document documento;

        try {
            documento = getDocument(usuario, documentoPDF);

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(false);
            xmlWorkerHelper.parseXHtml(pdfWriter, documento, new ByteArrayInputStream(html.getBytes()), StandardCharsets.UTF_8);

            documento.close();
            return documentoPDF;
        } catch (Exception e) {
            throw new DataFormation("Erro ao formar pdf para envio "+e.getMessage());
        }
    }

    public ByteArrayOutputStream generatePdfUsandoClasseDTO(DadosOrcamentoDTO orcamentoDTO, Usuario usuario) {
        PdfWriter pdfWriter = null;
        ByteArrayOutputStream documentoPDF = new ByteArrayOutputStream();
        Document documento;

        try {
            documento = getDocument(usuario, documentoPDF);

            Font fontCabecalho = new Font(Font.FontFamily.TIMES_ROMAN,24,Font.BOLD);
            fontCabecalho.setColor(0,0,0);

            Font fontCorpo = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
            fontCabecalho.setColor(0,0,0);

            Paragraph cabecalho = new Paragraph();
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setFont(fontCabecalho);
            cabecalho.add("ORÇAMENTO SISTEMA V8");
            documento.add(cabecalho);

            Image image = Image.getInstance("logoOficina.png");
            image.scalePercent(20);
            image.setAlignment(Element.ALIGN_CENTER);
            documento.add(image);

            Paragraph corpo = new Paragraph();
            corpo.setAlignment(Element.ALIGN_CENTER);
            corpo.setFont(fontCorpo);

            Paragraph paragraphUm = new Paragraph("Recuperadora de Cabines - Restaurante - Customizações Recuperação de Plasticos - Pinturas - Polimnetos");
            Paragraph paragraphDois = new Paragraph("FABINHO 99144-2974");
            Paragraph paragraphTres = new Paragraph("Rod. Celso Garcia Cid, 3348 Jd.Novo Bandeirantes - Cambé");
            Paragraph paragraphQuatro = new Paragraph("N Orçamento: "+orcamentoDTO.getNumeroOrcamento());
            Paragraph paragraphCinco = new Paragraph("Cliente: "+orcamentoDTO.getCliente().getNome());
            Paragraph paragraphSeis = new Paragraph("Email: "+orcamentoDTO.getCliente().getEmail());
            Paragraph paragraphSete = new Paragraph("Veiculo: "+orcamentoDTO.getCliente().getVeiculo());
            Paragraph  paragraphOito= new Paragraph("Placa: "+orcamentoDTO.getCliente().getPlaca());
            Paragraph paragraphNove = new Paragraph("Cidade: "+orcamentoDTO.getCliente().getCidade());

            ajustarParagrafo(paragraphUm,fontCorpo);
            ajustarParagrafo(paragraphDois,fontCorpo);
            ajustarParagrafo(paragraphTres,fontCorpo);
            ajustarParagrafo(paragraphQuatro,fontCorpo);
            ajustarParagrafo(paragraphCinco,fontCorpo);
            ajustarParagrafo(paragraphSeis,fontCorpo);
            ajustarParagrafo(paragraphSete,fontCorpo);
            ajustarParagrafo(paragraphOito,fontCorpo);
            ajustarParagrafo(paragraphNove,fontCorpo);

            PdfPTable tabelaMaterialCliente = new PdfPTable(5);
            PdfPTable tabelaMaterialUsuario = new PdfPTable(5);

            if (orcamentoDTO.getMateriaisCliente().size()>0 ) {
                PdfPCell cabecalhoTabelaCliente = new PdfPCell(new Paragraph("Material Cliente"));
                FormarTabela(tabelaMaterialCliente, cabecalhoTabelaCliente,orcamentoDTO.getMateriaisCliente());
            }
            if (orcamentoDTO.getMateriaisUsuario().size() > 0) {
                PdfPCell cabecalhoTabelaUsuario = new PdfPCell(new Paragraph("Material Usuario"));
                FormarTabela(tabelaMaterialUsuario, cabecalhoTabelaUsuario,orcamentoDTO.getMateriaisUsuario());
            }

            Paragraph valorTotal = new Paragraph("Valor total: "+orcamentoDTO.getValorTotal());
            valorTotal.setAlignment(Element.ALIGN_CENTER);
            valorTotal.setFont(fontCorpo);
            valorTotal.setSpacingBefore(10);



            GregorianCalendar calendar = new GregorianCalendar();
            int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
            int mes = calendar.get(GregorianCalendar.MONTH);
            mes++;
            int ano = calendar.get(GregorianCalendar.YEAR);

            String data = String.format("Data: %d/%d/%d",dia,mes,ano);
            Paragraph dataDeCriacao = new Paragraph(data);
            dataDeCriacao.setAlignment(Element.ALIGN_CENTER);
            dataDeCriacao.setFont(fontCorpo);
            dataDeCriacao.setSpacingBefore(10);

            Paragraph vendedor = new Paragraph("Vendedor: "+orcamentoDTO.getCliente().getNome());
            vendedor.setAlignment(Element.ALIGN_CENTER);
            vendedor.setFont(fontCorpo);
            vendedor.setSpacingBefore(10);

            corpo.add(paragraphUm);
            corpo.add(paragraphDois);
            corpo.add(paragraphTres);
            corpo.add(paragraphQuatro);
            corpo.add(paragraphCinco);
            corpo.add(paragraphSeis);
            corpo.add(paragraphSete);
            corpo.add(paragraphOito);
            corpo.add(paragraphNove);
            corpo.add(tabelaMaterialCliente);
            corpo.add(tabelaMaterialUsuario);
            corpo.add(valorTotal);
            corpo.add(dataDeCriacao);
            corpo.add(vendedor);
            documento.add(corpo);

            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(false);
            xmlWorkerHelper.parseXHtml(pdfWriter, documento, new ByteArrayInputStream("".getBytes()), StandardCharsets.UTF_8);

            documento.close();
            return documentoPDF;
        } catch (Exception e) {
            throw new DataFormation("Erro ao formar pdf para envio "+e.getMessage());
        }
    }

    private void ajustarParagrafo(Paragraph paragraph,Font fontCorpo) {
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setFont(fontCorpo);
        paragraph.setSpacingAfter(10);
    }

    private void FormarTabela(PdfPTable tabelaMaterial, PdfPCell cabecalhoTabela, List<Materiais> materiais) throws DocumentException {
        tabelaMaterial.setWidths(new int[] {60,50,30,65,80});//LARGURA DE CADA BLOCO DA TABELA

        cabecalhoTabela.setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecalhoTabela.setBackgroundColor(BaseColor.GRAY);
        cabecalhoTabela.setColspan(5);

        tabelaMaterial.addCell(cabecalhoTabela);
        tabelaMaterial.addCell("Quantidade");
        tabelaMaterial.addCell("Unidade(s)");
        tabelaMaterial.addCell("Código Produto");
        tabelaMaterial.addCell("Discriminização");
        tabelaMaterial.addCell("Preço por Unidade");

        for(int i=0;i<materiais.size();i++) {
            tabelaMaterial.addCell(materiais.get(i).getQuantidade().toString());
            tabelaMaterial.addCell(materiais.get(i).getPrecoUnidade().toString());
            tabelaMaterial.addCell(materiais.get(i).getCodProduto().toString());
            tabelaMaterial.addCell(materiais.get(i).getDescriminizacao());
            tabelaMaterial.addCell(materiais.get(i).getPrecoUnidade().toString());
        }

        tabelaMaterial.setSpacingAfter(10);
    }

    private Document getDocument(Usuario usuario, ByteArrayOutputStream documentoPDF) throws DocumentException {
        Document documento;
        documento = new Document(PageSize.A4,30,20,20,30);//PROPRIEDADES DO PDF
        documento.addAuthor(usuario.getNome());
        documento.addCreationDate();
        documento.addProducer();
        documento.addCreator("Sistema V8");
        documento.addTitle("Orçamento");
        documento.setPageSize(PageSize.LETTER);

        PdfWriter.getInstance(documento, documentoPDF);

        documento.open();
        return documento;
    }

}
