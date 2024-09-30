package com.bill.app.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class HandelDataBaseConnection {
    private static final String databaseName = "bills.db";
    private String databasePath;

    public HandelDataBaseConnection() {
    }

    public String getDatabasePath() {
        return databasePath + "/" + databaseName;
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    /* This method is designed to create the folder that will hold the database and files */
    public void createDatabaseFolder(String path) {
        Path theDirPath = Paths.get(path);
        try {
            // Ensure the path is absolute and canonical to prevent path traversal
            if (!Files.exists(theDirPath)) {
                // Create the folder and any non-existing parent directories
                Files.createDirectories(theDirPath);
                System.out.println("Directory created at: " + theDirPath);
            }
            Path canonicalPath = theDirPath.toRealPath();
            // Ensure databasePath is set correctly
            setDatabasePath(canonicalPath.toString());  // Use canonicalPath.toString() to avoid null issues
            System.out.println("Database Path Set: " + getDatabasePath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /* Method to establish a connection to the SQLite database */
    public Connection ConnectionEstablish() {
        Connection theConn = null; // Initialize the connection variable
        try {
            /* Attempt to establish a connection to the SQLite database using the database path */
            theConn = DriverManager.getConnection("jdbc:sqlite:" + getDatabasePath());
            System.out.println("Connection to SQLite has been established."); // Log success message
        } catch (SQLException e) {
            /* Catch any SQL exceptions and print the error message */
            System.out.println(e.getMessage());
        }
        /* Return the established connection for later use; do not close it here */
        return theConn;
    }


    /* Method to create a table named 'bill_info' in the SQLite database */
    public void creatingTable() {
        /* SQL query to create the 'bill_info' table with the specified columns and data types */
        String sqlQuery = "CREATE TABLE IF NOT EXISTS bill_info (" + " Id INTEGER PRIMARY KEY AUTOINCREMENT," // Unique identifier for each record
                + " Date_of_register TEXT NOT NULL," // Date of registration
                + " Address TEXT NOT NULL," // Vendor's address
                + " Name_vendor TEXT NOT NULL," // Vendor's name
                + " Tax_registration_number NUMERIC NOT NULL," // Vendor's tax registration number
                + " Product_name TEXT NOT NULL," // Name of the product
                + " Amount NUMERIC NOT NULL," // Quantity of the product
                + " Unit_price NUMERIC NOT NULL," // Price per unit of the product
                + " VAT_amount_string TEXT NOT NULL," // VAT amount as a string
                + " Total_price_without_tax NUMERIC NOT NULL," // Total price excluding tax
                + " Added_Tax_amount NUMERIC NOT NULL," // Amount of tax added
                + " Total_price_with_tax NUMERIC NOT NULL," // Total price including tax
                + " Tax_for_one_unit NUMERIC NOT NULL" // VAT amount for one unit
                + ");";

        /* Use try-with-resources to ensure the connection and statement are closed automatically */
        try (Connection conn = ConnectionEstablish();  // Establish a connection to the database
             Statement stmt = conn != null ? conn.createStatement() : null) { // Create a statement if the connection is valid

            if (stmt != null) {
                /* Execute the SQL query to create the table */
                stmt.execute(sqlQuery);
                System.out.println("Table 'bill_info' created successfully."); // Log success message
            } else {
                /* Log an error message if the statement could not be created due to a null connection */
                System.out.println("Failed to create Statement: Connection is null.");
            }

        } catch (SQLException e) {
            /* Catch and log any SQL exceptions that occur during table creation */
            System.out.println(e.getMessage());
        }
    }

    /* Method to update the 'bill_info' table with a new record from the provided DataModel */
    public void updateTable(DataModel dataModel) {

        /* SQL query to insert a new record into the 'bill_info' table */
        String sqlQuery = "INSERT INTO bill_info (Date_of_register, Address, Name_vendor, Tax_registration_number, " + "Product_name, Amount, Unit_price, VAT_amount_string, Total_price_without_tax, Added_Tax_amount, " + "Total_price_with_tax, Tax_for_one_unit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        /* Use try-with-resources to ensure the connection and prepared statement are closed automatically */
        try (Connection conn = ConnectionEstablish(); // Establish a connection to the database
             PreparedStatement stmt = conn.prepareStatement(sqlQuery)) { // Prepare the SQL statement

            /* Set the values in the prepared statement using the properties from the DataModel */
            stmt.setString(1, dataModel.getDate()); // Set the date of registration
            stmt.setString(2, dataModel.vendorAddressProperty().getValue()); // Set the vendor's address
            stmt.setString(3, dataModel.vendorNameProperty().getValue()); // Set the vendor's name
            stmt.setFloat(4, dataModel.VATNumberProperty().getValue()); // Set the tax registration number
            stmt.setString(5, dataModel.productNameProperty().getValue()); // Set the product name
            stmt.setFloat(6, dataModel.amountProperty().getValue()); // Set the amount of the product
            stmt.setFloat(7, dataModel.priceProperty().getValue()); // Set the unit price of the product
            stmt.setString(8, dataModel.VATAmountProperty().getValue()); // Set the VAT amount as a string
            stmt.setFloat(9, dataModel.totalPriceWithoutVat().getValue()); // Set the total price without tax
            stmt.setFloat(10, dataModel.VATPriceProperty().getValue()); // Set the added tax amount
            stmt.setFloat(11, dataModel.getTotalPriceWithVatProperty().getValue()); // Set the total price with tax
            stmt.setFloat(12, dataModel.oneVATPriceProperty().getValue()); // Set the VAT for one unit price

            /* Execute the update operation to insert the record */
            stmt.executeUpdate();
            System.out.println("Record inserted successfully."); // Log success message

        } catch (SQLException e) {
            /* Catch and log any SQL exceptions that occur during the update */
            System.out.println(e.getMessage());
        }
    }

}
