package redroosterdevelopment.vertxdemo.api;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public class EndpointFactory {
	public static Endpoint create(HttpMethod method, String path, Handler<RoutingContext> handler) {
		return new Endpoint() {
			@Override
			public void handle(RoutingContext routingContext) {
				handler.handle(routingContext);
			}
			@Override
			public String getPath() {
				return path;
			}

			@Override
			public HttpMethod getMethod() {
				return method;
			}			
		};
	}
}
