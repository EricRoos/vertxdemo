package redroosterdevelopment.vertxdemo;

import java.util.HashMap;

import redroosterdevelopment.vertxdemo.controllers.Controller;

@FunctionalInterface
public interface RespondsWith {
	public HashMap<String, Object> respondWith(Controller controller);
}
