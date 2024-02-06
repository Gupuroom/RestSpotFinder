package com.restspotfinder.restarea.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;


@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restAreaId;
    private String name; // "휴게소명"
    private String routeName; // "도로노선명"
    private String routeDirection; // "도로노선방향"
    @JsonIgnore
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point position;
    private String roadType; // "도로종류"
    private String roadNumber; // "도로노선번호"
    private double latitude; // "위도"
    private double longitude; // "경도"
    private String type; // "휴게소종류"
    private String operatingStartTime; // "휴게소운영시작시각"
    private String operatingEndTime; // "휴게소운영종료시각"
    private int siteArea; // "도로점용면적"
    private int parkingSpaceCount; // "주차면수"
    private Boolean isMaintenanceAvailable; // "경정비가능여부"
    private Boolean hasGasStation; // "주유소유무"
    private Boolean hasLpgChargingStation; // "LPG충전소유무"
    private Boolean hasElectricChargingStation; // "전기차충전소유무"
    private Boolean isBusTransferAvailable; // "버스환승가능여부"
    private Boolean isRestArea; // "쉼터유무"
    private Boolean hasRestroom; // "화장실유무"
    private Boolean hasPharmacy; // "약국유무"
    private Boolean hasNursingRoom; // "수유실유무"
    private Boolean hasStore; // "매점유무"
    private Boolean hasRestaurant; // "음식점유무"
    private String otherFacilities; // "기타편의시설"
    private String representativeFood; // "휴게소대표음식명"
    private String phoneNumber; // "휴게소전화번호"
    private String dataBaseDate; // "데이터기준일자"
    private String providerCode; // "제공기관코드"
    private String providerName; // "제공기관명"

    public static RestArea from(JsonNode node) {
        GeometryFactory geometryFactory = new GeometryFactory();
        double xValue = node.get("경도").asDouble(0);
        double yValue = node.get("위도").asDouble(0);
        Point position = geometryFactory.createPoint(new Coordinate(xValue, yValue));
        System.out.println("position = " + position);
        return RestArea.builder()
                .name(node.get("휴게소명").asText("")) // 기본값으로 "" 사용
                .routeName(node.get("도로노선명").asText(""))
                .routeDirection(node.get("도로노선방향").asText(""))
                .position(position)
                .roadType(node.get("도로종류").asText(""))
                .roadNumber(node.get("도로노선번호").asText(""))
                .latitude(node.get("위도").asDouble(0)) // 기본값으로 0 사용
                .longitude(node.get("경도").asDouble(0))
                .type(node.get("휴게소종류").asText(""))
                .operatingStartTime(node.get("휴게소운영시작시각").asText(""))
                .operatingEndTime(node.get("휴게소운영종료시각").asText(""))
                .siteArea(node.get("도로점용면적").asInt(0))
                .parkingSpaceCount(node.get("주차면수").asInt(0))
                .isMaintenanceAvailable("Y".equals(node.get("경정비가능여부").asText(null)))
                .hasGasStation("Y".equals(node.get("주유소유무").asText(null)))
                .hasLpgChargingStation("Y".equals(node.get("LPG충전소유무").asText(null)))
                .hasElectricChargingStation("Y".equals(node.get("전기차충전소유무").asText(null)))
                .isBusTransferAvailable("Y".equals(node.get("버스환승가능여부").asText(null)))
                .isRestArea("Y".equals(node.get("쉼터유무").asText(null)))
                .hasRestroom("Y".equals(node.get("화장실유무").asText(null)))
                .hasPharmacy("Y".equals(node.get("약국유무").asText(null)))
                .hasNursingRoom("Y".equals(node.get("수유실유무").asText(null)))
                .hasStore("Y".equals(node.get("매점유무").asText(null)))
                .hasRestaurant("Y".equals(node.get("음식점유무").asText(null)))
                .otherFacilities(node.get("기타편의시설").asText(""))
                .representativeFood(node.get("휴게소대표음식명").asText(""))
                .phoneNumber(node.get("휴게소전화번호").asText(""))
                .dataBaseDate(node.get("데이터기준일자").asText(""))
                .providerCode(node.get("제공기관코드").asText(""))
                .providerName(node.get("제공기관명").asText(""))
                .build();
    }

    //            {
//                "휴게소명" : "덕유산(대전)",
//                    "도로종류" : "고속국도",
//                    "도로노선번호" : "35",
//                    "도로노선명" : "대전통영선",
//                    "도로노선방향" : "상행",
//                    "위도" : "35.816481",
//                    "경도" : "127.646149",
//                    "휴게소종류" : "일반휴게소",
//                    "휴게소운영시작시각" : "00:00",
//                    "휴게소운영종료시각" : "23:59",
//                    "도로점용면적" : "43941",
//                    "주차면수" : "191",
//                    "경정비가능여부" : "N",
//                    "주유소유무" : "Y",
//                    "LPG충전소유무" : "Y",
//                    "전기차충전소유무" : "Y",
//                    "버스환승가능여부" : "N",
//                    "쉼터유무" : "N",
//                    "화장실유무" : "Y",
//                    "약국유무" : "N",
//                    "수유실유무" : "Y",
//                    "매점유무" : "Y",
//                    "음식점유무" : "Y",
//                    "기타편의시설" : "",
//                    "휴게소대표음식명" : "흑돼지김치찌개",
//                    "휴게소전화번호" : "063-353-4200",
//                    "데이터기준일자" : "2023-08-24",
//                    "제공기관코드" : "B500004",
//                    "제공기관명" : "한국도로공사"
//            }
}
