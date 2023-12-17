package com.study.palette.common.enums.recording;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CityCode {

  //    서울
  JONGNO(1, 1, "종로구"),
  JUNG(1, 2, "중구"),
  YONGSAN(1, 3, "용산구"),
  SEONGDONG(1, 4, "성동구"),
  GWANGJIN(1, 5, "광진구"),
  DONGDAEMUN(1, 6, "동대문구"),
  JUNGNANG(1, 7, "중랑구"),
  SEONGBUK(1, 8, "성북구"),
  GANGBUK(1, 9, "강북구"),
  DOBONG(1, 10, "도봉구"),
  NOWON(1, 11, "노원구"),
  EUNPYEONG(1, 12, "은평구"),
  SEODAEMUN(1, 13, "서대문구"),
  MAPO(1, 14, "마포구"),
  YANGCHEON(1, 15, "양천구"),
  GANGSEO(1, 16, "강서구"),
  GURO(1, 17, "구로구"),
  GEUMCHEON(1, 18, "금천구"),
  YEONGDEUNGPO(1, 19, "영등포구"),
  DONGJAK(1, 20, "동작구"),
  GWANAK(1, 21, "관악구"),
  SEOCHO(1, 22, "서초구"),
  GANGNAM(1, 23, "강남구"),
  SONGPA(1, 24, "송파구"),
  GANGDONG(1, 25, "강동구"),

  //    경기
  SUWON(2, 1, "수원시"),
  GOYANG(2, 2, "고양시"),
  YONGIN(2, 3, "용인시"),
  SEONGNAM(2, 4, "성남시"),
  BUCHEON(2, 5, "부천시"),
  HWASEONG(2, 6, "화성시"),
  ANSAN(2, 7, "안산시"),
  NAMYANGJU(2, 8, "남양주시"),
  ANYANG(2, 9, "안양시"),
  PYEONGTAEK(2, 10, "평택시"),
  SIHEUNG(2, 11, "시흥시"),
  PAJU(2, 12, "파주시"),
  UIJEONGBU(2, 13, "의정부시"),
  GIMPO(2, 14, "김포시"),
  GWANGJU(2, 15, "광주시"),
  GWANGMYEONG(2, 16, "광명시"),
  GUNPO(2, 17, "군포시"),
  HANAM(2, 18, "하남시"),
  OSAN(2, 19, "오산시"),
  YANGJU(2, 20, "양주시"),
  ICHON(2, 21, "이천시"),
  GURI(2, 22, "구리시"),
  ANSEONG(2, 23, "안성시"),
  POCHEON(2, 24, "포천시"),
  UIWANG(2, 25, "의왕시"),
  YANGPYEONG(2, 26, "양평군"),
  YEOJU(2, 27, "여주시"),
  DONGDUCHEON(2, 28, "동두천시"),
  GAPYEONG(2, 29, "가평군"),
  GWAECHEON(2, 30, "과천시"),
  YEONCHEON(2, 31, "연천군"),

  //    인천
  INCHEON_JUNG(3, 1, "중구"),
  INCHEON_DONG(3, 2, "동구"),
  MICHUHOL(3, 3, "미추홀구"),
  YEONSU(3, 4, "연수구"),
  NAMDONG(3, 5, "남동구"),
  BUPYEONG(3, 6, "부평구"),
  GYEHYANG(3, 7, "계양구"),
  INCHEON_SEO(3, 8, "서구"),
  GANGHWA(3, 9, "강화군"),
  ONGJIN(3, 10, "옹진군"),

  //    부산
  BUSAN_JUNG(4, 1, "중구"),
  BUSAN_SEO(4, 2, "서구"),
  BUSAN_DONG(4, 3, "동구"),
  YEONGDO(4, 4, "영도구"),
  JIN(4, 5, "진구"),
  DONGRAE(4, 6, "동래구"),
  BUSAN_NAM(4, 7, "남구"),
  BUSAN_BUK(4, 8, "북구"),
  HAEUNDAE(4, 9, "해운대구"),
  SAHA(4, 10, "사하구"),
  GEUMJEONG(4, 11, "금정구"),
  BUSAN_GANGSEO(4, 12, "강서구"),
  YEONJE(4, 13, "연제구"),
  SUYEONG(4, 14, "수영구"),
  SASANG(4, 15, "사상구"),
  GIJANG(4, 16, "기장군"),

  //    광주
  GWANGJU_DONG(5, 1, "동구"),
  GWANGJU_SEO(5, 2, "서구"),
  GWANGJU_NAM(5, 3, "남구"),
  GWANGJU_BUK(5, 4, "북구"),
  GWANGSAN(5, 5, "광산구"),

  //    대전
  DAEJEON_DONG(6, 1, "동구"),
  DAEJEON_JUNG(6, 2, "중구"),
  DAEJEON_SEO(6, 3, "서구"),
  YUSEONG(6, 4, "유성구"),
  DAEDEOK(6, 5, "대덕구"),

  //    대구
  DAEGU_JUNG(7, 1, "중구"),
  DAEGU_DONG(7, 2, "동구"),
  DAEGU_SEO(7, 3, "서구"),
  DAEGU_NAM(7, 4, "남구"),
  DAEGU_BUK(7, 5, "북구"),
  SUSUNG(7, 6, "수성구"),
  DALSEO(7, 7, "달서구"),
  DALSEONG(7, 8, "달성군"),
  GUNWI(7, 9, "군위군"),

  //    울산
  ULSAN_JUNG(8, 1, "중구"),
  ULSAN_NAM(8, 2, "남구"),
  ULSAN_DONG(8, 3, "동구"),
  ULSAN_BUK(8, 4, "북구"),
  ULJU(8, 5, "울주군"),

  //    전북
  JU(9, 1, "주시"),
  GUNSAN(9, 2, "군산시"),
  IKSAN(9, 3, "익산시"),
  JEONGEUP(9, 4, "정읍시"),
  NAMWON(9, 5, "남원시"),
  GIMJE(9, 6, "김제시"),
  WANJU(9, 7, "완주군"),
  JINAN(9, 8, "진안군"),
  MUJU(9, 9, "무주군"),
  JANGSU(9, 10, "장수군"),
  IMSIL(9, 11, "임실군"),
  SUNCHANG(9, 12, "순창군"),
  GOCHEON(9, 13, "고창군"),
  BUAN(9, 14, "부안군"),

  //    전남
  MOKPO(10, 1, "목포시"),
  YEOSU(10, 2, "여수시"),
  SUNCHEON(10, 3, "순천시"),
  NAJU(10, 4, "나주시"),
  GWANGYANG(10, 5, "광양시"),
  DAMYANG(10, 6, "담양군"),
  GOKSEONG(10, 7, "곡성군"),
  GURYE(10, 8, "구례군"),
  GOHEUNG(10, 9, "고흥군"),
  BOSEONG(10, 10, "보성군"),
  HWASUN(10, 11, "화순군"),
  JANGHEUNG(10, 12, "장흥군"),
  GANGJIN(10, 13, "강진군"),
  HAENAM(10, 14, "해남군"),
  YEONGAM(10, 15, "영암군"),
  MUAN(10, 16, "무안군"),
  HAMPYEONG(10, 17, "함평군"),
  YEONGGWANG(10, 18, "영광군"),
  JANGSEONG(10, 19, "장성군"),
  WANDO(10, 20, "완도군"),
  JINDO(10, 21, "진도군"),
  SINAN(10, 22, "신안군"),
  //    충북
  CHEONGJU(11, 1, "청주시"),
  CHUNGJUJU(11, 2, "충주시"),
  JECHEON(11, 3, "제천시"),
  BOEUN(11, 4, "보은군"),
  OKCHEON(11, 5, "옥천군"),
  YEONGDONG(11, 6, "영동군"),
  JEUNGPYEONG(11, 7, "증평군"),
  JINCHEON(11, 8, "진천군"),
  GOESAN(11, 9, "괴산군"),
  EUMSEONG(11, 10, "음성군"),
  DANYANG(11, 11, "단양군"),

  //    충남
  CHEONAN(12, 7, "천안시"),
  GONGJU(12, 8, "공주시"),
  BORYEONG(12, 9, "보령시"),
  ASAN(12, 10, "아산시"),
  SEOSAN(12, 11, "서산시"),
  NONSAN(12, 12, "논산시"),
  GYERYONG(12, 13, "계룡시"),
  DANGJIN(12, 14, "당진시"),
  GUMSANG(12, 15, "금산군"),
  BUWEO(12, 16, "부여군"),
  SEOCHEON(12, 17, "서천군"),
  CHEONGYANG(12, 18, "청양군"),
  HONGSEONG(12, 19, "홍성군"),
  YESAN(12, 20, "예산군"),
  TAAN(12, 21, "태안군"),

  //    경북
  POHANG(13, 3, "포항시"),
  GYEONGJU(13, 4, "경주시"),
  KIMCHEON(13, 5, "김천시"),
  ANDONG(13, 6, "안동시"),
  GUMI(13, 7, "구미시"),
  YEONGJU(13, 8, "영주시"),
  YEONGCHEON(13, 9, "영천시"),
  SANGJU(13, 10, "상주시"),
  MUNGYES(13, 11, "문경시"),
  GYEONGSAN(13, 12, "경산시"),
  UISEONG(13, 13, "의성군"),
  CHEONGSONG(13, 14, "청송군"),
  YEONGYANG(13, 15, "영양군"),
  YEONGDEOK(13, 16, "영덕군"),
  CHEONDO(13, 17, "청도군"),
  GORYEONG(13, 18, "고령군"),
  SUNGJU(13, 19, "성주군"),
  CHILGOK(13, 20, "칠곡군"),
  YECHEON(13, 21, "예천군"),
  BONGHWA(13, 22, "봉화군"),
  ULJIN(13, 23, "울진군"),
  ULLEUNG(13, 24, "울릉군"),

  //    경남
  CHANGWON(14, 3, "창원시"),
  JINJU(14, 4, "진주시"),
  TONGYEONG(14, 5, "통영시"),
  SACHUN(14, 6, "사천시"),
  GIMHAE(14, 7, "김해시"),
  MILYANG(14, 8, "밀양시"),
  GEOJE(14, 9, "거제시"),
  YANGSAN(14, 10, "양산시"),
  UREUNG(14, 11, "의령군"),
  HAMAN(14, 12, "함안군"),
  CHANGNYEONG(14, 13, "창녕군"),
  GOSEONG(14, 14, "고성군"),
  NAMHAE(14, 15, "남해군"),
  HADONG(14, 16, "하동군"),
  SANCHEONG(14, 17, "산청군"),
  HAMYANG(14, 18, "함양군"),
  GEOCHANG(14, 19, "거창군"),
  HAPCHEON(14, 20, "합천군"),

  //    강원
  CHUNCHEON(15, 3, "춘천시"),
  WONJU(15, 4, "원주시"),
  GANGNEUNG(15, 5, "강릉시"),
  DONGHAE(15, 6, "동해시"),
  TAEBAEK(15, 7, "태백시"),
  SOKCHO(15, 8, "속초시"),
  SAMCHEOK(15, 9, "삼척시"),
  HONGCHEON(15, 10, "홍천군"),
  HOENGSEONG(15, 11, "횡성군"),
  YEONGWOL(15, 12, "영월군"),
  PYEONGCHANG(15, 13, "평창군"),
  JEONGSEON(15, 14, "정선군"),
  CHEORWON(15, 15, "철원군"),
  HWACHEON(15, 16, "화천군"),
  YANGGU(15, 17, "양구군"),
  INJE(15, 18, "인제군"),
  GANGWON_GOSEONG(15, 19, "고성군"),
  YANGYANG(15, 20, "양양군"),

//    세종

  //    제주
  JEJU(17, 1, "제주시"),
  SEOGWIPO(17, 2, "서귀포시");

  private int groupCode;
  private int code;
  private String name;

  public static CityCode findCityCode(int groupCode, int code) {
    for (CityCode cityCode : CityCode.values()) {
      if (cityCode.getGroupCode() == groupCode && cityCode.getCode() == code) {
        return cityCode;
      }
    }
    throw new RuntimeException("groupCode와 code가 일치하는 CityCode가 없습니다.");
  }

  //그룹 코드가 일치하는 도시 코드 목록 조회
  public static List<CityCode> getCityCodes(int groupCode) {
    return Arrays.stream(CityCode.values())
        .filter(cityCode -> cityCode.getGroupCode() == groupCode)
        .collect(Collectors.toList());
  }


}
