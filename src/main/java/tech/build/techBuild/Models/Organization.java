package tech.build.techBuild.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name="organization")
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private String name;
    private String phone;
    private Date rentDate;

    @JsonIgnore
    @OneToMany(mappedBy = "orgId")
    private List<Usr> users;

    @JsonIgnore
    @OneToMany(mappedBy = "orgId")
    private List<Material> materials;

    @JsonIgnore
    @OneToMany(mappedBy = "orgId")
    private List<Object> objects;
}
