package net.blackshard.clarity.tome;

import java.util.Date;
import java.util.LinkedHashMap;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DTO for Mem Scribblet Readings
 *
 * @author Matthew R. Trower
 * @version 1.0
 */
@Entity
@Table(name = "plugin_mem")
public class MemReading extends Reading {
    private Integer metricSwap;
    private Integer metricFree;


    /**
     * Default Constructor
     *
     * @since 1.0
     */
    public MemReading() {}

    /**
     * Basic constructor initializing standard fields.
     *
     * @param machineId the ID of the machine the Reading was taken on
     * @param timestamp the date and time the Reading was taken at
     *
     * @since 1.0
     */
    public MemReading(Integer machineId, Date timestamp,
            Integer swap, Integer free) {
        super(machineId, timestamp);

        this.metricSwap = swap;
        this.metricFree = free;
    }
    

    /**
     * Retrieve free swap space
     *
     * @return free swap in KB
     * @since 1.0
     */
    public Integer getMetricSwap() { return metricSwap; }

    /**
     * Retrieve free system memory
     *
     * @return free memory in KB
     * @since 1.0
     */
    public Integer getMetricFree() { return metricFree; }

    /**
     * Set free swap space
     *
     * @param value swap in KB
     * @since 1.0
     */
    public void setMetricSwap(Integer value) { metricSwap = value; }

    /**
     * Set free system memory
     *
     * @param value memory in KB
     * @since 1.0
     */
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
