package com.example.squad03.dto;

import com.example.squad03.enums.StatusContrato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Dados de retorno de um Contrato")
public class ContratoResponseDTO {

    @Schema(description = "ID do contrato", example = "1")
    private Long idContrato;

    @Schema(description = "Número do contrato", example = "CT-2025-0001")
    private String numeroContrato;

    @Schema(description = "Descrição/resumo do contrato", example = "Prestação de serviços de TI")
    private String descricao;

    @Schema(description = "Data de início do contrato", example = "2025-01-01")
    private LocalDate dataInicio;

    @Schema(description = "Data de término do contrato", example = "2025-12-31")
    private LocalDate dataFim;

    @Schema(description = "Termos de pagamento", example = "30 dias após entrega")
    private String termosDePagamento;

    @Schema(description = "Valor total do contrato", example = "100000.00")
    private BigDecimal valorContrato;

    @Schema(description = "Valor já pago", example = "25000.00")
    private BigDecimal valorTotalPago;

    @Schema(description = "Saldo pendente", example = "75000.00")
    private BigDecimal valorTotalPendente;

    @Schema(description = "Renovação automática?", example = "false")
    private Boolean autoRenovacao;

    @Schema(description = "Dias de antecedência para cancelamento", example = "30")
    private Integer diasParaCancelamento;

    @Schema(description = "Motivo de cancelamento (se houver)", example = "Rescisão amigável")
    private String motivoCancelamento;

    @Schema(description = "Data de criação", example = "2025-01-01T12:00:00")
    private LocalDateTime criadoEm;

    @Schema(description = "Data da última atualização", example = "2025-05-28T10:15:30")
    private LocalDateTime atualizadoEm;

    @Schema(description = "Usuário que criou", example = "admin")
    private String criadoPor;

    @Schema(description = "Usuário que atualizou por último", example = "filipe.brisio")
    private String atualizadoPor;

    @Schema(description = "Status do contrato", example = "ATIVO", allowableValues = {"ATIVO", "INATIVO", "ENCERRADO"})
    private StatusContrato statusContrato;

    @Schema(description = "Tipo do contrato", example = "Serviços")
    private String tipoContrato;

    @Schema(description = "Tags/keywords CSV", example = "TI,Terceirização")
    private String tags;

    @Schema(description = "URL do documento", example = "https://bucket.s3.amazonaws.com/contratos/ct-2025-0001.pdf")
    private String documentUrl;

    @Schema(description = "Empresa associada")
    private EmpresaResponseDTO empresa;

    @Schema(description = "Responsável pelo contrato")
    private ColaboradorResponseDTO responsavel;

    @Schema(description = "Representante associado")
    private RepresentanteResponseDTO representante;
}
