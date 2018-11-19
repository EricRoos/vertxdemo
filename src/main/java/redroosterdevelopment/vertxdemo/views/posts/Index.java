package redroosterdevelopment.vertxdemo.views.posts;

import java.util.HashMap;
import java.util.List;

import io.vertx.core.json.Json;
import redroosterdevelopment.vertxdemo.models.Post;
import redroosterdevelopment.vertxdemo.views.Viewable;

public class Index implements Viewable{
	private HashMap<String, Object> locals;
	
	
	public Index(HashMap<String, Object> locals) {
		super();
		this.locals = locals;
	}


	@Override
	public String render() {
		@SuppressWarnings("unchecked")
		List<Post> posts = (List<Post>)locals.get("posts");	
		return Json.encode(posts.toArray(new Post[posts.size()]));
	}


	@Override
	public HashMap<String, Object> getLocals() {
		return locals;
	}


	@Override
	public String contentType() {
		return "application/json";
	}

}
