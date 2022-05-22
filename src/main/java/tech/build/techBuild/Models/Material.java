package tech.build.techBuild.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="material")
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String manufacturer;
    private String name;
    private String photo;
    private int price;
    private int quantity;


    @ManyToOne
    @JoinColumn(name="org_id")
    private Organization orgId;

    @ManyToOne
    @JoinColumn(name="kind_material_id")
    private KindMaterial kindMaterialId;


    @JsonIgnore
    @OneToMany(mappedBy = "materialId")
    private List<SupplyMaterial> materials;

}
