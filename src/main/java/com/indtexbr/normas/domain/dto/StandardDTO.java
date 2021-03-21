package com.indtexbr.normas.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.indtexbr.normas.domain.dto.enums.Sector;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
@Builder
public class StandardDTO {

    @ApiModelProperty(example = "ABNT01")
    @NotNull
    private String id;

    @NotNull
    private Sector sector;

    @ApiModelProperty(example = "Lorem ipsum dolor sit amet")
    @NotNull
    private String title;

    @ApiModelProperty(example = "15/02/2021", readOnly = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date createdAt;

    @ApiModelProperty(example = "ABNT", readOnly = true)
    private String owner;

}
