package redroosterdevelopment.vertxdemo.controllers;

import java.util.HashMap;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import redroosterdevelopment.vertxdemo.db.Pool;
import redroosterdevelopment.vertxdemo.repositories.PostRepository;
import redroosterdevelopment.vertxdemo.views.Viewable;
import redroosterdevelopment.vertxdemo.views.posts.Index;
import redroosterdevelopment.vertxdemo.views.posts.Show;

public class PostsController extends Controller{
	
	public PostsController() {
		// TODO Auto-generated constructor stub
	}

	public void index(RoutingContext context, Handler<Viewable> viewHandler){
		Pool.getConnection( dbConn -> {
			HashMap<String, Object> returnedContext = new HashMap<String, Object>();
			(new PostRepository()).all(dbConn, posts -> {
				dbConn.close();
				returnedContext.put("posts", posts);
				viewHandler.handle(new Index(returnedContext));
			});

		});		
	}

	public Viewable show(RoutingContext routingContext) {
		String id = routingContext.request().params().get("id");
		HashMap<String, Object> returnedContext = new HashMap<String, Object>();
		//returnedContext.put("post", new Post("post "+id));
		return new Show(returnedContext);
	}
}
