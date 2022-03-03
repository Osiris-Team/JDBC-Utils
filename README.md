# JDBC-Utils [![](https://jitpack.io/v/Osiris-Team/JDBC-Utils.svg)](https://jitpack.io/#Osiris-Team/JDBC-Utils)
Collection of SQL utilities to make your life a bit easier. Java Database Connectivity (JDBC) Utils.
Add this as dependency via [Maven/Gradle/Sbt/Leinigen](https://jitpack.io/#Osiris-Team/JDBC-Utils/LATEST).

**SQLUtils.initDatabase(...)** will create the database, missing tables and columns,
and even update the columns definitions.

```java
import com.osiris.ljdb.SQLUtils;
import com.osiris.ljdb.SQLTable;

import java.sql.Connection;

class Example {
    public static SQLTable tableUsers;
    public static SQLTable tableStats;

    public static void main(String[] args) {
        String dbName = "db_name";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, "root", "");
        SQLUtils sql = new SQLUtils();
        sql.initDatabase(con, dbName,
                (tableUsers = sql.table("users", 
                        sql.col("id", "INT NOT NULL PRIMARY KEY"),
                        sql.col("name", "VARCHAR(20) NOT NULL"),
                        sql.col("age", "TINYINT NOT NULL"),
                        sql.col("email", "VARCHAR(255) NOT NULL"))),
                (tableStats = sql.table("stats",
                        sql.col("id", "INT NOT NULL PRIMARY KEY"),
                        sql.col("timestamp", "TIMESTAMP NOT NULL"),
                        sql.col("daily_visits", "LONG NOT NULL"),
                        sql.col("daily_users", "INT NOT NULL")))
                );
    }
}
```
