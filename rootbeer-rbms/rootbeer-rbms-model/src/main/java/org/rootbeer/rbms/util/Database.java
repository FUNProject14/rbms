package org.rootbeer.rbms.util;

import java.net.URI;
import java.util.ArrayList;


import com.couchbase.client.CouchbaseClient;

public class Database {
	public CouchbaseClient client;
	
	public Database() throws Exception {
		ArrayList<URI> nodes = new ArrayList<URI>();

		// Add one or more nodes of your cluster (exchange the IP with yours)
		nodes.add(URI.create("http://10.6.16.140:8091/pools"));

		// Try to connect to the client
		CouchbaseClient client = new CouchbaseClient(nodes, "rootbeer-rbms", "mahara123");
		
		this.client = client;
	}
	
	public void close() {
		// Shutdown the client
		client.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		Database database = new Database();
		
		// Set your first document with a key of "hello" and a value of "couchbase!"
		database.client.set("hello", "couchbase!").get();

		// Return the result and cast it to string
		String result = (String) database.client.get("hello");
		System.out.println(result);

		database.close();
	}
}
