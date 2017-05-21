import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mindex.gaea.gateway.App;
import com.mindex.gaea.gateway.server.GateWayServer;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		GateWayServer GateWayServer = context.getBean(GateWayServer.class);
		GateWayServer.listen(8080);
	}
}
