package com.example.squad03.dto;

import com.example.squad03.enums.StatusContrato;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Dados para criação de um Contrato")
public class ContratoCreateDTO {

    @NotNull
    @Schema(description = "Número do contrato", example = "CT-2025-0001")
    private String numeroContrato;

    @NotNull
    @Schema(description = "Descrição/resumo do contrato", example = "Prestação de serviços de TI")
    private String descricao;

    @NotNull
    @Schema(description = "Data de início do contrato", example = "2025-01-01")
    private LocalDate dataInicio;

    @NotNull
    @Schema(description = "Data de término do contrato", example = "2025-12-31")
    private LocalDate dataFim;

    @Schema(description = "Termos de pagamento", example = "30 dias após entrega")
    private String termosDePagamento;

    @Schema(description = "Valor pago até o momento", example = "50000.00")
    private BigDecimal valorTotalPago;

    @NotNull
    @Schema(description = "Valor total do contrato", example = "100000.00")
    private BigDecimal valorContrato;

    @Schema(description = "Renovação automática?", example = "false")
    private Boolean autoRenovacao;

    @Schema(description = "Dias de antecedência para cancelamento", example = "30")
    private Integer diasParaCancelamento;

    @Schema(description = "Motivo de cancelamento (se houver)", example = "Rescisão amigável")
    private String motivoCancelamento;

    @NotNull
    @Schema(description = "Status do contrato", example = "ATIVO", allowableValues = {"ATIVO", "INATIVO", "ENCERRADO"})
    private StatusContrato statusContrato;

    @Schema(description = "Tipo do contrato", example = "Serviços")
    private String tipoContrato;

    @Schema(description = "Tags/keywords CSV", example = "TI,Terceirização")
    private String tags;

    @Schema(description = "URL do documento (PDF, S3, etc.)", example = "https://bucket.s3.amazonaws.com/contratos/ct-2025-0001.pdf")
    private String documentUrl;

    @NotNull
    @Schema(description = "ID da empresa")
    private Long empresaId;

    @NotNull
    @Schema(description = "ID do colaborador responsável")
    private Long responsavelId;

//    @NotNull
//    @Schema(description = "ID do representante")
//    private Long representanteId;
}
