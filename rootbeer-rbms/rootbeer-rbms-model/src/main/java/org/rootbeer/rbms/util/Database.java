package org.rootbeer.rbms.util;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.rootbeer.rbms.logic.GetPostsOutOfBoundsException;
import org.rootbeer.rbms.model.Action;
import org.rootbeer.rbms.model.Picture;
import org.rootbeer.rbms.model.Post;
import org.rootbeer.rbms.model.User;

import static org.rootbeer.rbms.util.Database.getClient;

public class Database {
	private static EnumMap<Bucket, CouchbaseClient> clientMap;

	public enum Bucket {
		ACTION, PICTURE, POST, USER, HELLO,
	}

	static {
		clientMap = new EnumMap<>(Bucket.class);
		ArrayList<URI> nodes = new ArrayList<URI>();

		// Add one or more nodes of your cluster (exchange the IP with yours)
		nodes.add(URI.create("http://10.6.16.140:8091/pools"));

		// Try to connect to the client
		try {
			clientMap.put(Bucket.ACTION, new CouchbaseClient(nodes,
					"rootbeer-rbms-action", "mahara123"));
			clientMap.put(Bucket.PICTURE, new CouchbaseClient(nodes,
					"rootbeer-rbms-picture", "mahara123"));
			clientMap.put(Bucket.POST, new CouchbaseClient(nodes,
					"rootbeer-rbms-post", "mahara123"));
			clientMap.put(Bucket.USER, new CouchbaseClient(nodes,
					"rootbeer-rbms-user", "mahara123"));
			clientMap.put(Bucket.HELLO, new CouchbaseClient(nodes,
					"rootbeer-rbms-hello", "mahara123"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static CouchbaseClient getClient(Bucket key) {
		if (clientMap == null) {
			throw new IllegalStateException("client already closed");
		}
		return clientMap.get(key);
	}
	
	/**
	 * データベースとの接続を切る
	 */
	public static void close() {
		// Shutdown the client
		for (CouchbaseClient client : clientMap.values()) {
			client.shutdown();
		}
		clientMap = null;
	}

	/**
	 * ユーザーをデータベースに格納する
	 * @param userID
	 * @param user
	 */
	public static void addUser(String userID, User user) {
		CouchbaseClient client = getClient(Bucket.USER);
		client.add(userID, ModelUtil.GSON.toJson(user));
	}

	/**
	 * ユーザーをデータベースから探す
	 * @param userID
	 * @return
	 */
	public static User getUser(String userID) {
		CouchbaseClient client = getClient(Bucket.USER);
		Object o = client.get(userID);
		if (o == null)
			return null;
		return ModelUtil.GSON.fromJson(o.toString(), User.class);
	}

	/**
	 * ポストをデータベースに格納する
	 * @param authUserID
	 * @param post
	 */
	public static void addPost(Post post) {
		String authorUserID = post.getAuthorUserId();
		CouchbaseClient client = getClient(Bucket.POST);
		Object o = client.get(authorUserID);
		Post[] posts;
		if (o == null) {
			posts = new Post[] { post };
			client.add(authorUserID, ModelUtil.GSON.toJson(posts));
		} else {
			posts = ModelUtil.GSON.fromJson(o.toString(),Post[].class);

			Post[] newPosts = new Post[posts.length + 1];
			newPosts[0] = post;
			for (int i = 1; i <= posts.length; ++i)
				newPosts[i] = posts[i-1];
			
			client.replace(authorUserID, ModelUtil.GSON.toJson(newPosts));
		}
	}

	/**
	 * ポストをデータベースから探す
	 * @param authorUserID
	 * @param srcPosition 取得範囲の開始値
	 * @param destPosition 取得範囲の終了値
	 * @throws GetPostsOutOfBoundsException 取得範囲の開始値がポストの総数を超えていたときに投げます
	 * @return returnPosts
	 */
	public static Post[] getPosts(String authorUserID, int srcPosition, int destPosition) throws GetPostsOutOfBoundsException{
		CouchbaseClient client = getClient(Bucket.POST);
		Object o = client.get(authorUserID);
		if (o == null)
			return null;
		Post[] getPosts = ModelUtil.GSON.fromJson(o.toString(), Post[].class);
		if (getPosts.length < srcPosition)
			throw new GetPostsOutOfBoundsException();
		
		int length = destPosition - srcPosition;
		if(getPosts.length - srcPosition < length){
			length = getPosts.length - srcPosition;
		}
		Post[] returnPosts = new Post[length];
		System.arraycopy(getPosts, srcPosition, returnPosts, 0, length);
		return returnPosts;
	}
	
	/**
	 * ピクチャーをデータベースに格納する
	 * @param picture
	 */
	public static void addPicture(Picture picture) {
		String authorUserID = picture.getAuthorUserId();
		CouchbaseClient client = getClient(Bucket.PICTURE);
		Object o = client.get(authorUserID);
		Picture[] pictures;
		if (o == null) {
			pictures = new Picture[] { picture };
			client.add(authorUserID, ModelUtil.GSON.toJson(pictures));
		} else {
			pictures = ModelUtil.GSON.fromJson(o.toString(),Picture[].class);

			Picture[] newPictures = new Picture[pictures.length + 1];
			for (int i = 0; i < pictures.length; ++i)
				newPictures[i] = pictures[i];
			newPictures[pictures.length] = picture;

			client.replace(authorUserID, ModelUtil.GSON.toJson(newPictures));
		}
	}
	
	/**
	 * ピクチャーをデータベースから探す
	 * @param authorUserID
	 * @return
	 */
	public static Picture[] getPictures(String authorUserID) {
		CouchbaseClient client = getClient(Bucket.PICTURE);
		Object o = client.get(authorUserID);
		if (o == null)
			return null;
		return ModelUtil.GSON.fromJson(o.toString(), Picture[].class);
	}

        /**
	 * アクションをデータベースに追加する
	 * @param action
	 */
	public static void addAction(Action action){
		String actorUserID = action.getActorUserId();
		CouchbaseClient client = getClient(Bucket.ACTION);
		Object o = client.get(actorUserID);
		Action[] actions;
		if(o == null){
			actions = new Action[] {action};
                        client.add(actorUserID, ModelUtil.GSON.toJson(actions));
		} else {
			actions = ModelUtil.GSON.fromJson(o.toString(), Action[].class);
			
			Action[] newActions = new Action[actions.length + 1];
			for (int i = 0; i < actions.length; ++i)
				newActions[i] = actions[i];
			newActions[actions.length] = action;

			client.replace(actorUserID, ModelUtil.GSON.toJson(newActions));
		}	
	}
	
	/**
	 * アクションをデータベースから探す
	 * @param actorUserID
	 * @return
	 */
	public static Action[] getActions(String actorUserID){
		CouchbaseClient client = getClient(Bucket.ACTION);
		Object o = client.get(actorUserID);
		if(o == null)
			return null;
		return ModelUtil.GSON.fromJson(o.toString(), Action[].class);
	}

	/**
	 * すべてのユーザーのユーザーIDを返す
	 * @return ユーザーIDの配列
	 */
    public static String[] getUserIDs() {
    	//TODO:issue #95
		CouchbaseClient client = getClient(Bucket.USER);
		View view = client.getView("dev_userids",  "list_userids");
		Query query = new Query();
		query.setStale(Stale.FALSE);
		ViewResponse viewResponse = client.query(view, query);
		ArrayList<String> keys = new ArrayList<String>();
		for (ViewRow vr : viewResponse){
			keys.add(vr.getKey());
		}
		return keys.toArray(new String[0]);
		
	    /* 対応するビューの定義は、Couchbase ServerのWebUIから
	     * [views]タブ
	     * バケツとして[rootbeer-rbms-user]を選ぶ
	     * [Development views]を押す
	     * [Create Development view]ダイアログ
	     * [Design Documument Name]に_design/dev_useridsを、[View Name]にlist_useridsを記入し、[Save]を押す
	     * [Edit]を押す
	     * [VIEW CODE]に以下を記入
	     * function (doc, meta) {
	     *   if (meta.type === "json") {
	     *     emit(meta.id, null);
	     *   }
	     * }
	     */
    }
    
    public static void deleteTestUserData(){
    	String[] userIds = getUserIDs();
    	Pattern p = Pattern.compile("^@");
    	for(String userId : userIds){
    		Matcher m = p.matcher(userId);
    		if(m.find()){
    			getClient(Bucket.ACTION).delete(userId);
    			getClient(Bucket.PICTURE).delete(userId);
    			getClient(Bucket.POST).delete(userId);
    			getClient(Bucket.USER).delete(userId);
    		}
    	}
    }
	public static void main(String[] args) throws Exception {
		// Set your first document with a key of "hello" and a value of
		// "couchbase!"
		CouchbaseClient client = Database.getClient(Database.Bucket.HELLO);
		client.set("hello", "couchbase!");

		// Return the result and cast it to string
		String result = (String) client.get("hello");
		System.out.println(result);

		Database.close();
	}
}
