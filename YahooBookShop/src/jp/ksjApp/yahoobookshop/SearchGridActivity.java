package jp.ksjApp.yahoobookshop;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import jp.ksjApp.yahoobookshop.adapter.RankingAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.TextView;

public class SearchGridActivity  extends Activity {

	@SuppressWarnings("unused")
	private static final String TAG = SearchGridActivity.class.getSimpleName();

	private RequestQueue mQueue;
	private GridView mGridView;
	private Point mPoint;
	
	RankingAdapter mRankingAdapter;
	
	private String mSearchWord;
	
	// スクロール中
	private Boolean mScrolling = false;
	
	private boolean isLoad = false;
	
	// 指定した順位を20位毎に表示
	private int mOffset = 1;
	// APIから取得する件数
	private static final int TOTAL_RESULTS_RETURNED = 50;
	// 最大取得件数　
	private static final int LIMIT_TOTAL_RESULTS = 200;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		
		final TextView searchView = (TextView) findViewById(R.id.searchView);
		final Bundle ext = getIntent().getExtras();
		if (ext != null) {
			mSearchWord = ext.getString(Const.INTENT_KEY_SEARCH_WORD);
			if(TextUtils.isEmpty(mSearchWord)) {
				searchView.setVisibility(View.GONE);
			} else {
				searchView.setVisibility(View.VISIBLE);
				final StringBuffer strbuf = new StringBuffer();
				strbuf.append("『");
				strbuf.append(mSearchWord);
				strbuf.append("』で検索した結果");
				searchView.setText(strbuf);
			}
		} else {
			searchView.setVisibility(View.GONE);
		}
		
		mGridView = (GridView) findViewById(R.id.gridView);
		mGridView.setOnScrollListener(new GridViewOnScrollListener());
		
		final Context context = getApplicationContext();
		mQueue = Volley.newRequestQueue(context);
		
		final Display disp = getWindowManager().getDefaultDisplay();
		mPoint = new Point();
		disp.getSize(mPoint);
		
		mRankingAdapter = new RankingAdapter(context, new ArrayList<ItemData>(), mQueue, mPoint);
		mGridView.setAdapter(mRankingAdapter);
		
		//読み込み中文言の表示
		findViewById(R.id.loaingView).setVisibility(View.VISIBLE);
		
		request();
	}
	
	
	private void request() {

		if(isLoad){
			return;
		}
		
		isLoad = true;
		final String url = createUrl();

		mQueue.add(new JsonObjectRequest(Method.GET, url, null,
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						
						JSONObject resultSet;
						try {
							final ArrayList<ItemData> itemData = new ArrayList<ItemData>();
							resultSet = response.getJSONObject("ResultSet").getJSONObject("0").getJSONObject("Result");
							final int totalResultsReturned = Integer.valueOf(response.getJSONObject("ResultSet").getString("totalResultsReturned"));
							
							for (int i = 0; i < totalResultsReturned; i++) {
								final ItemData data = new ItemData();
								final String index = String.valueOf(i);
								JSONObject item = resultSet.getJSONObject(index);
								Log.e(TAG, item.toString());
								
								data.name = item.getString("Name");
								data.url = item.getString("Url");
								data.thumbnailUrl = item.getJSONObject("ExImage").getString("Url");
								data.publisherName = item.getJSONObject("Store").getString("Name");
								data.price = item.getJSONObject("PriceLabel").getString("DefaultPrice");
								itemData.add(data);
							}
							
							mRankingAdapter.addItem(itemData);
							
							// アダプターにデータ変更の通知
							mRankingAdapter.notifyDataSetChanged();
							// GridViewの再描画
							mGridView.invalidateViews();
							
							//　次回リクエスト時のオフセットを20件進める
							mOffset += TOTAL_RESULTS_RETURNED;
							
							if( LIMIT_TOTAL_RESULTS < mOffset) {
								// 最大件数を超えた場合これ以上リクエストしないようにリスナーを削除する
								mGridView.setOnScrollListener(null);
							}
							
							//読み込み中文言の非表示
							findViewById(R.id.loaingView).setVisibility(View.GONE);
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
						isLoad = false;
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// エラー処理 error.networkResponseで確認
						// エラー表示など
						Log.e(TAG, "onErrorResponse");
						isLoad = false;
					}
				}));
	}
	
	/**
	 * リクエストパラメータを付けてAPIのエントリーポイントを返す
	 * @return urlString 
	 */
	private String createUrl() {
		final StringBuffer strbuf = new StringBuffer();
		strbuf.append(Const.YAHOO_SHOP_CATEGORY_RANKING_API_URL);
		strbuf.append("&");
		strbuf.append("category_id=" + Const.BOOK_GENRE_MAIN);
		strbuf.append("&");
		strbuf.append("offset=" + mOffset);
		
		try {
			final String query = URLEncoder.encode(mSearchWord, "UTF-8") ;
			strbuf.append("&");
			strbuf.append("query=" + query );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if(Const._DEBUG_){
			Log.d(TAG, "url : " + strbuf.toString());
		}
		return strbuf.toString();
	}
	
	
	/*
	 * スクローラーの状態検知
	 */
	public class GridViewOnScrollListener implements OnScrollListener {

		/*
		 * ステータスが変わった時
		 * 
		 * @see
		 * android.widget.AbsListView.OnScrollListener#onScrollStateChanged(
		 * android.widget.AbsListView, int)
		 */
		@Override
		public void onScrollStateChanged(AbsListView paramAbsListView,
				int scrollState) {
			switch (scrollState) {

			// スクロール停止
			case OnScrollListener.SCROLL_STATE_IDLE:

				// decode処理をqueueに登録して開始する記述
				mScrolling = false;

				// アダプターにデータ変更の通知
				mRankingAdapter.notifyDataSetChanged();
				// GridViewの再描画
				mGridView.invalidateViews();

				break;

			// スクロール
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				// decodeのqueueをキャンセルする処理を記述
				mScrolling = true;
				break;

			// フリック
			case OnScrollListener.SCROLL_STATE_FLING:
				// decodeのqueueをキャンセルする処理を記述
				mScrolling = true;
				break;

			default:
				break;
			}
		}

		/*
		 * スクロール中
		 * @see
		 * android.widget.AbsListView.OnScrollListener#onScroll(android.widget
		 * .AbsListView, int, int, int)
		 */
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

			// 現在表示されているリストの末尾番号
			int displayCount = firstVisibleItem + visibleItemCount;

			// 初期でdisplayCountに数値が入ってるのに、totalItemCountが0の場合があるためスクロール中のみ判定するようにする
			if (displayCount < totalItemCount - 25 || !mScrolling) {
				return;
			}

			// APIリクエスト
			request();
		}
	}
}
