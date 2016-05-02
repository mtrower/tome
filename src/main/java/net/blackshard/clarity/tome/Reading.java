package net.blackshard.clarity.tome;

import java.util.Date;
import java.util.LinkedHashMap;
import javax.persistence.*;

/**
 * @author Matthew R. Trower
 * class Reading
 * 
 * DTO interface for Scribblet Readings
 */
@MappedSuperclass
public abstract class Reading {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "machine_id")
    protected Integer machineId;

    @Column(columnDefinition="timestamp with time zone")
    protected Date timestamp;

    public Reading() {}
    public Reading(Integer machineId, Date timestamp) {
        this.machineId = machineId;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public Integer getMachineId() { return machineId; }
    public Date getTimestamp() { return timestamp; }
    public void setId(Long value) { id = value; }
    public void setMachineId(Integer value) { machineId = value; }
    public void setTimestamp(Date value) { timestamp = value; }

    public abstract LinkedHashMap getMetrics();

    public String toString() {                                                                                                                                              
        return    String.format("%s: %d, ", "id", id)
                + String.format("%s: %d, ", "machineId", machineId)
                + String.format("%s: %s, ", "timestamp", timestamp)
                + String.format("%s: %s ", "metrics", getMetrics());
    }
}
