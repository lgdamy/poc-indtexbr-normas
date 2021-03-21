package com.indtexbr.normas.controller;

import com.indtexbr.normas.domain.dto.StandardDTO;
import com.indtexbr.normas.domain.dto.ResultPage;
import com.indtexbr.normas.domain.dto.enums.MockStandard;
import com.indtexbr.normas.domain.dto.enums.Sector;
import com.indtexbr.normas.exception.FuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.net.URI;
import java.util.Date;

/**
 * @author lgdamy@raiadrogasil.com on 14/03/2021
 */
@RestController
@Api(tags = "Normas")
@RequestMapping("/standards/v1")
@Validated
public class StandardController {

    private static final String NAO_ENCONTRADO = "Nenhuma norma localizada";

    private final RestTemplate restTemplate = new RestTemplate();

    @ApiOperation("Gera uma norma nova")
    @PostMapping
    public ResponseEntity<String> gerarNova(@Valid @RequestBody StandardDTO standard) {
        return ResponseEntity.ok(standard.getId());
    }

    @ApiOperation("Consulta uma lista de normas pela data de cria\u00e7\u00e3o, setor ou fornecedor, a resposta \u00e9 paginada")
    @GetMapping
    public ResultPage<StandardDTO> buscar(
            @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "ddMMyyyy") Date from,
            @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "ddMMyyyy") Date to,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sector", required = false) Sector sector,
            @RequestParam(value = "owner", required = false) String owner
    ) {
        return MockStandard.busca(from, to, page, size, sector, owner);
    }

    @ApiOperation(value = "Consulta os detalhes de uma norma pelo identificador")
    @GetMapping("{id}")
    public ResponseEntity<StandardDTO> buscar(@ApiParam(example = "ABNT9050") @PathVariable("id") String id) {
        return ResponseEntity.ok(MockStandard.busca(id).map(MockStandard::getStandard).orElseThrow(() -> new FuntimeException(NAO_ENCONTRADO, HttpStatus.NOT_FOUND)));
    }

    @ApiOperation(value = "Remove uma norma pelo identificador")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@ApiParam(example = "ABNT9050") @PathVariable("id") String id) {
        buscaPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Altera uma norma com o identificador")
    @PatchMapping("{id}")
    public ResponseEntity<Void> atualizar(@ApiParam(example = "ABNT9050") @PathVariable("id") String id, @RequestBody StandardDTO standard) {
        buscaPorId(id);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation(value = "Realiza o download do documento de uma norma")
    @GetMapping(value = "{id}/download", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<byte[]> downloadDocumento(@ApiParam(example = "ABNT9050") @PathVariable("id") String id) {
        URI uri = MockStandard.busca(id).map(MockStandard::getUri).orElseThrow(() -> new FuntimeException(NAO_ENCONTRADO, HttpStatus.NOT_FOUND));
        try {
            byte[] pdf = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(null), byte[].class).getBody();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("content-disposition", String.format("attachment; filename=%s.pdf", id));
            return ResponseEntity.ok().headers(headers).body(pdf);
        } catch (Exception e) {
            throw new FuntimeException(String.format("Falha ao buscar documento: %s", e.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }

    private StandardDTO buscaPorId(String id) {
        return MockStandard.busca(id).map(MockStandard::getStandard)
                .orElseThrow(() -> new FuntimeException(NAO_ENCONTRADO, HttpStatus.NOT_FOUND));
    }
}
