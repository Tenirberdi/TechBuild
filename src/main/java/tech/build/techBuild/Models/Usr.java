package tech.build.techBuild.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="usr")
@AllArgsConstructor
@NoArgsConstructor
public class Usr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean enabled;
    private String fullName;
    private String login;
    private String pass;
    private String email;
    private String phone;
    private String photo;

    @ManyToOne
    @JoinColumn(name="org_id")
    private Organization orgId;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role roleId;


    @JsonIgnore
    @OneToMany(mappedBy = "adminId")
    private List<Admin> admins;

    @JsonIgnore
    @OneToMany(mappedBy = "employeeId")
    private List<Admin> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "builderId")
    private List<ObjectConstruction> builders;

    @JsonIgnore
    @OneToMany(mappedBy = "clientId")
    private List<ObjectConstruction> clients;

    @JsonIgnore
    @OneToMany(mappedBy = "builderId")
    private List<SupplyMaterial> supplyMaterials;

    @JsonIgnore
    @OneToMany(mappedBy = "supplierId")
    private List<SupplyMaterial> supplyMaterials1;

}

