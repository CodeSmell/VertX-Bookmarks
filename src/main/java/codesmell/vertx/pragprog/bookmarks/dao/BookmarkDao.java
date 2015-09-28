package codesmell.vertx.pragprog.bookmarks.dao;

import java.util.Collection;

import codesmell.vertx.pragprog.bookmarks.Bookmark;

public interface BookmarkDao {
	Collection<Bookmark> getAllBookmarks();
	
	Bookmark getBookmark(String id);
	
	String addBookmark(Bookmark bm);
	
	void updateBookmark(Bookmark bm);
	
	void deleteBookmark(String id);
}
