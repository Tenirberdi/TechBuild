package tech.build.techBuild.DTO;

import java.sql.Date;

public interface DeliveredListDTO {
    long getId();
    Date getDelivered_date();
    Date getOrdered_date();
    int getQuantity();
    long getBuilder_id();
    String getBuilder_full_name();
    long getMaterial_id();
    String getName();
    long getSupplier_id();
    String getSupplier_full_name();
}
