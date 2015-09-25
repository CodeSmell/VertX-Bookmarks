package vertx.pragprog.bookmarks.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import vertx.pragprog.bookmarks.BookmarksVerticle;
import vertx.pragprog.bookmarks.dao.BookmarkDao;
import vertx.pragprog.bookmarks.dao.BookmarkNoDatabaseDao;

@Configuration
@PropertySource("classpath:/dataAccess.config")
public class SpringConfig {
    @Autowired
    Environment env;

    @Bean @Scope("singleton")
    public BookmarkDao databaseDao() {
        return new BookmarkNoDatabaseDao();
    }

    @Bean @Scope("singleton")
    public BookmarksVerticle bookmarkVerticle() {
        return new BookmarksVerticle();
    }    

}
