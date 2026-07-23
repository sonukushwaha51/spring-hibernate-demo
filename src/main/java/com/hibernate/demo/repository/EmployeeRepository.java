package com.hibernate.demo.repository;

import com.hibernate.demo.configuration.HibernateConfiguration;
import com.hibernate.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

import static com.hibernate.demo.configuration.HibernateConfiguration.createSessionFactory;

public class EmployeeRepository {

    private final SessionFactory sessionFactory = createSessionFactory();

    public void saveEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.persist(employee);
                transaction.commit();
            } catch (Exception exception) {
                assert transaction != null;
                transaction.rollback();
                session.close();
            }
        }
    }

    public Employee updateEmployee(Employee employee) {
        Employee employee1 = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                employee1 = session.merge(employee);
                transaction.commit();
            } catch (Exception exception) {
                assert transaction != null;
                transaction.rollback();
                session.close();
            }
        }
        return employee1;
    }

    public Optional<Employee> findById(int id) {
        Employee employee = null;
        try (Session session = sessionFactory.openSession()) {
            employee = session.get(Employee.class, id);
        }
        return Optional.ofNullable(employee);
    }
}
