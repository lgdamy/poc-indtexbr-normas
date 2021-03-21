package com.indtexbr.normas.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.indtexbr.normas.domain.dto.enums.Sector;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author lgdamy@raiadrogasil.com on 13/03/2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardFetchStrategyDTO {

    @ApiModelProperty(example = "24", readOnly = true)
    private Integer id;

    @ApiModelProperty(example = "Busca das normas T\u00eaxteis da ABNT")
    @NotNull
    private String title;

    @ApiModelProperty(example = "https://abnt.com.br/feed/textil")
    @NotNull
    private String searchUrl;

    @ApiModelProperty(example = "codigo")
    @NotNull
    private String idNode;

    @ApiModelProperty(example = "dados.descricao")
    @NotNull
    private String titleNode;

    @ApiModelProperty(example = "dados.url")
    @NotNull
    private String urlNode;

    @Pattern(regexp = "^[1-9]\\d?[mhDWM]$")
    private String fetchPeriodicity;


}
