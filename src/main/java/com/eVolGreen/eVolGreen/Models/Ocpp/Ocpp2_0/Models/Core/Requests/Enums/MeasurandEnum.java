package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum que representa el tipo de medición realizada (Measurand).
 * <p>
 * Indica el tipo de valor medido, como energía activa, reactiva, corriente, potencia, entre otros.
 * Por defecto, se utiliza "Energy.Active.Import.Register".
 */
public enum MeasurandEnum {

    /** Corriente exportada. */
    @SerializedName("Current.Export")
    CurrentExport,

    /** Corriente importada. */
    @SerializedName("Current.Import")
    CurrentImport,

    /** Corriente ofrecida. */
    @SerializedName("Current.Offered")
    CurrentOffered,

    /** Energía activa exportada, registrada. */
    @SerializedName("Energy.Active.Export.Register")
    EnergyActiveExportRegister,

    /** Energía activa importada, registrada (por defecto). */
    @SerializedName("Energy.Active.Import.Register")
    EnergyActiveImportRegister,

    /** Energía reactiva exportada, registrada. */
    @SerializedName("Energy.Reactive.Export.Register")
    EnergyReactiveExportRegister,

    /** Energía reactiva importada, registrada. */
    @SerializedName("Energy.Reactive.Import.Register")
    EnergyReactiveImportRegister,

    /** Energía activa exportada, por intervalo. */
    @SerializedName("Energy.Active.Export.Interval")
    EnergyActiveExportInterval,

    /** Energía activa importada, por intervalo. */
    @SerializedName("Energy.Active.Import.Interval")
    EnergyActiveImportInterval,

    /** Energía activa neta. */
    @SerializedName("Energy.Active.Net")
    EnergyActiveNet,

    /** Energía reactiva exportada, por intervalo. */
    @SerializedName("Energy.Reactive.Export.Interval")
    EnergyReactiveExportInterval,

    /** Energía reactiva importada, por intervalo. */
    @SerializedName("Energy.Reactive.Import.Interval")
    EnergyReactiveImportInterval,

    /** Energía reactiva neta. */
    @SerializedName("Energy.Reactive.Net")
    EnergyReactiveNet,

    /** Energía aparente neta. */
    @SerializedName("Energy.Apparent.Net")
    EnergyApparentNet,

    /** Energía aparente importada. */
    @SerializedName("Energy.Apparent.Import")
    EnergyApparentImport,

    /** Energía aparente exportada. */
    @SerializedName("Energy.Apparent.Export")
    EnergyApparentExport,

    /** Frecuencia medida. */
    Frequency,

    /** Potencia activa exportada. */
    @SerializedName("Power.Active.Export")
    PowerActiveExport,

    /** Potencia activa importada. */
    @SerializedName("Power.Active.Import")
    PowerActiveImport,

    /** Factor de potencia. */
    @SerializedName("Power.Factor")
    PowerFactor,

    /** Potencia ofrecida. */
    @SerializedName("Power.Offered")
    PowerOffered,

    /** Potencia reactiva exportada. */
    @SerializedName("Power.Reactive.Export")
    PowerReactiveExport,

    /** Potencia reactiva importada. */
    @SerializedName("Power.Reactive.Import")
    PowerReactiveImport,

    /** Estado de carga (State of Charge - SoC). */
    SoC,

    /** Voltaje medido. */
    Voltage
}
