package com.restspotfinder.route.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum OptionCode {
    fast("trafast", "실시간 빠른길"),
    optimal("traoptimal", "실시간 최적"),
    avoidtoll("traavoidtoll", "무료 우선"),
    comfort("tracomfort", "실시간 편한길"),
    avoidcaronly("traavoidcaronly", "자동차 전용 도로 회피 우선");

    private final String value;
    private final String desc;

    OptionCode(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    // @RequestParam ENUM Parsing
    @JsonCreator
    public static OptionCode from(String s) {
        return OptionCode.valueOf(s);
    }
}
