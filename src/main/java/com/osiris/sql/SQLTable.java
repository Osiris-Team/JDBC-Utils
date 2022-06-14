package com.osiris.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLTable {
    public String name;
    public List<SQLColumn> columns;
    public String insert;
    public String update;
    public String delete;

    public SQLTable(String name, SQLColumn... columns) {
        this.name = name;
        if (columns == null || columns.length==0) this.columns = new ArrayList<>();
        else this.columns = Arrays.asList(columns);
        this.insert = "INSERT INTO "+name+" ";
        this.update = "UPDATE "+name+" ";
        this.delete = "DELETE FROM "+name+" ";
    }

    public SQLTable(String name, List<SQLColumn> columns) {
        this.name = name;
        this.columns = columns;
    }
}
