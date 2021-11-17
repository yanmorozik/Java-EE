package eu.senla.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class TransactionalAspect {

    private final Logger logger = Logger.getLogger(TransactionalAspect.class.getName());

    @Autowired
    private final Connection connection;

    public TransactionalAspect(Connection connection) {
        this.connection = connection;
    }

    @Around("@within(eu.senla.library.annotation.Transactional) || @annotation(eu.senla.library.annotation.Transactional)")
    public Object runInTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            connection.setAutoCommit(false);
            Object object = joinPoint.proceed();
            connection.commit();
            return object;
        } catch (RuntimeException e) {
            connection.rollback();
            logger.log(Level.SEVERE,e.getMessage());
            throw e;
        } catch (Exception e) {
            connection.commit();
            logger.log(Level.SEVERE,e.getMessage());
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
