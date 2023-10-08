package com.study.palette.test.albumArt;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtUpdateReqeustDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoCreateRequestDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.user.repository.UserRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Profile("local")
public class AlbumArtServiceTest {

    @Autowired
    private AlbumArtService albumArtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlbumArtRepository albumArtRepository;

    //    @Test
    @Transactional
    public void getAlbumArtList() {
        FindAlbumArtQuery query = new FindAlbumArtQuery();

        query.setName("test");
        query.setLimit(10);
        query.setPage(1);

        PaginationDto<AlbumArtResponseDto> albumArtServiceAlbumArts = albumArtService.getAlbumArts(query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));

        Assert.notNull(albumArtServiceAlbumArts);
    }

    @Test
//    @Transactional
    public void updateAlbumArt() {
        AlbumArtInfo albumArtInfo = albumArtRepository.findByServiceName("serviceName0");
        List<AlbumArtLicenseInfoWithIdDto> ss = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            ss.add(
                    new AlbumArtLicenseInfoWithIdDto(
                            albumArtInfo.getAlbumArtLicenseInfo().get(j).getId().toString(),
                            10,
                            1000,
                            "update 라이센스" + j,
                            3,
                            null,
                            3,
                            true,
                            true,
                            true,
                            true
                    ));
        }

        albumArtService.updateAlbumArt(albumArtInfo.getId().toString(), new AlbumArtUpdateReqeustDto(
                "updateServiceName",
                "테스트업데이트",
                "updateEditInfo",
                ss
        ));

        AlbumArtInfo aa = albumArtRepository.findByServiceName("updateServiceName");

        Assert.notNull(aa);

        System.out.println(aa.getServiceExplain());
        System.out.println(aa.getAlbumArtLicenseInfo().get(0).getServedFile());

    }
}
