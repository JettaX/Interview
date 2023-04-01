package org.sl.DTO;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sl.entity.Student;
import org.sl.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class StudentDtoImpl implements StudentDto {
    @Override
    public void save(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(List<Student> students) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            students.forEach(session::persist);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Student> findById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("from Student s where s.id = :id", Student.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findByMark(int mark) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("from Student s where s.mark = :mark", Student.class);
            query.setParameter("mark", mark);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Student> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("from Student", Student.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public boolean deleteById(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var query = session.createQuery("delete from Student s where s.id = :id");
            query.setParameter("id", id);
            int rowCount = query.executeUpdate();
            transaction.commit();
            return rowCount > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public long size() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(*) from Student", Long.class);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
