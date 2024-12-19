package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SampledValuePhase {
    L1("L1"),
    L2("L2"),
    L3("L3"),
    N("N"),
    L1_N("L1-N"),
    L2_N("L2-N"),
    L3_N("L3-N"),
    L1_L2("L1-L2"),
    L2_L3("L2-L3"),
    L3_L1("L3-L1");

    private final String value;

    SampledValuePhase(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SampledValuePhase fromValue(String value) {
        for (SampledValuePhase phase : SampledValuePhase.values()) {
            if (phase.value.equals(value)) {
                return phase;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}
