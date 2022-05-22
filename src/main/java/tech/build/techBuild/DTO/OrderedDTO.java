package tech.build.techBuild.DTO;

import java.sql.Date;

public interface OrderedDTO {
    long getId();
    Date getOrdered_date();
    int getQuantity();
    long getBuilder_id();
    long getMaterial_id();
    String getName();
    String getManufacturer();
}
