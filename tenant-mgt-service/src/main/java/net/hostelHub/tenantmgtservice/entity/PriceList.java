package net.hostelHub.tenantmgtservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Entity @Table(name = "price_list")
@AllArgsConstructor @NoArgsConstructor
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_name")
    private String propertyName;

    private School school;

    @Column(name = "number_of_persons")
    private Integer numberOfPersons;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private HostelProperty property;

}
