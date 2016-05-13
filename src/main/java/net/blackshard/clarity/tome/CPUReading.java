package net.blackshard.clarity.tome;

import java.util.Date;
import java.util.LinkedHashMap;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DTO for CPU Scribblet Readings
 *
 * @author Matthew R. Trower
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "plugin_cpu")
public class CPUReading extends Reading {
    private Integer metricSystem;
    private Integer metricUser;
    private Integer metricIdle;


    /**
     * Default Constructor
     *
     * @since 1.0
     */
    public CPUReading() {}

    /**
     * Basic constructor initializing standard fields.
     *
     * @param machineId the ID of the machine the Reading was taken on
     * @param timestamp the date and time the Reading was taken at
     *
     * @since 1.0
     */
    public CPUReading(Integer machineId, Date timestamp,
            Integer user, Integer system, Integer idle) {
        super(machineId, timestamp);

        this.metricUser = user;
        this.metricSystem = system;
        this.metricIdle = idle;
    }

    /**
     * Basic constructor initializing standard and extended fields.
     *
     * @param machineId the ID of the machine the Reading was taken on
     * @param timestamp the date and time the Reading was taken at
     * @param metrics map of all metrics to be set. The map will be copied,
     * with excluded metrics remaining undefined and extraneous metrics
     * ignored.
     *
     * @since 1.0
     */
    public CPUReading(Integer machineId, Date timestamp,
            LinkedHashMap<String, Integer> metrics) {
        super(machineId, timestamp, metrics);
    }
    

    /**
     * Retrieve CPU time spent on user operations
     *
     * @return percentage of CPU user time
     * @since 1.0
     */
    public Integer getMetricUser() { return metricUser; }

    /**
     * Retrieve CPU time spent on system operations
     *
     * @return percentage of CPU system time
     * @since 1.0
     */
    public Integer getMetricSystem() { return metricSystem; }

    /**
     * Retrieve CPU time spent idle
     *
     * @return percentage of CPU idle time
     * @since 1.0
     */
    public Integer getMetricIdle() { return metricIdle; }

    /**
     * Set CPU time spent on user operations
     *
     * @param value percentage of CPU user time
     * @since 1.0
     */
    public void setMetricUser(Integer value) { metricUser = value; }

    /**
     * Set CPU time spent on system operations
     *
     * @param value percentage of CPU system time
     * @since 1.0
     */
    public void setMetricSystem(Integer value) { metricSystem = value; }

    /**
     * Set CPU time spent idle
     *
     * @param value percentage of CPU idle time
     * @since 1.0
     */
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
