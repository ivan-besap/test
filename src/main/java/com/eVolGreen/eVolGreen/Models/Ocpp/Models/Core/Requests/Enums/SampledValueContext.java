package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SampledValueContext {

    @JsonProperty("Interruption.Begin")
    Interruption_Begin,
    @JsonProperty("Interruption.End")
    Interruption_End,
    @JsonProperty("Sample.Clock")
    Sample_Clock,
    @JsonProperty("Sample.Periodic")
    Sample_Periodic,
    @JsonProperty("Transaction.Begin")
    Transaction_Begin,
    @JsonProperty("Transaction.End")
    Transaction_End,
    Trigger,
    Other

}
