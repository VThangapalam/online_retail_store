import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InitServlet
               implements ServletContextListener{

      

  //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("ServletContextListener started calling data store");
		MySQLDataStoreUtilities.createDatabaseandTables();
		MySQLDataStoreUtilities.deleteProductsTable();
		BestDealSerializedDataStore.populateProductTablet();
		BestDealSerializedDataStore.readBestDealDataStore();
		BestDealSerializedDataStore.readBestDealDataStoreUser();
		
		
	}
	
	 @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
		//BestDealSerializedDataStore.writeBestDealDataStore();
    }
}