package net.blackshard.clarity.tome;

import java.util.Date;

/**
 * @author Matthew R. Trower
 * class Reading
 * 
 * DTO interface for Scribblet Readings
 */
public interface Reading {
    public Long getId();
    public Integer getMachineId();
    public Date getTimestamp();

    public void setId(Long value);
    public void setMachineId(Integer value);
    public void setTimestamp(Date value);
}
