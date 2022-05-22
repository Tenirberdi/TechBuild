package tech.build.techBuild.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="kind_material")
@AllArgsConstructor
@NoArgsConstructor
public class KindMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String kind;
    private String img;

    @JsonIgnore
    @OneToMany(mappedBy = "kindMaterialId")
    private List<Material> materials;

}
