package com.study.palette.test.albumArt;

import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.users.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Profile("local")
public class AlbumArtServiceTest {

    @Autowired
    private AlbumArtService albumArtService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AlbumArtRepository albumArtRepository;

    //    @Test
    @Transactional
    public void getAlbumArtList() {
        FindAlbumArtQuery query = new FindAlbumArtQuery();

//        query.setName("test");
//        query.setLimit(10);
//        query.setPage(1);
//
//        PaginationDto<AlbumArtResponseDto> albumArtServiceAlbumArts = albumArtService.getAlbumArts(query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
//
//        Assert.notNull(albumArtServiceAlbumArts);
    }

    @Test
//    @Transactional
    public void updateAlbumArt() {
        /*AlbumArtInfo albumArtInfo = albumArtRepository.findByServiceName("serviceName0");
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
                ss,
                ), null);

        AlbumArtInfo aa = albumArtRepository.findByServiceName("updateServiceName");

        Assert.notNull(aa);

        System.out.println(aa.getServiceExplain());
        System.out.println(aa.getAlbumArtLicenseInfo().get(0).getServedFile());*/

    }
}
