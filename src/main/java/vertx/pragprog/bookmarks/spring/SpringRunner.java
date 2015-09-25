package vertx.pragprog.bookmarks.spring;

import io.vertx.core.Vertx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import vertx.pragprog.bookmarks.BookmarksVerticle;

public class SpringRunner {

	public static void main(String[] args){
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
	    final Vertx vertx = Vertx.vertx();
	    vertx.deployVerticle(context.getBean(BookmarksVerticle.class));
	}
}
