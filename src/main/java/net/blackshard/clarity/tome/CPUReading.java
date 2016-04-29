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

    private Date timestamp;
    private Integer metric_system;
    private Integer metric_user;
    private Integer metric_idle;


    public CPUReading() {}

    public CPUReading(Integer machineId, Date timestamp,
            Integer system, Integer user, Integer idle) {
        this.id = id;
        this.machineId = machineId;
        this.timestamp = timestamp;
        this.metric_system = system;
        this.metric_user = user;
        this.metric_idle = idle;
    }
    

    public Long getId() { return id; }
    public Integer getMachineId() { return machineId; }
    public Date getTimestamp() { return timestamp; }
    public Integer getSystem() { return metric_system; }
    public Integer getUser() { return metric_user; }
    public Integer getIdle() { return metric_idle; }

    public void setId(Long value) { id = value; }
    public void setMachineId(Integer value) { machineId = value; }
    public void setTimestamp(Date value) { timestamp = value; }
    public void setSystem(Integer value) { metric_system = value; }
    public void setUser(Integer value) { metric_user = value; }
    public void setIdle(Integer value) { metric_idle = value; }
}
