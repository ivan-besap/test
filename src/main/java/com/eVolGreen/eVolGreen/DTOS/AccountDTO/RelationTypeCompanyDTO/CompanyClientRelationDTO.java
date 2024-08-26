package com.eVolGreen.eVolGreen.DTOS.AccountDTO.RelationTypeCompanyDTO;

import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.CompanyClientRelation;
import com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany.RelationType;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import jakarta.validation.constraints.NotNull;

public class CompanyClientRelationDTO {

    private long id;

    private CompanyUser Compañia;

    private ClientUser Cliente;

    @NotNull(message = "El tipo de relación no puede ser nulo.")
    private RelationType Relacion;

    public CompanyClientRelationDTO(CompanyClientRelation RelacionCompañiaCliente) {

        id = RelacionCompañiaCliente.getId();
        Compañia = RelacionCompañiaCliente.getCompañia();
        Cliente = RelacionCompañiaCliente.getCliente();
        Relacion = RelacionCompañiaCliente.getRelacion();
    }

    public long getId() {
        return id;
    }

    public CompanyUser getCompañia() {
        return Compañia;
    }

    public ClientUser getCliente() {
        return Cliente;
    }

    public @NotNull(message = "El tipo de relación no puede ser nulo.") RelationType getRelacion() {
        return Relacion;
    }

    @Override
    public String toString() {
        return "CompanyClientRelationDTO{" +
                "id=" + id +
                ", Compañia=" + Compañia +
                ", Cliente=" + Cliente +
                ", Relacion=" + Relacion +
                '}';
    }
}
