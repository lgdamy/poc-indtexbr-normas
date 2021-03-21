package com.indtexbr.normas.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * @author lgdamy@raiadrogasil.com on 07/08/2020
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ResultPage<T> {
    private List<T> content;

    @ApiModelProperty(example = "50")
    private long totalElements;

    @ApiModelProperty(example = "1")
    private int page;

    @ApiModelProperty(example = "10")
    private int pageSize;

    public ResultPage(int pageSize) {
        super();
        this.page = 1;
        this.totalElements = 0L;
        this.content = Collections.emptyList();
        this.pageSize = pageSize;
    }

}
