package redroosterdevelopment.vertxdemo.db;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;


public class Pool {
	private static SQLClient instance;
	public static void initPool(Vertx vertx) {
		if(instance == null) {
			JsonObject mySQLClientConfig = new JsonObject().put("host", "0.0.0.0")
					.put("database", "vertxdemo")
					.put("username", "root")
					.put("password", "test");
			
			instance = MySQLClient.createShared(vertx, mySQLClientConfig);
		}		
	}
	public static void getConnection(Handler<SQLConnection> handler) {
		if(instance != null) {		
			instance.getConnection(res -> {				
				if (res.succeeded()) {					
					/*SQLConnection connection = res.result();
					(new PostRepository()).all(connection, (posts) -> {
						System.out.println(posts);
					});*/
					handler.handle(res.result());
				}else {
					System.err.println(res.cause().getMessage());
				}
			});
		}
	}
}
