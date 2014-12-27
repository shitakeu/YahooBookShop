package jp.ksjApp.yahoobookshop;

public class Const {

	public static final boolean _DEBUG_ = true;

	public static final int API_TIMEOUT = 30000;
	
	/**
	 * YahooAPI関連
	 */
	public static final String YAHOO_APP_ID = "dj0zaiZpPTFVVms2TVk5a3pNQSZzPWNvbnN1bWVyc2VjcmV0Jng9NzU-";
	public static final String YAHOO_AFFILIATEI_ID = "onlgl89gRKBbvvpOFlOZ";

	// YahooショッピングカテゴリランキングAPI
	// http://developer.yahoo.co.jp/webapi/shopping/shopping/v1/categoryranking.html
	public static final String YAHOO_SHOP_CATEGORY_RANKING_API_URL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/categoryRanking?affiliate_type=yid&appid="
			+ YAHOO_APP_ID + "&affiliate_id=" + YAHOO_AFFILIATEI_ID;

	// Yahoo商品検索API
	// http://developer.yahoo.co.jp/webapi/shopping/shopping/v1/itemsearch.html
	public static final String YAHOO_SHOP_SEARCH_API_URL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch?affiliate_type=yid&hits=50&appid="
			+ YAHOO_APP_ID + "&affiliate_id=" + YAHOO_AFFILIATEI_ID;

	// 性別タイプ
	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;
	
	// ジャンルID
	public static final String BOOK_GENRE_MAIN = "10002";
	public static final String INTENT_KEY_SEARCH_GENRE_ID = "search_genre_id";
	public static final String INTENT_KEY_SEARCH_GENRE_TITLE = "search_genre_title";

}
