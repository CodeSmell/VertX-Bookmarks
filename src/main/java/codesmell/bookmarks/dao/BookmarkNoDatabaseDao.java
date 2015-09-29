package codesmell.bookmarks.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import codesmell.bookmarks.Bookmark;
import codesmell.bookmarks.exceptions.NotFoundException;

public class BookmarkNoDatabaseDao implements BookmarkDao {
	private Map<String, Bookmark> bookmarksMap = new LinkedHashMap<>();
	
	public BookmarkNoDatabaseDao(){
		Bookmark bm = new Bookmark("1", "http://vertx.io", "Vert.x Reactive Framework");
		bookmarksMap.put(bm.getBookmarkId(), bm);

		bm = new Bookmark("2", "http://typesafe.com/", "Typesafe Reactive Framework");
		bookmarksMap.put(bm.getBookmarkId(), bm);
	}
	
	@Override
	public Collection<Bookmark> getAllBookmarks() {
		return bookmarksMap.values();
	}

	@Override
	public Bookmark getBookmark(String id) {
		return bookmarksMap.get(id);
	}

	@Override
	public String addBookmark(Bookmark bm) {
		String bmId = UUID.randomUUID().toString();
		bm.setBookmarkId(bmId);
		bookmarksMap.put(bmId, bm);
		return bmId;
	}

	@Override
	public void updateBookmark(Bookmark bm) {
		String id = bm.getBookmarkId();
		if (bookmarksMap.containsKey(id)){
			bookmarksMap.put(id, bm);
		}
		else {
			// not found
			throw new NotFoundException(id);
		}
	}

	@Override
	public void deleteBookmark(String id) {
		if (bookmarksMap.containsKey(id)){
			bookmarksMap.remove(id);
		}
		else {
			// not found
			throw new NotFoundException(id);
		}
	}
}
