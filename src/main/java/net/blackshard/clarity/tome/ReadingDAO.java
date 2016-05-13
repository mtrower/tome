package net.blackshard.clarity.tome;

import java.util.List;

/**
 * DAO interface for Reading persistance
 * 
 * @author Matthew R. Trower
 * @version 1.0
 */
public interface ReadingDAO<T extends Reading> {
    /**
     * Insert a new reading into the database.  This is an INSERT operation,
     * not an UPDATE.
     *
     * @param reading the reading to insert.
     * @since 1.0
     */
    public Long insert(T reading);

    /**
     * Retrieve a reading from the database by its ID.
     *
     * @param clazz the class of the reading to be retrieved.
     * @param id The primary key of the reading to be retrieved.
     * @since 1.0
     */
    public T get(Class<T> clazz, Long id);

    /**
     * Retrieve all readings from the database.  Note that this is undoubtedly
     * a very large number of readings.
     *
     * @param clazz the class of the reading to be retrieved.
     * @since 1.0
     */
    public List<T> getAll(Class<T> clazz);
    //public List<T> getRange(Long start, Long end);

    /**
     * Retrieve the last N readings from the database.
     *
     * @param clazz the class of the reading to be retrieved.
     * @param count The number of the readings to be retrieved.
     * @since 1.0
     */
    public List<T> getLatest(Class<T> clazz, int count);

    /**
     * Delete a reading from the database.
     *
     * @param templateClass the class of the reading to be retrieved.
     * @param id The primary key of the reading to be retrieved.
     * @since 1.0
     */
    public boolean delete(Class<T> templateClass, Long id);
}
