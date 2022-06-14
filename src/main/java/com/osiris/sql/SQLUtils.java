package com.osiris.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtils {
    /**
     * Creates the database with the provided name if not existing yet. <br>
     * Creates the provided tables if not existing yet. <br>
     * Creates the {@link SQLTable#columns} if not existing yet. <br>
     * Also updates the {@link SQLColumn#definition} of the column. <br>
     */
    public void initTables(Connection con, SQLTable... tables) throws SQLException {
        for (SQLTable table :
                tables) {
            try (Statement s = con.createStatement()) {
                s.executeUpdate("CREATE TABLE IF NOT EXISTS " + table.name +
                        "(id int NOT NULL AUTO_INCREMENT," +
                        " PRIMARY KEY(id))");
            }
            SQLColumn columnBefore = null;
            for (SQLColumn column :
                    table.columns) {
                try (Statement s0 = con.createStatement()) {
                    ResultSet rs = s0.executeQuery("SHOW COLUMNS FROM `" + table.name + "` LIKE '" + column.name + "';");
                    if (rs == null || !rs.next()) {
                        StringBuilder builder = new StringBuilder();
                        builder.append("ALTER TABLE " + table.name + " ADD COLUMN " + column.name + " " + column.definition);
                        if (columnBefore != null) builder.append(" AFTER " + columnBefore.name + ";");

                        try (Statement s1 = con.createStatement()) {
                            s1.execute(builder.toString());
                        }
                    } else { // This means that the column already exists, so update the definitions
                        try (Statement s1 = con.createStatement()) {
                            s1.execute("ALTER TABLE " + table.name + " MODIFY COLUMN " + column.name + " " + column.definition + ";");
                        }
                    }
                }
                columnBefore = column;
            }
        }
    }

    public SQLTable table(String name, SQLColumn... columns){
        return new SQLTable(name, columns);
    }
    public SQLColumn col(String name, String definition){
        return new SQLColumn(name, definition);
    }
}
