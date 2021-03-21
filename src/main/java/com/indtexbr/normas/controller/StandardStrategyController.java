package com.indtexbr.normas.controller;

import com.indtexbr.normas.domain.dto.ResultPage;
import com.indtexbr.normas.domain.dto.StandardFetchStrategyDTO;
import com.indtexbr.normas.exception.FuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Random;

/**
 * @author lgdamy@raiadrogasil.com on 14/03/2021
 */
@RestController
@Api(tags = "Estrat\u00e9gias de Busca")
@RequestMapping("/strategy/v1")
@Validated
public class StandardStrategyController {

    private static final String NAO_ENCONTRADO = "Nenhuma estrat\u00e9gia localizada";

    @ApiOperation("Gera uma norma nova")
    @PostMapping
    public ResponseEntity<Integer> gerarNova(@Valid @RequestBody StandardFetchStrategyDTO standard) {
        return ResponseEntity.ok(new Random().nextInt(10000));
    }

    @ApiOperation("Consulta uma lista de estrategias t\u00edtulo atribu\u00eddo, a resposta \u00e9 paginada")
    @GetMapping
    public ResultPage<StandardFetchStrategyDTO> buscar(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "owner", required = false) String owner) {
        return new ResultPage<>(size);
    }

    @ApiOperation(value = "Consulta os detalhes de uma estrat\u00e9gia pelo identificador")
    @GetMapping("{id}")
    public ResponseEntity<StandardFetchStrategyDTO> buscar(@ApiParam(example = "102") @PathVariable("id") Integer id) {
        throw new FuntimeException(NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Remove uma estrat\u00e9gia pelo identificador")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@ApiParam(example = "102") @PathVariable("id") Integer id) {
        throw new FuntimeException(NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Altera uma estrat\u00e9gia com o identificador")
    @PatchMapping("{id}")
    public ResponseEntity<Void> atualizar(@ApiParam(example = "102") @PathVariable("id") String id, @RequestBody StandardFetchStrategyDTO standard) {
        throw new FuntimeException(NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
    }
}
