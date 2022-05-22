package tech.build.techBuild.DTO;

import java.sql.Date;

public interface NotFinishedObjectsDTO {
    String getPhoto();
    long getId();
    Date getStart_date();
    long getBuilder_id();
    String getBuilderFullName();
    long getClient_id();
    String getCLientFullName();
    long getObject_id();
    String getAddress();
    String getName();
}
