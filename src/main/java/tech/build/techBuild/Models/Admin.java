package tech.build.techBuild.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="admin")
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean enabled;
    private Date endDate;
    private Date startDate;

    @ManyToOne
    @JoinColumn(name="admin_id")
    private Usr adminId;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Usr employeeId;
}
