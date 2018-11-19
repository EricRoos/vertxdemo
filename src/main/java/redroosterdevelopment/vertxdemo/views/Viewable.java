package redroosterdevelopment.vertxdemo.views;

import java.util.HashMap;

public interface Viewable {
	public HashMap<String, Object> getLocals();
	public String contentType();
	public String render();
}
