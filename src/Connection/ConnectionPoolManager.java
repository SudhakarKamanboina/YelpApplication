package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

  public class ConnectionPoolManager {

      public DataSource getDS() throws Exception {
          PoolProperties p = new PoolProperties();
          p.setUrl("jdbc:mysql://localhost:3306/test");
  p.setDriverClassName("com.mysql.jdbc.Driver");
  p.setUsername("root");
  p.setPassword("Smack123");
  
 System.out.println("Inside connection pooling");
 /*In Connection Pooling*/ 
  
  p.setJmxEnabled(true);
  p.setTestWhileIdle(false);
  p.setTestOnBorrow(true);
  p.setValidationQuery("SELECT 1");
  p.setTestOnReturn(false);
  p.setValidationInterval(30000);
  p.setTimeBetweenEvictionRunsMillis(30000);
  p.setMaxActive(500);
  p.setInitialSize(10);
  p.setMaxWait(10000);
  p.setRemoveAbandonedTimeout(60);
  p.setMinEvictableIdleTimeMillis(30000);
  p.setMinIdle(10);
  p.setLogAbandoned(true);
  p.setRemoveAbandoned(true);
  p.setJdbcInterceptors(
    "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
"org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
  DataSource datasource = new DataSource();
  datasource.setPoolProperties(p);
  return datasource;
      }
  }