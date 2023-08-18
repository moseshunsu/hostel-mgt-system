package net.hostelHub.tenantmgtservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Setter @Getter @Table(name = "hostel_properties")
@AllArgsConstructor @NoArgsConstructor @Entity
public class HostelProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private School school;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "tenant_code", referencedColumnName = "tenant_code" , nullable = false)
    private Tenant tenant;

    @Column(nullable = false)
    private String address;

    private String description;

    @Column(name = "total_rooms")
    private int totalRooms;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @ElementCollection
    @CollectionTable(name = "property_photos", joinColumns = @JoinColumn(name = "property_id"))
    @MapKeyColumn(name = "photo_index")
    @Column(name = "photo_url")
    private Map<Name, String> photos = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "price_list", joinColumns = @JoinColumn(name = "property_id"))
    @MapKeyColumn(name = "number_of_persons")
    @Column(name = "price")
    private Map<Integer, Double> priceList = new HashMap<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
