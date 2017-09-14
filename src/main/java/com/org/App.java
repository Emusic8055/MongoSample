/**
 * 
 */
package com.org;

import java.util.Date;

import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * @author vijay
 *
 */
public class App {
	
	 public static void main(String[] args) throws UnknownHostException {

		    try {

			/**** Connect to MongoDB ****/
			// Since 2.10.0, uses MongoClient
			@SuppressWarnings("resource")
			MongoClient mongo = new MongoClient("localhost", 27017);

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			@SuppressWarnings("deprecation")
			DB db = mongo.getDB("mydb");

			/**** Get collection / table from 'testdb' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("user");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("userid", "springBoot");
			document.put("password", "Boot");
			document.put("createdDate", new Date());
			table.insert(document);

			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("userid", "springBoot");

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			/**** Update ****/
			// search document where name="mkyong" and update it with new values
			BasicDBObject query = new BasicDBObject();
			query.put("userid", "springBoot");

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("userid", "springBoot-updated");

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			table.update(query, updateObj);

			/**** Find and display ****/
			BasicDBObject searchQuery2
			    = new BasicDBObject().append("userid", "springBoot-updated");

			DBCursor cursor2 = table.find(searchQuery2);

			while (cursor2.hasNext()) {
				System.out.println(cursor2.next());
			}

			/**** Done ****/
			System.out.println("Done");

		    } catch (MongoException e) {
			e.printStackTrace();
		    }

		  }

}
