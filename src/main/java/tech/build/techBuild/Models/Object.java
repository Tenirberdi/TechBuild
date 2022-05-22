package tech.build.techBuild.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="object")
@AllArgsConstructor
@NoArgsConstructor
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private int cost;
    private String name;
    private String photo;

    @ManyToOne
    @JoinColumn(name="org_id")
    private Organization orgId;

    @JsonIgnore
    @OneToMany(mappedBy = "objectId")
    private List<ObjectConstruction> objectConstructions;
}
