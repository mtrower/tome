package net.blackshard.clarity.tome;

import java.util.Date;
import java.util.LinkedHashMap;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Matthew R. Trower
 * class MemReading
 * 
 * DTO for Mem Scribblet Readings
 */
@Entity
@Table(name = "plugin_mem")
public class MemReading extends Reading {
    private Integer metricSwap;
    private Integer metricFree;


    public MemReading() {}

    public MemReading(Integer machineId, Date timestamp,
            Integer swap, Integer free) {
        super(machineId, timestamp);

        this.metricSwap = swap;
        this.metricFree = free;
    }
    

    public Integer getMetricSwap() { return metricSwap; }
    public Integer getMetricFree() { return metricFree; }

    public void setMetricSwap(Integer value) { metricSwap = value; }
    public void setMetricFree(Integer value) { metricFree = value; }

    public void setMetrics(LinkedHashMap<String, Integer> metrics) {
        if (metrics.containsKey("swap")) metricSwap = metrics.get("swap");
        if (metrics.containsKey("free")) metricFree = metrics.get("free");
    }

    public LinkedHashMap<String, Integer> getMetrics() {
        LinkedHashMap<String, Integer> metrics = new LinkedHashMap();
        metrics.put("swap", metricSwap);
        metrics.put("free", metricFree);

        return metrics;
    }
}
