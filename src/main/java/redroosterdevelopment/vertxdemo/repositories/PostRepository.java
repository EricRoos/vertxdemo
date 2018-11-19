package redroosterdevelopment.vertxdemo.repositories;

import java.util.List;
import java.util.stream.Collectors;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.SQLConnection;
import redroosterdevelopment.vertxdemo.models.Post;

public class PostRepository {
	public void all(SQLConnection connection, Handler<List<Post>> resultHandler) {
		connection.query("select * from posts", (result) -> {
			List<JsonArray> results = result.result().getResults();
			List<Post> posts = results.stream()
				.map( json -> this.marshallJson(result.result().getColumnNames(), json))
				.collect(Collectors.toList());
			resultHandler.handle(posts);
		});
	}
	
	public void find(SQLConnection connection, int id, Handler<Post> resultHandler) {
		connection.querySingle("select * from posts where id = " + id + " LIMIT 1", (result) -> {

		});	
	}

	private Post marshallJson(List<String> columns, JsonArray json) {
		Post p = new Post();
		p.setContent(json.getString(columns.indexOf("content")));
		p.setId(json.getInteger((columns.indexOf("id"))));
		return p;
	}
}
