# LJDB [![](https://jitpack.io/v/Osiris-Team/LJDB.svg)](https://jitpack.io/#Osiris-Team/LJDB)
Light Java database. Lightweight utilities for your SQL database to keep it low-level 
but make your life a bit easier. Add this as dependency via 
[Maven/Gradle/Sbt/Leinigen](https://jitpack.io/#Osiris-Team/LJDB/LATEST).

**SQLUtils.initDatabase(...)** will create the database, missing tables and columns,
and even update the columns definitions.

```java
import SQLUtils;
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
                        sql.col("name", "VARCHAR(20) NOT NULL"),
                        sql.col("age", "TINYINT NOT NULL"),
                        sql.col("email", "VARCHAR(255) NOT NULL"))),
                (tableStats = sql.table("stats",
                        sql.col("timestamp", "TIMESTAMP NOT NULL"),
                        sql.col("daily_visits", "LONG NOT NULL"),
                        sql.col("daily_users", "INT NOT NULL")))
                );
    }
}
```
