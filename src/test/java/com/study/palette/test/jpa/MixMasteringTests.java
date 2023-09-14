package com.study.palette.test.jpa;

import com.study.palette.test.jpa.crud.model.MixMasteringInfo;
import com.study.palette.test.jpa.crud.repository.MixMasteringRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("local")
public class MixMasteringTests {


    @Autowired
    private MixMasteringRepository mixMasteringRepository;

    @Test
    void save() {

        //given
        MixMasteringInfo params = MixMasteringInfo.builder()
                .id("280a8a2d-b27f1")
                .serviceName("테스트")
                .artistId("손흥민")
                .serviceExplain("믹스마스터링테스트22")
                .editInfo("수정정보테스트")
                .serviceStatus(true)
                .cratedAt(new Date())
                .userId("pallete@pallete.com")
                .build();

        MixMasteringInfo mixMateringInfo = mixMasteringRepository.save(params);

        //when
        Optional<MixMasteringInfo> foundMixMasteringInfo = mixMasteringRepository.findById(params.getId());

        //then
        assertThat(foundMixMasteringInfo.isPresent()).isTrue();
    }

    @Test
    void find() {

        //given
        String id = "280a8a2d-b27f1";

        //when
        Optional<MixMasteringInfo> mixMateringInfo = mixMasteringRepository.findById(id);

        //then
        assertThat(mixMateringInfo.get().getId().equals(id));

    }

    @Test
    void update() {

        //given
        MixMasteringInfo params = MixMasteringInfo.builder()
                .id("280a8a2d-b27f1")
                .serviceName("테스트")
                .artistId("손흥민3")
                .serviceExplain("믹스마스터링테스트22")
                .editInfo("수정정보테스트")
                .serviceStatus(true)
                .cratedAt(new Date())
                .userId("pallete@pallete2.com")
                .build();

        Optional mixMasteringDTO = mixMasteringRepository.findById(params.getId());

        //when
        MixMasteringInfo save = mixMasteringRepository.save(params);

        //then
        assertThat(save.getUserId().equals(params.getUserId()));


    }

    @Test
    void delete() {

        //given
        String param = "280a8a2d-b27f1";

        //when
        mixMasteringRepository.deleteById(param);

        //then
        assertTrue(mixMasteringRepository.findById("param").isEmpty());

    }

    @Test
    void queryDslTest() {

        //given
        String id = "280a8a2d-b27f1";

        //when
        MixMasteringInfo mixMasteringInfo = mixMasteringRepository.findGenre(id);

        //then
        assertThat(mixMasteringInfo.getId().equals(id));


    }


}
