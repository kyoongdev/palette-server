package com.study.palette.module.recording.dto.query;

import com.querydsl.core.types.OrderSpecifier;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.exception.CustomException;
import com.study.palette.module.albumArt.exception.AlbumArtErrorCode;
import com.study.palette.module.serviceProgress.entity.QServiceProgressInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindRecordingQuery extends PageDto {
    private String saleType;

    /*
    인기순 = 매출액 순(매출액이 같은 경우 판매량 순)
      → 매출액은 ‘작업 완료’(페이지명 :
           chat-seller-작업완료 전송_클릭)
           까지 완료된거래에 한해 선정

    판매량 순 = 판매량이 가장 많은 순서(판매량이 같은 경우 매출액 순)

    평점 순 = 서비스 후기 10개 이상인 서비스 중 서비스후기 평점이 가장 좋은 순서(평점이 같은 경우 매출액 순 다음 판매량 순)

    신규등록 순 = 판매글 등록이 완료된 최신순
*/
    @Schema(description = "정렬", defaultValue = "NEW", type = "string", allowableValues = {"NEW", "POPULAR", "RECOMMEND", "SCORE"})
    private CustomSort sort;

    public OrderSpecifier<?>[] getSort() {
        QServiceProgressInfo q = QServiceProgressInfo.serviceProgressInfo;

        if (this.sort == null) {
            throw new CustomException(AlbumArtErrorCode.ALBUM_ART_NOT_SORT);
        }

        if (this.sort == CustomSort.POPULAR) {
            return new OrderSpecifier[]{q.price.sum().desc()};
        } else if (this.sort == CustomSort.RECOMMEND) {
            return new OrderSpecifier[]{q.id.count().desc(), q.price.sum().desc()};
        } else if (this.sort == CustomSort.SCORE) {
//            return new OrderSpecifier[]{}; TODO review 구현후 테스트
            return null;
        } else { // 신규등록
            return new OrderSpecifier[]{q.createdAt.desc()};
        }
    }
}
