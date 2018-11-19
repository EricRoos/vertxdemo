package redroosterdevelopment.vertxdemo.api;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public interface Endpoint extends Handler<RoutingContext>{
	public String getPath();
	public HttpMethod getMethod();
}
