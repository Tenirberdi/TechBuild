package tech.build.techBuild.DTO;

import java.sql.Date;

public interface RegisteredUsers {
    long getId();
    Date getStart_date();
    Date getEnd_date();
    long getAdmin_id();
    long getEmployee_id();
}
