package com.indtexbr.normas.domain.dto.enums;

import com.indtexbr.normas.domain.dto.ResultPage;
import com.indtexbr.normas.domain.dto.StandardDTO;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author lgdamy@raiadrogasil.com on 20/03/2021
 */
@Getter
public enum MockStandard {

    ABNT_9050(StandardDTO.builder().id("ABNT9050").createdAt(randomDate()).owner("ABNT").sector(Sector.JURIDICO).title("Inclusao de pessoas com deficiencia").build(),"http://www.mpf.mp.br/atuacao-tematica/pfdc/institucional/grupos-de-trabalho/inclusao-pessoas-deficiencia/atuacao/legislacao/docs/norma-abnt-NBR-9050.pdf"),
    LOREM_IPSUM(StandardDTO.builder().id("LIPS001").createdAt(randomDate()).owner("Lorem Ipsum").sector(Sector.COMPLIANCE).title("Lorem Ipsum").build(),"https://www.soundczech.cz/temp/lorem-ipsum.pdf"),
    SAMPLE_PDF(StandardDTO.builder().id("SAMP010").createdAt(randomDate()).owner("SAMPLE").sector(Sector.AMBIENTAL).title("Sample Standard").build(),"http://www.africau.edu/images/default/sample.pdf"),
    TEXTIL(StandardDTO.builder().id("ABNT1280").createdAt(randomDate()).owner("ABNT").sector(Sector.TEXTIL).title("Padroes da Industria Textil").build(),"http://abnt.org.br/paginampe/biblioteca/files/upload/anexos/pdf/d2f9da2dc7058b510ebf8923e474a88d.pdf"),
    LAVAGEM(StandardDTO.builder().id("USPTEX7008").createdAt(randomDate()).owner("USP").sector(Sector.TEXTIL).title("Padroes de Lavagem de Tecidos").build(),"https://www.teses.usp.br/teses/disponiveis/100/100133/tde-19012018-165818/publico/2018_Versao_Corrogida.pdf"),
    JEANS(StandardDTO.builder().id("SEBR8991").createdAt(randomDate()).owner("SEBRAE").sector(Sector.TEXTIL).title("Padr\u00e3o de Confec\u00e7\u00e3o de Jeans").build(),"http://www.bibliotecas.sebrae.com.br/chronus/ARQUIVOS_CHRONUS/bds/bds.nsf/2d85ff3222c74b561e6b42872abfe35c/$File/5296.pdf"),
    FIBRAS(StandardDTO.builder().id("IFSC7791").createdAt(randomDate()).owner("IFSC").sector(Sector.TEXTIL).title("Manual de Fibras").build(),"https://wiki.ifsc.edu.br/mediawiki/images/8/88/Apostila_fibras.pdf"),
    SEGURANCA(StandardDTO.builder().id("ABNT8861").createdAt(randomDate()).owner("ABNT").sector(Sector.SEGURANCA_DO_TRABALHO).title("Guia de seguranca do trabalho").build(),"https://www.abvtex.org.br/wp-content/uploads/2020/01/Cartilha-Seguran%C3%A7a-Qu%C3%ADmica-em-T%C3%AAxteis-Revis%C3%A3o-Dezembro-2019.pdf"),
    TECNICOS(StandardDTO.builder().id("INDTEX007").createdAt(randomDate()).owner("INDTEXBR").sector(Sector.ETICA).title("T\u00e9cnica saud\u00e1vel de contrata\u00e7\u00e3o").build(),"http://www.abint.org.br/pdf/Manual_ttecnicos.pdf"),
    FINANCEIRO(StandardDTO.builder().id("INDTEX020").createdAt(randomDate()).owner("INDTEXBR").sector(Sector.JURIDICO).title("Formul\u00e1rio de crescimento da ind\u00fastria").build(),"https://www.bnb.gov.br/documents/88765/89729/iis_ano8_n03_2014_textil.pdf");


    MockStandard(StandardDTO standard, String uriString) {
        this.uri = URI.create(uriString);
        this.standard = standard;
    }
    private URI uri;
    private StandardDTO standard;

    private static Date randomDate() {
        return Date.from(LocalDateTime.now().minusDays(new Random().nextInt(365)).toInstant(ZoneOffset.UTC));
    }

    public static ResultPage<StandardDTO> busca(Date from,
                                                Date to,
                                                int page,
                                                int size,
                                                Sector sector,
                                                String owner) {
        List<StandardDTO> standards = EnumSet.allOf(MockStandard.class)
                .stream()
                .map(MockStandard::getStandard)
                .filter(standard -> from == null || standard.getCreatedAt().after(from))
                .filter(standard -> to == null || standard.getCreatedAt().before(to))
                .filter(standard -> sector == null || sector.equals(standard.getSector()))
                .filter(standard -> owner == null || standard.getOwner().toUpperCase().contains(owner.toUpperCase()))
                .collect(Collectors.toList());

        return new ResultPage<>(
                standards.stream().skip(size * page).limit(size).collect(Collectors.toList()),
                standards.size(),
                page,
                size);
    }

    public static Optional<MockStandard> busca(String id) {
        return EnumSet.allOf(MockStandard.class).stream().filter(s -> s.getStandard().getId().equalsIgnoreCase(id)).findFirst();
    }
}
