package net.blackshard.clarity.tome;

import java.util.Date;
import java.util.LinkedHashMap;
import javax.persistence.*;

/**
 * DTO interface for Scribblet readings.  All readings must implement.
 *
 * @author Matthew R. Trower
 * @version 1.0
 */
@MappedSuperclass
public abstract class Reading {
    /**
     * Primary database key
     *
     * @since 1.0
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;

    /**
     * ID of machine reading was taken on
     *
     * @since 1.0
     */
    @Column(name = "machine_id")
    protected Integer machineId;

    /**
     * Timestamp reading was taken at
     *
     * @since 1.0
     */
    @Column(columnDefinition="timestamp with time zone")
    protected Date timestamp;

    /**
     * Default constructor.
     */
    public Reading() {}

    /**
     * Basic constructor initializing standard fields.
     *
     * @param machineId the ID of the machine the Reading was taken on
     * @param timestamp the date and time the Reading was taken at
     *
     * @since 1.0
     */
    public Reading(Integer machineId, Date timestamp) {
        setMachineId(machineId);
        setTimestamp(timestamp);
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
    public Reading(Integer machineId, Date timestamp, LinkedHashMap metrics) {
        setMachineId(machineId);
        setTimestamp(timestamp);
        setMetrics(metrics);
    }

    public Long getId() { return id; }
    public Integer getMachineId() { return machineId; }
    public Date getTimestamp() { return timestamp; }
    public void setId(Long value) { id = value; }
    public void setMachineId(Integer value) { machineId = value; }
    public void setTimestamp(Date value) { timestamp = value; }

    /**
     * Set all extended metrics in one go.  Excluded metrics are left
     * unchanged.  Extraneous metrics are ignored.
     *
     * @since 1.0
     */
    public abstract void setMetrics(LinkedHashMap<String, Integer> metrics);

    /**
     * Retrieve all extended metrics in one go.
     *
     * @since 1.0
     */
    public abstract LinkedHashMap<String, Integer> getMetrics();

    public String toString() {
        return    String.format("%s: %d, ", "id", id)
                + String.format("%s: %d, ", "machineId", machineId)
                + String.format("%s: %s, ", "timestamp", timestamp)
                + String.format("%s: %s ", "metrics", getMetrics());
    }
}
