package com.bill.app.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HandelDataBaseConnection {
    private  static String databaseName = "bills";
    private String databasePath;

    public HandelDataBaseConnection() {

    }

    public String getDatabasePath() {
        return databasePath+"/"+databaseName;
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }
    /* This methode designed to create the folder that will hold database and files */
    public void createDatabaseFolder(String path)
    {
        /* First we need check if the folder exist or not and perform security to prevent path traversal */



        /* Here convert the string to path object and ten create the folder by using Files that take
        * the object theDirPath as a parameter theDirPath hold the path we will create the database in.*/

        Path theDirPath = Paths.get(path);
        try {
            /* Ensure that the path is absolute and canonical to prevent path traversal*/
            Path canonicalPath = theDirPath.toRealPath();
            /*attempts to create the specified directory. If the directory already exists
             it will not throw an error and will just skip the creation process.
             If any of the directories in the path don’t exist (even intermediate ones), it will create them all.
              This is important because, unlike Files.createDirectory(), which only creates the final directory
               Files.createDirectories() ensures that the entire path is created, including any non-existing parent directories.*/
            if (!Files.exists(canonicalPath))
            {
                /*Create the folder and any non-existing parent directories*/
                Files.createDirectories(canonicalPath);
                System.out.println("Directory created at: " + canonicalPath);
            }
            setDatabasePath(theDirPath.toString());
            ConnectionEstablish();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void ConnectionEstablish()
    {
        Connection theConn = null;
        try {
            theConn = DriverManager.getConnection ("jdbc:sqlite:" + getDatabasePath());
            System.out.println ("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println (e.getMessage ());
        }
        finally
        {
            try
            {
                if (theConn != null)
                {
                    theConn.close ();
                }
            }
            catch (SQLException ex)
            {
                System.out.println (ex.getMessage ());
            }
        }
    }
}
