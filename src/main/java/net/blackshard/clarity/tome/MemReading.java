package net.blackshard.clarity.tome;

import java.util.Date;
import java.util.LinkedHashMap;
import javax.persistence.*;

/**
 * @author Matthew R. Trower
 * class MemReading
 * 
 * DTO for Mem Scribblet Readings
 */
@Entity
@Table(name = "plugin_cpu")
public class MemReading implements Reading {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "machine_id")
    private Integer machineId;

    @Column(columnDefinition="timestamp with time zone")
    private Date timestamp;

    private Integer metricSwap;
    private Integer metricFree;


    public MemReading() {}

    public MemReading(Integer machineId, Date timestamp,
            Integer swap, Integer free) {
        this.id = id;
        this.machineId = machineId;
        this.timestamp = timestamp;
        this.metricSwap = swap;
        this.metricFree = free;
    }
    

    public Long getId() { return id; }
    public Integer getMachineId() { return machineId; }
    public Date getTimestamp() { return timestamp; }
    public Integer getMetricSwap() { return metricSwap; }
    public Integer getMetricFree() { return metricFree; }

    public void setId(Long value) { id = value; }
    public void setMachineId(Integer value) { machineId = value; }
    public void setTimestamp(Date value) { timestamp = value; }
    public void setMetricSwap(Integer value) { metricSwap = value; }
    public void setMetricFree(Integer value) { metricFree = value; }

    public LinkedHashMap getMetrics() {
        LinkedHashMap<String, Integer> metrics = new LinkedHashMap();
        metrics.put("swap", metricSwap);
        metrics.put("free", metricFree);

        return metrics;
    }

    public String toString() {                                                                                                                                              
        return    String.format("%s: %d, ", "id", id)
                + String.format("%s: %d, ", "machineId", machineId)
                + String.format("%s: %s, ", "timestamp", timestamp)
                + String.format("%s: %s ", "metrics", getMetrics());
    }
}
