package vertx.pragprog.bookmarks.dao;

import java.util.Collection;

import vertx.pragprog.bookmarks.Bookmark;

public interface BookmarkDao {
	Collection<Bookmark> getAllBookmarks();
	
	Bookmark getBookmark(String id);
	
	String addBookmark(Bookmark bm);
}
