package redroosterdevelopment.vertxdemo;

import java.time.Instant;
import java.util.Base64;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import redroosterdevelopment.vertxdemo.api.V1;
import redroosterdevelopment.vertxdemo.db.Pool;

public class MainVerticle extends AbstractVerticle {
	
	private void logRequest(HttpServerRequest request) {
		String method = request.method().toString();
		String path = request.path();
		String timestamp = Instant.now().toString();
		String id = new String(Base64.getEncoder().encode((timestamp+request.hashCode()).getBytes()));
		String contentType = request.getHeader("Content-Type");
		contentType = contentType != null ? contentType : "*";
		System.out.println("[timestamp: "+timestamp+"][requestId:"+id+"] Starting " + method + "@" + path + " as " + contentType);
	}

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		Pool.initPool(vertx);
		Router router = Router.router(vertx);
		V1 api = new V1();
		
		
		api.getEndpoints().stream().forEach( (endpoint -> {
			router.route(endpoint.getMethod(), endpoint.getPath()).handler( routingContext -> {
				logRequest(routingContext.request());
				endpoint.handle(routingContext);
			});
		}));
		
		vertx.createHttpServer()
			.requestHandler(router::accept)
			.listen(config().getInteger("http.port", 8080));
	}
}
