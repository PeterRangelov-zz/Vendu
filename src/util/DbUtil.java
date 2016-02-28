package util;


import dto.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class DbUtil {
    ServiceRegistryBuilder ssrb;
    private SessionFactory sessionFactory;
    private Configuration c;

    public DbUtil () {
        c = new Configuration();
        c.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", System.getenv("MYSQL_URL"))
                .setProperty("hibernate.connection.username", System.getenv("MYSQL_USERNAME"))
                .setProperty("hibernate.connection.password", System.getenv("MYSQL_PASSWORD"))
                .setProperty("hibernate.hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext")
                .addAnnotatedClass(User.class);
        ssrb = new ServiceRegistryBuilder().applySettings(c.getProperties());
        sessionFactory = c.buildSessionFactory(ssrb.buildServiceRegistry());
    }

    public SessionFactory getFactory () {
        return sessionFactory;
    }

}
