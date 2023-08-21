package net.hostelHub.tenantmgtservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Entity @Table(name = "property_photos")
@AllArgsConstructor @NoArgsConstructor
public class PropertyPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_name")
    private String propertyName;

    private School school;

    @Column(name = "photo_url")
    private String photoUrl;

    private String description;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private HostelProperty property;

}




