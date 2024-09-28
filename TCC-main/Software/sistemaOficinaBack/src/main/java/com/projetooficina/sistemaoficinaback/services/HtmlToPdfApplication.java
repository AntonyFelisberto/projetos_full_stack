package com.projetooficina.sistemaoficinaback.services;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.GregorianCalendar;

import com.projetooficina.sistemaoficinaback.bytecontrol.GeneratePDF;
import com.projetooficina.sistemaoficinaback.dto.DadosOrcamentoDTO;
import com.projetooficina.sistemaoficinaback.dto.EmailHtml;
import com.projetooficina.sistemaoficinaback.model.Usuario;
import lombok.AllArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HtmlToPdfApplication {

    private final UsuarioServices usuarioServices;
    private final GeneratePDF generateEmailPdf;

    public byte[] createPdf(Long idUsuario, DadosOrcamentoDTO orcamentoDTO, EmailHtml emailHtml) {

        Usuario usuario = usuarioServices.encontrarUsuarioPorId(idUsuario);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        VelocityEngine ve = new VelocityEngine();
        String materialUsuario = "";
        String materialCliente = "";
        String formularDados = "";

        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        Template t = ve.getTemplate("templates/templateHtml.vm");
        VelocityContext context = new VelocityContext();

        context.put("numeroOrcamento", orcamentoDTO.getNumeroOrcamento());
        context.put("nomeCliente", orcamentoDTO.getCliente().getNome());
        context.put("emailCliente", orcamentoDTO.getCliente().getEmail());
        context.put("enderecoCliente", orcamentoDTO.getCliente().getEndereco());
        context.put("veiculoCliente", orcamentoDTO.getCliente().getVeiculo());
        context.put("placaCliente", orcamentoDTO.getCliente().getPlaca());
        context.put("cidadeCliente", orcamentoDTO.getCliente().getCidade());
        context.put("estadoCliente", orcamentoDTO.getCliente().getEstado());
        context.put("telefoneCliente", orcamentoDTO.getCliente().getTelefone());
        context.put("celularCliente", orcamentoDTO.getCliente().getCelular());

        if (orcamentoDTO.getMateriaisCliente().size()>0 ) {
            formularDados = formularDados.concat("<table class=\"terc\">");
            formularDados = formularDados.concat("<tr class=\"col\">\n" +
                                                        "<td style=\"text-align: center;\" colspan=\"5\">Materiais Cliente</td>\n" +
                                                    "</tr>");
            formularDados = formularDados.concat("<tr class=\"col\">\n" +
                                                        "<td class=\"col\">Quantidade</td>\n" +
                                                        "<td class=\"col\">Unidade(s)</td>\n" +
                                                        "<td class=\"col\">Código Produto</td>\n" +
                                                        "<td class=\"col\">Discriminização</td>\n" +
                                                        "<td class=\"col\">Preço por Unidade</td>\n" +
                                                      "</tr>");
            for (int dadosMaterialCliente = 0; dadosMaterialCliente < orcamentoDTO.getMateriaisCliente().size(); dadosMaterialCliente++) {
                materialCliente = materialCliente.concat("<tr class=\"col\">\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisCliente().get(dadosMaterialCliente).getQuantidade()+"</td>\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisCliente().get(dadosMaterialCliente).getUnidade()+"</td>\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisCliente().get(dadosMaterialCliente).getCodProduto()+"</td>\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisCliente().get(dadosMaterialCliente).getDescriminizacao()+"</td>\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisCliente().get(dadosMaterialCliente).getPrecoUnidade()+"</td>\n" +
                                                             "</tr>");
            }
            formularDados = formularDados.concat(materialCliente);
            formularDados = formularDados.concat("</table>");
        }

        if (orcamentoDTO.getMateriaisUsuario().size() > 0) {
            formularDados = formularDados.concat("<table class=\"terc\">");
            formularDados = formularDados.concat("<tr class=\"col\">\n" +
                                                        "<td style=\"text-align: center;\" colspan=\"5\">Materiais Usuario</td>\n" +
                                                     "</tr>");
            formularDados = formularDados.concat("<tr class=\"col\">\n" +
                                                        "<td class=\"col\">Quantidade</td>\n" +
                                                        "<td class=\"col\">Unidade(s)</td>\n" +
                                                        "<td class=\"col\">Código Produto</td>\n" +
                                                        "<td class=\"col\">Discriminização</td>\n" +
                                                        "<td class=\"col\">Preço por Unidade</td>\n" +
                                                      "</tr>");
            for (int dadosMaterialUsuario = 0; dadosMaterialUsuario < orcamentoDTO.getMateriaisUsuario().size(); dadosMaterialUsuario++) {
                materialUsuario = materialUsuario.concat("<tr class=\"col\">\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisUsuario().get(dadosMaterialUsuario).getQuantidade()+"</td>\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisUsuario().get(dadosMaterialUsuario).getUnidade()+"</td>\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisUsuario().get(dadosMaterialUsuario).getCodProduto()+"</td>\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisUsuario().get(dadosMaterialUsuario).getDescriminizacao()+"</td>\n" +
                                                                "<td class=\"col\">"+orcamentoDTO.getMateriaisUsuario().get(dadosMaterialUsuario).getPrecoUnidade()+"</td>\n" +
                                                             "</tr>");
            }
            formularDados = formularDados.concat(materialUsuario);
            formularDados = formularDados.concat("</table>");
        }

        context.put("dadosTabela", formularDados);

        GregorianCalendar calendar = new GregorianCalendar();
        int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = calendar.get(GregorianCalendar.MONTH);
        int ano = calendar.get(GregorianCalendar.YEAR);

        context.put("dia", dia);
        context.put("mes", mes);
        context.put("ano", ano);
        context.put("total", orcamentoDTO.getValorTotal());

        context.put("vendedor", usuario.getNome());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);


        emailHtml.setHtml(writer.toString().replaceAll("ó","&oacute;").replaceAll("ç","&ccedil;").replaceAll("é","&eacute;").replaceAll("ã","&atilde;"));

        baos =  generateEmailPdf.generatePdfUsandoClasseDTO(orcamentoDTO,usuario);

        return baos.toByteArray();
    }

}
