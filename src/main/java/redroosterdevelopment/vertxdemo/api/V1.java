package redroosterdevelopment.vertxdemo.api;

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import redroosterdevelopment.vertxdemo.controllers.PostsController;
import redroosterdevelopment.vertxdemo.views.Viewable;

public class V1 implements WebAPI{

	
	public Endpoint viewPostsEndpoint() {
		return EndpointFactory.create(HttpMethod.GET, "/posts", (routingContext) -> {
			HttpServerResponse response = routingContext.response();
			PostsController controller = new PostsController();
			controller.index(routingContext, (viewable) -> {
				System.out.println(viewable.render());
				response.end(viewable.render());
			});		
					
		});
	}
	
	public Endpoint viewPostEndpoint() {
		return EndpointFactory.create(HttpMethod.GET, "/posts/:id", (routingContext) -> {
			HttpServerResponse response = routingContext.response();
			PostsController controller = new PostsController();
			Viewable v = controller.show(routingContext);
			response.end(v.render());		
		});
	}
	
	
	@Override
	public List<Endpoint> getEndpoints() {
		List<Endpoint> endpoints = new ArrayList<Endpoint>();
		endpoints.add(this.viewPostsEndpoint());
		endpoints.add(this.viewPostEndpoint());
		return endpoints;
	}
	
}
