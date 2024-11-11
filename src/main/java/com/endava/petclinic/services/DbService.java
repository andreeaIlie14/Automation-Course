package com.endava.petclinic.services;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.util.EnvReader;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DbService {

    public Owner getOwnerById(Long id) {
        // Mapare coloane tabel către proprietăți obiect
        var mapColumnsToProperties = new HashMap<String, String>();
        mapColumnsToProperties.put("first_name", "firstName");
        mapColumnsToProperties.put("last_name", "lastName");
        mapColumnsToProperties.put("address", "address");
        mapColumnsToProperties.put("city", "city");
        mapColumnsToProperties.put("telephone", "telephone");

        BeanProcessor beanProcessor = new BeanProcessor(mapColumnsToProperties);
        RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);
        ResultSetHandler<Owner> h = new BeanHandler<>(Owner.class, rowProcessor);

        try (Connection conn = DriverManager.getConnection(
                EnvReader.getDbUrl(), EnvReader.getDbUsername(), EnvReader.getDbPassword())) {

            QueryRunner runner = new QueryRunner();
            Owner owner = runner.query(conn, "SELECT * FROM owners WHERE id = ?", h, id);

            if (owner == null) {
                throw new RuntimeException("Owner with ID " + id + " not found in the database.");
            }

            return owner;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve Owner from DB due to a database error", e);
        }
    }
}


//package com.endava.petclinic.services;
//
//import com.endava.petclinic.model.Owner;
//import com.endava.petclinic.util.EnvReader;
//import org.apache.commons.dbutils.*;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//
//public class DbService {
//
//    public Owner getOwnerById(Long id) {
//
//        try (Connection conn = DriverManager.getConnection(EnvReader.getDbUrl(), EnvReader.getDbUsername(), EnvReader.getDbPassword());
//        ) {
//            var mapColumnsToProperties = new HashMap<String, String>();
//            mapColumnsToProperties.put("first_name", "firstName");
//            mapColumnsToProperties.put("last_name", "lastName");
//            BeanProcessor beanProcessor = new BeanProcessor(mapColumnsToProperties);
//            RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);
//            ResultSetHandler<Owner> h = new BeanHandler<Owner>(Owner.class, rowProcessor);
//
//            QueryRunner runner = new QueryRunner();
//            Owner owner = runner.query(conn, "SELECT * from owners where id = ?", h, id);
//            return owner;
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Can't connect to DB", e);
//        }
//
//    }
//}
