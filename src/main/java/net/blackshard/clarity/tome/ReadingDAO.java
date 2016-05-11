package net.blackshard.clarity.tome;

import java.util.List;

/**
 * @author Matthew R. Trower
 * class CPUReadingDAO
 * 
 * DAO interface for Reading persistance
 */
public interface ReadingDAO<T extends Reading> {
    public Long insert(T reading);
    public T get(Class<T> clazz, Long id);
    public List<T> getAll(Class<T> clazz);
    //public List<T> getRange(Long start, Long end);
    public List<T> getLatest(Class<T> clazz, int count);
    public boolean delete(Class<T> templateClass, Long id);
}
