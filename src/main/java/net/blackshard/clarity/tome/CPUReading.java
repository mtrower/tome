package net.blackshard.clarity.tome;

import java.util.Date;
import java.util.LinkedHashMap;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Matthew R. Trower
 * class CPUReading
 * 
 * DTO for CPU Scribblet Readings
 */
@Entity
@Table(name = "plugin_cpu")
public class CPUReading extends Reading {
    private Integer metricSystem;
    private Integer metricUser;
    private Integer metricIdle;


    public CPUReading() {}

    public CPUReading(Integer machineId, Date timestamp,
            Integer user, Integer system, Integer idle) {
        super(machineId, timestamp);

        this.metricUser = user;
        this.metricSystem = system;
        this.metricIdle = idle;
    }
    

    public Integer getMetricUser() { return metricUser; }
    public Integer getMetricSystem() { return metricSystem; }
    public Integer getMetricIdle() { return metricIdle; }

    public void setMetricUser(Integer value) { metricUser = value; }
    public void setMetricSystem(Integer value) { metricSystem = value; }
    public void setMetricIdle(Integer value) { metricIdle = value; }

    public void setMetrics(LinkedHashMap<String, Integer> metrics) {
        if (metrics.containsKey("user")) metricUser = metrics.get("user");
        if (metrics.containsKey("system")) metricSystem = metrics.get("system");
        if (metrics.containsKey("idle")) metricIdle = metrics.get("idle");
    }

    public LinkedHashMap<String, Integer> getMetrics() {
        LinkedHashMap<String, Integer> metrics = new LinkedHashMap();
        metrics.put("user", metricUser);
        metrics.put("system", metricSystem);
        metrics.put("idle", metricIdle);

        return metrics;
    }
}
