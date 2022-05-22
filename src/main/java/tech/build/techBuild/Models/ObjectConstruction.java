package tech.build.techBuild.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="object_construction")
@AllArgsConstructor
@NoArgsConstructor
public class ObjectConstruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date endDate;
    private Date startDate;

    @ManyToOne
    @JoinColumn(name="builder_id")
    private Usr builderId;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Usr clientId;

    @ManyToOne
    @JoinColumn(name="object_id")
    private Object objectId;
}
