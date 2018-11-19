package redroosterdevelopment.vertxdemo.views.posts;

import java.util.HashMap;

import io.vertx.core.json.Json;
import redroosterdevelopment.vertxdemo.models.Post;
import redroosterdevelopment.vertxdemo.views.Viewable;

public class Show implements Viewable{
	private HashMap<String, Object> locals;
	
	
	public Show(HashMap<String, Object> locals) {
		super();
		this.locals = locals;
	}


	@Override
	public String render() {
		Post post = ((Post)locals.get("post"));	
		return Json.encode(post);
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
