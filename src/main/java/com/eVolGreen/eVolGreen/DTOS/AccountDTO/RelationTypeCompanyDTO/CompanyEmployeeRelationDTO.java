package com.eVolGreen.eVolGreen.DTOS.AccountDTO.RelationTypeCompanyDTO;

import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyEmployeeRelation;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.RelationType;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.EmployeeUser;

import jakarta.validation.constraints.NotNull;

public class CompanyEmployeeRelationDTO {

    private long id;

    private CompanyUser company;

    private EmployeeUser Trabajador;

    @NotNull(message = "El tipo de relación no puede ser nulo.")
    private RelationType Relacion;

    public CompanyEmployeeRelationDTO(CompanyEmployeeRelation RelacionCompañiaTrabajador) {

        id = RelacionCompañiaTrabajador.getId();
        company = RelacionCompañiaTrabajador.getCompañia();
        Trabajador = RelacionCompañiaTrabajador.getTrabajador();
        Relacion = RelacionCompañiaTrabajador.getRelacion();
    }

    public long getId() {
        return id;
    }

    public CompanyUser getCompañia() {
        return company;
    }

    public EmployeeUser getTrabajador() {
        return Trabajador;
    }

    public @NotNull(message = "El tipo de relación no puede ser nulo.") RelationType getRelacion() {
        return Relacion;
    }

    @Override
    public String toString() {
        return "CompanyEmployeeRelationDTO{" +
                "id=" + id +
                ", Compañia=" + company +
                ", Trabajador=" + Trabajador +
                ", Relacion=" + Relacion +
                '}';
    }
}
