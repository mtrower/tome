package net.blackshard.clarity.tome;

import java.util.Date;
import javax.persistence.*;

/**
 * @author Matthew R. Trower
 * class CPUReading
 * 
 * DTO for CPU Scribblet Readings
 */
@Entity
@Table(name = "plugin_cpu")
public class CPUReading implements Reading {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "machine_id")
    private Integer machineId;

    @Column(columnDefinition="timestamp with time zone")
    private Date timestamp;

    private Integer metricSystem;
    private Integer metricUser;
    private Integer metricIdle;


    public CPUReading() {}

    public CPUReading(Integer machineId, Date timestamp,
            Integer system, Integer user, Integer idle) {
        this.id = id;
        this.machineId = machineId;
        this.timestamp = timestamp;
        this.metricSystem = system;
        this.metricUser = user;
        this.metricIdle = idle;
    }
    

    public Long getId() { return id; }
    public Integer getMachineId() { return machineId; }
    public Date getTimestamp() { return timestamp; }
    public Integer getMetricSystem() { return metricSystem; }
    public Integer getMetricUser() { return metricUser; }
    public Integer getMetricIdle() { return metricIdle; }

    public void setId(Long value) { id = value; }
    public void setMachineId(Integer value) { machineId = value; }
    public void setTimestamp(Date value) { timestamp = value; }
    public void setMetricSystem(Integer value) { metricSystem = value; }
    public void setMetricUser(Integer value) { metricUser = value; }
    public void setMetricIdle(Integer value) { metricIdle = value; }
}
