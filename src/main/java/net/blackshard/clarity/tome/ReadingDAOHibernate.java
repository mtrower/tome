package net.blackshard.clarity.tome;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Hibernate implementation of ReadingDAO
 *
 * @author Matthew R. Trower
 * @version 1.0
 */
public class ReadingDAOHibernate<T extends Reading> implements ReadingDAO<T> {
    public Long insert(T reading) {
        Session book = Library.getBook();
        Transaction page = null;
        Long id = new Long(-1);

        try {
            page = book.beginTransaction();
            id = (Long) book.save(reading);
            page.commit();
        } catch (HibernateException he) {
            if (page != null)
                page.rollback();

            he.printStackTrace();
        } finally {
            book.close();
        }

        return id;
    }

    public T get(Class<T> clazz, Long id) {
        Session book = Library.getBook();
        Transaction page = null;
        T reading = null;

        try {
            page = book.beginTransaction();
            reading = (T)book.get(clazz, id);
            page.commit();
        } catch (HibernateException he) {
            if (page != null)
                page.rollback();

            he.printStackTrace();
        } finally {
            book.close();
        }

        return reading;
    }

    public List<T> getAll(Class<T> clazz) {
        Session book = Library.getBook();
        Transaction page = null;
        List res = null;

        try {
            String tableName = clazz.getName();
            page = book.beginTransaction();
            res = book
                .createQuery("FROM " + tableName)
                .list();
            page.commit();
        } catch (HibernateException he) {
            if (page != null)
                page.rollback();

            he.printStackTrace();
        } finally {
            book.close();
        }

        return res;
    }

    //public List<T> getRange(Long start, Long end) {return null;}
    public List<T> getLatest(Class<T> clazz, int count) {
        Session book = Library.getBook();
        Transaction page = null;
        List res = null;

        try {
            String tableName = clazz.getName();
            page = book.beginTransaction();
            res = book
                .createQuery("FROM " + tableName + " ORDER BY timestamp DESC")
                .setMaxResults(count)
                .list();
            page.commit();
        } catch (HibernateException he) {
            if (page != null)
                page.rollback();

            he.printStackTrace();
        } finally {
            book.close();
        }

        return res;
    }

    public boolean delete(Class<T> templateClass, Long id) {
        Session book = Library.getBook();
        Transaction page = null;

        try {
            page = book.beginTransaction();
            T reading = (T)book.get(templateClass, id);
            book.delete(reading);
            page.commit();
        } catch (HibernateException he) {
            if (page != null)
                page.rollback();

            he.printStackTrace();
            return false;
        } finally {
            book.close();
        }

        return true;
    }
}
