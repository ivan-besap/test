package com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany;

import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CompanyEmployeeRelation {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Compañia_id", nullable = false)
    @JsonBackReference("Compañia-RelacionCompañiaEmpleado")
    private CompanyUser Compañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Trabajador_id", nullable = false)
    @JsonBackReference("Trabajador-RelacionCompañiaEmpleado")
    private EmployeeUser Trabajador;

    @NotNull(message = "El tipo de relación no puede ser nulo.")
    @Enumerated(EnumType.STRING)
    private RelationType Relacion;

    public CompanyEmployeeRelation() {}

    public CompanyEmployeeRelation(CompanyUser Compañia, EmployeeUser Trabajador, RelationType Relacion) {
        this.Compañia = Compañia;
        this.Trabajador = Trabajador;
        this.Relacion = Relacion;
        this.Compañia.getRelacionCompañiaEmpleado().add(this);
        this.Trabajador.getRelacionCompañiaEmpleado().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyUser getCompañia() {
        return Compañia;
    }

    public void setCompañia(CompanyUser compañia) {
        Compañia = compañia;
    }

    public EmployeeUser getTrabajador() {
        return Trabajador;
    }

    public void setTrabajador(EmployeeUser trabajador) {
        Trabajador = trabajador;
    }

    public @NotNull(message = "El tipo de relación no puede ser nulo.") RelationType getRelacion() {
        return Relacion;
    }

    public void setRelacion(@NotNull(message = "El tipo de relación no puede ser nulo.") RelationType relacion) {
        Relacion = relacion;
    }

    @Override
    public String toString() {
        return "CompanyEmployeeRelation{" +
                "id=" + id +
                ", Compañia=" + Compañia +
                ", Trabajador=" + Trabajador +
                ", Relacion=" + Relacion +
                '}';
    }
}
