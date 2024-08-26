package com.eVolGreen.eVolGreen.Models.Account.RelationTypeCompany;

import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CompanyClientRelation {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Compañia_id")
    @JsonBackReference("Compañia-RelacionCompañiaCliente")
    private CompanyUser Compañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cliente_id")
    @JsonBackReference("Cliente-RelacionCompañiaCliente")
    private ClientUser Cliente;

    @NotNull(message = "El tipo de relación no puede ser nulo.")
    @Enumerated(EnumType.STRING)
    private RelationType Relacion;

    public CompanyClientRelation() {}

    public CompanyClientRelation(CompanyUser Compañia, ClientUser Cliente, RelationType Relacion) {
        this.Compañia = Compañia;
        this.Cliente = Cliente;
        this.Relacion = Relacion;
        this.Compañia.getRelacionCompañiaCliente().add(this);
        this.Cliente.getRelacionCompañiaCliente().add(this);
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

    public ClientUser getCliente() {
        return Cliente;
    }

    public void setCliente(ClientUser cliente) {
        Cliente = cliente;
    }

    public @NotNull(message = "El tipo de relación no puede ser nulo.") RelationType getRelacion() {
        return Relacion;
    }

    public void setRelacion(@NotNull(message = "El tipo de relación no puede ser nulo.") RelationType relacion) {
        Relacion = relacion;
    }

    @Override
    public String toString() {
        return "CompanyClientRelation{" +
                "id=" + id +
                ", Compañia=" + Compañia +
                ", Cliente=" + Cliente +
                ", Relacion=" + Relacion +
                '}';
    }
}


