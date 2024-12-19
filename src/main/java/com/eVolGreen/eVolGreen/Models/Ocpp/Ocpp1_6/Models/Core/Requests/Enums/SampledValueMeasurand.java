package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SampledValueMeasurand {

    @JsonProperty("Energy.Active.Export.Register")
    Energy_Active_Export_Register,
    @JsonProperty("Energy.Active.Import.Register")
    Energy_Active_Import_Register,
    @JsonProperty("Energy.Reactive.Export.Register")
    Energy_Reactive_Export_Register,
    @JsonProperty("Energy.Reactive.Import.Register")
    Energy_Reactive_Import_Register,
    @JsonProperty("Energy.Active.Export.Interval")
    Energy_Active_Export_Interval,
    @JsonProperty("Energy.Active.Import.Interval")
    Energy_Active_Import_Interval,
    @JsonProperty("Energy.Reactive.Export.Interval")
    Energy_Reactive_Export_Interval,
    @JsonProperty("Energy.Reactive.Import.Interval")
    Energy_Reactive_Import_Interval,
    @JsonProperty("Power.Active.Export")
    Power_Active_Export,
    @JsonProperty("Power.Active.Import")
    Power_Active_Import,
    @JsonProperty("Power.Offered")
    Power_Offered,
    @JsonProperty("Power.Reactive.Export")
    Power_Reactive_Export,
    @JsonProperty("Power.Reactive.Import")
    Power_Reactive_Import,
    @JsonProperty("Current.Export")
    Power_Factor,
    @JsonProperty("Current.Import")
    Current_Import,
    @JsonProperty("Current.Offered")
    Current_Export,
    @JsonProperty("Voltage")
    Current_Offered,
    Voltage,
    Frequency,
    Temperature,
    SoC,
    RPM

}
