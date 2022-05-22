package tech.build.techBuild.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="supply_material")
@AllArgsConstructor
@NoArgsConstructor
public class SupplyMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date deliveredDate;
    private Date orderedDate;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="builder_id")
    private Usr builderId;

    @ManyToOne
    @JoinColumn(name="material_id")
    private Material materialId;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Usr supplierId;

}
