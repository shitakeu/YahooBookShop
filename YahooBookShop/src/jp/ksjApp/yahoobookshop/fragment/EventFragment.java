package jp.ksjApp.yahoobookshop.fragment;

import java.util.ArrayList;

import jp.ksjApp.yahoobookshop.Const;
import jp.ksjApp.yahoobookshop.ItemData;
import jp.ksjApp.yahoobookshop.R;
import jp.ksjApp.yahoobookshop.adapter.RankingAdapter;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.AbsListView.OnScrollListener;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class EventFragment extends Fragment {

	@SuppressWarnings("unused")
	private static final String TAG = EventFragment.class.getSimpleName();

	private RequestQueue mQueue;
	private GridView mGridView;
	private Point mPoint;
	
	// スクロール中
	private Boolean mScrolling = false;
	
	RankingAdapter mRankingAdapter;
	
	private boolean isLoad = false;
	
	// 読み込み中文言
	private View mLoaingView;
	
	public EventFragment() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.ranking_fragment, null);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		
		final Context context = getActivity().getApplicationContext();
		mQueue = Volley.newRequestQueue(context);
		
		final Display disp = getActivity().getWindowManager().getDefaultDisplay();
		mPoint = new Point();
		disp.getSize(mPoint);
		
		mRankingAdapter = new RankingAdapter(context, new ArrayList<ItemData>(), mQueue, mPoint);
		mGridView.setAdapter(mRankingAdapter);
		
		//読み込み中文言の表示
		mLoaingView = view.findViewById(R.id.loaingView);
		mLoaingView.setVisibility(View.VISIBLE);
		
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		request();
	}

	@Override
	public void onStop() {
		super.onStop();
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
								data.name = item.getString("Title");
								data.url = item.getString("Url");
								data.thumbnailUrl = item.getJSONObject("Image").getString("Original");
								itemData.add(data);
							}
							
							mRankingAdapter.addItem(itemData);
							
							// アダプターにデータ変更の通知
							mRankingAdapter.notifyDataSetChanged();
							// GridViewの再描画
							mGridView.invalidateViews();
							
							// 読み込み中文言の非表示
							mLoaingView.setVisibility(View.GONE);
						
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
		strbuf.append(Const.YAHOO_SHOP_EVENT_API_URL);

		if(Const._DEBUG_){
			Log.d(TAG, "url : " + strbuf.toString());
		}
		return strbuf.toString();
	}

}