package codesmell.bookmarks;

import org.springframework.util.StringUtils;

/**
 * the Bookmark class from Seven Web Frameworks in Seven Weeks
 */
public class Bookmark {
	private String bookmarkId;
	private String bookmarkUrl;
	private String bookmarkTitle;
	
	public Bookmark(){
	}
	
	public Bookmark(String id, String url, String title){
		this.bookmarkId=id;
		this.bookmarkUrl=url;
		this.bookmarkTitle=title;
	}
	
	public String getBookmarkId() {
		return bookmarkId;
	}
	public void setBookmarkId(String bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	public String getBookmarkUrl() {
		return bookmarkUrl;
	}
	public void setBookmarkUrl(String bookmarkUrl) {
		this.bookmarkUrl = bookmarkUrl;
	}
	public String getBookmarkTitle() {
		return bookmarkTitle;
	}
	public void setBookmarkTitle(String bookmarkTitle) {
		this.bookmarkTitle = bookmarkTitle;
	}
	
	public static boolean isValid(Bookmark bm){
		boolean isValid = false;
		
		if (bm!=null){
			if (!StringUtils.isEmpty(bm.getBookmarkTitle()) &&
			    !StringUtils.isEmpty(bm.getBookmarkUrl())) {
				isValid = true;
			}
		}
		
		return isValid;
	}
}
