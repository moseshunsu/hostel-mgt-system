package net.hostelHub.tenantmgtservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.hostelHub.tenantmgtservice.utils.School;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @JsonIgnore
    private HostelProperty property;

}




