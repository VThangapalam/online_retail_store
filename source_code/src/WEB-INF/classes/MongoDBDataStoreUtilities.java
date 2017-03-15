
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import java.util.Arrays;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import com.mongodb.AggregationOutput;


public class MongoDBDataStoreUtilities {
	
	
   static DBCollection reviewsCol;
   public static void addReview( Review review ) {
	System.out.println("into mongo add review");
      try{
			System.out.println("1*****");
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			System.out.println("1");
         // Now connect to your databases
         DB db = mongoClient.getDB( "CustomerReviews" );
        	System.out.println("1");
       reviewsCol = db.getCollection("reviews");
       System.out.println(reviewsCol.toString());
       	System.out.println("3");
          com.mongodb.BasicDBObject test = new BasicDBObject("product",review.getProductname()).
    		   append("category", review.getProductCategory()).
    		   append("price", review.getProductPrice()).
    		   append("manufacturer_name", review.getManufacturerName()).
    		   append("manufacturer_rebate", review.getManufacturerRebate()).
    		   append("retailer_name", review.getRetailerName()).
    		   append("retailer_zip", review.getRetailerZip()).
    		   append("retailer_city", review.getRetailerCity()).
    		   append("retailer_state", review.getRetailerState()).
    		   append("Product On Sale", review.isProductOnsale()).
    		   append("user_id", review.getUserId()).
    		   append("age", review.getUserAge()).
    		   append("gender", review.getGender()).
    		   append("occupation", review.getOccupation()).
    		   append("rating", review.getRating()).
    		   append("review_date", review.getDate()).
    		   append("review_comment", review.getReviewText());
       
       reviewsCol.insert(test);
       
       DBCursor cursor = reviewsCol.find();
       while (cursor.hasNext()) {
          DBObject obj = cursor.next();
          System.out.println("obj "+obj.toString());
         
       }

       
       
   	System.out.println("Done");
			
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
   
   public static List<Review> getProductReview(String productName) {
	   List<Review> reviews= new ArrayList<>();
	   try {
		   DBCollection reviewsCol;
		System.out.println("into view rev mongo");   
		      MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			System.out.println("1 prd 333333"+productName);
         // Now connect to your databases
         DB db = mongoClient.getDB( "CustomerReviews" );
        	System.out.println("1");
       reviewsCol = db.getCollection("reviews");
	   System.out.println("2");
		BasicDBObject allQuery = new BasicDBObject();
   	allQuery.put("product", productName);
   	BasicDBObject fields = new BasicDBObject();
   	fields.put("user_id", 1);
   	fields.put("review_comment", 1);
   	fields.put("rating", 1);
  	fields.put("_id", 0);

   	DBCursor cursor1 = reviewsCol.find(allQuery, fields);
   	while (cursor1.hasNext()) {
   		DBObject obj  = cursor1.next();
   		Review rev = new Review();
		System.out.println(obj.get("user_id"));
   		System.out.println(obj.get("review_comment"));
   		System.out.println(obj.get("rating"));
		rev.setUserId(obj.get("user_id").toString());
		rev.setReviewText(obj.get("review_comment").toString());
		System.out.println("5");

		rev.setRating(obj.get("rating").toString());
		reviews.add(rev);
   		
   	}
	System.out.println("3");
	
   	
	   } catch (Exception e){
		   System.out.println("Exce###"+ e.getMessage());
	   }
	   return reviews;
   }
   
   
   public static List<String> getTopFiveLiked(){
	   List<String> topliked =  new ArrayList<>();
	   try {
		   DBCollection reviewsCol;
		    // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB("CustomerReviews");
        
       reviewsCol = db.getCollection("reviews");
      // System.out.println(reviewsCol.toString());
       
       
       DBObject groupFields= new BasicDBObject("_id", 0);
       groupFields.put("count",new BasicDBObject("$avg","$rating"));
       groupFields.put("_id", "$product");
       DBObject group = new BasicDBObject("$group", groupFields);
       DBObject sort = new BasicDBObject();
       DBObject projectFields= new BasicDBObject("_id", 0);
       projectFields.put("value", "$_id");
       projectFields.put("ReviewValue","$count");
       DBObject project = new BasicDBObject("$project", projectFields);
       sort.put("ReviewValue",-1);
       DBObject orderby=new BasicDBObject("$sort",sort);
       DBObject limit=new BasicDBObject("$limit",5);
       AggregationOutput aggregate= reviewsCol.aggregate(group,project,orderby,limit);
       for (DBObject result: aggregate.results())
       {
		   String temp ="";
    	   System.out.println(result);
    	   System.out.println(result.get("value").toString());
		   temp = temp+result.get("value").toString()+","+result.get("ReviewValue").toString();
		   topliked.add(temp);
		   
       }
       
	   }catch(Exception e){
		   
	   }
	   return topliked;
   }
   
   
   public static List<String> getTopZip(){
	   List<String> topzip =  new ArrayList<>();
	   try {
		    DBCollection reviewsCol;
		         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB("CustomerReviews");
        
       reviewsCol = db.getCollection("reviews");
      // System.out.println(reviewsCol.toString());
       
       
       DBObject groupFields= new BasicDBObject("_id", 0);
       groupFields.put("count",new BasicDBObject("$sum",1));
       groupFields.put("_id", "$retailer_zip");
       DBObject group = new BasicDBObject("$group", groupFields);
       DBObject sort = new BasicDBObject();
       DBObject projectFields= new BasicDBObject("_id", 0);
       projectFields.put("value", "$_id");
       projectFields.put("ReviewValue","$count");
       DBObject project = new BasicDBObject("$project", projectFields);
       sort.put("ReviewValue",-1);
       DBObject orderby=new BasicDBObject("$sort",sort);
       DBObject limit=new BasicDBObject("$limit",5);
       AggregationOutput aggregate= reviewsCol.aggregate(group,project,orderby,limit);
       for (DBObject result: aggregate.results())
       {
		   String temp = "";
    	   System.out.println(result);
    	   System.out.println(result.get("value").toString());
		   temp = temp+result.get("value").toString()+","+result.get("ReviewValue").toString();
		   topzip.add(temp);
       }
     
       
     
       
	   }catch(Exception e){
		   
	   }
	   return topzip;
   }
   
   
   public static List<String> dataAnalytics(Review rev){
	   List<String> res = new ArrayList<>();
	   try {
		   System.out.println("into data Analytics");
		    DBCollection reviewsCol;
		         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB("CustomerReviews");
        
       reviewsCol = db.getCollection("reviews");
      // System.out.println(reviewsCol.toString());
       
		   
		   BasicDBObject allQuery = new BasicDBObject();
		   if(!rev.getProductname().equals("all")){
			   allQuery.put("product",rev.getProductname());
			   System.out.println("prod!!"+rev.getProductname());
		   }
		   if(!rev.getProductCategory().equals("all")){
			    allQuery.put("category",rev.getProductCategory());
		   }
		   if(!rev.getProductPrice().equals(null)){
			   allQuery.put("price",rev.getProductPrice());
		   }
		   
		      if(!rev.getRetailerName().equals(null)){
			   allQuery.put("retailer_name",rev.getRetailerName());
		   }
		   
		      if(!rev.getRetailerZip() .equals(null)){
			   allQuery.put("retailer_zip",rev.getRetailerZip() );
		   }
		   
		   
		      if(!rev.getRetailerCity().equals(null)){
			   allQuery.put("retailer_city",rev.getRetailerCity());
		   }
		   
		   
		         if(!rev.getUserId().equals(null)){
			   allQuery.put("user_id",rev.getUserId());
		   }
		   
		   	BasicDBObject fields = new BasicDBObject();
   	fields.put("user_id", 1);
   	fields.put("review_comment", 1);
   	fields.put("rating", 1);
   	fields.put("product", 1);
   	fields.put("_id", 0);

   	DBCursor cursor1 = reviewsCol.find(allQuery, fields);
   	String temp  = "";
   	
   	while (cursor1.hasNext()) {
   		DBObject obj  = cursor1.next();
   		temp = temp+obj.get("review_comment")+",";
   		temp = temp+obj.get("rating")+",";
   		temp = temp+obj.get("user_id");
   		
   		System.out.println(temp);
   		
   	}
   	
		   
		   
	   }catch(Exception e) {
		   System.out.println("Exception "+e.getMessage());
	   }
	   return res;
   }
   
}