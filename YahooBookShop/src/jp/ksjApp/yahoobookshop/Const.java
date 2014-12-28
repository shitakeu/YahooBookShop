package jp.ksjApp.yahoobookshop;

public class Const {

	public static final boolean _DEBUG_ = true;

	public static final int API_TIMEOUT = 30000;
	
	/**
	 * YahooAPI関連
	 */
	public static final String YAHOO_APP_ID = "dj0zaiZpPTFVVms2TVk5a3pNQSZzPWNvbnN1bWVyc2VjcmV0Jng9NzU-";
	public static final String YAHOO_AFFILIATEI_ID = "onlgl89gRKBbvvpOFlOZ";

	// ※ランキングAPIに不具合があったためこちらは使わない
	// YahooショッピングカテゴリランキングAPI
	// http://developer.yahoo.co.jp/webapi/shopping/shopping/v1/categoryranking.html
//	public static final String YAHOO_SHOP_CATEGORY_RANKING_API_URL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/categoryRanking?affiliate_type=yid&appid="
//			+ YAHOO_APP_ID + "&affiliate_id=" + YAHOO_AFFILIATEI_ID;
	
	public static final String YAHOO_SHOP_CATEGORY_RANKING_API_URL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?affiliate_type=yid&hits=50&image_size=600&appid="
			+ YAHOO_APP_ID + "&affiliate_id=" + YAHOO_AFFILIATEI_ID;
	

	// Yahoo商品検索API
	// http://developer.yahoo.co.jp/webapi/shopping/shopping/v1/itemsearch.html
	public static final String YAHOO_SHOP_SEARCH_API_URL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch?affiliate_type=yid&hits=50&appid="
			+ YAHOO_APP_ID + "&affiliate_id=" + YAHOO_AFFILIATEI_ID;

	// 性別タイプ
	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;
	
	// ジャンルID
	//　総合
	public static final String BOOK_GENRE_MAIN = "10002";
	//　コミック
	public static final String BOOK_GENRE_COMIC = "10251";
	//　文芸
	public static final String BOOK_GENRE_LITERATURE = "12492";
	//　雑誌
	public static final String BOOK_GENRE_MAGAZINE = "10003";
	//　写真集
	public static final String BOOK_GENRE_PHOTO = "10141";
	//　ビジネス
	public static final String BOOK_GENRE_BUSINESS = "10725";
	//　コンピュータ
	public static final String BOOK_GENRE_COMPUTER = "10604";
	
	//　エンターテインメント
	public static final String BOOK_GENRE_ENTERTAINMENT = "10149";
	//生活
	public static final String BOOK_GENRE_LIFE = "12243";
	//趣味
	public static final String BOOK_GENRE_HOBBY = "11998";
	//歴史、心理、教育
	public static final String BOOK_GENRE_HISTORY = "13190";
	//学習参考書
	public static final String BOOK_GENRE_LEARNING = "11270";
	
	
	public static final String INTENT_KEY_SEARCH_GENRE_ID = "search_genre_id";
	public static final String INTENT_KEY_SEARCH_GENRE_TITLE = "search_genre_title";

}
