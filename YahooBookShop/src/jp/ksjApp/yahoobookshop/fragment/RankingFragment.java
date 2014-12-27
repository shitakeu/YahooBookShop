package jp.ksjApp.yahoobookshop.fragment;

import java.util.ArrayList;

import jp.ksjApp.yahoobookshop.Const;
import jp.ksjApp.yahoobookshop.ItemData;
import jp.ksjApp.yahoobookshop.R;
import jp.ksjApp.yahoobookshop.adapter.RankingAdapter;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class RankingFragment extends Fragment {

	@SuppressWarnings("unused")
	private static final String TAG = RankingFragment.class.getSimpleName();

	private RequestQueue mQueue;
	private GridView mGridView;
	private Point mPoint;
	
	private String mCategoryId;
	
	private String mGender;

	public RankingFragment(String genreId) {
		super();
		mCategoryId = genreId;
	}
	
	public void setGender(int type){
		
		if(type == Const.GENDER_MALE){
			mGender = "male";
		}else if(type == Const.GENDER_FEMALE){
			mGender = "female";
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.ranking_fragment, null);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		 
		final Display disp = getActivity().getWindowManager().getDefaultDisplay();
		mPoint = new Point();
		disp.getSize(mPoint);
		
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

		final String url = createUrl();

		mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
		mQueue.add(new JsonObjectRequest(Method.GET, url, null,
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						JSONObject resultSet;
						try {
							
							ArrayList<ItemData> itemData = new ArrayList<ItemData>();
							resultSet = response.getJSONObject("ResultSet").getJSONObject("0").getJSONObject("Result");
							for (int i = 0; i < 20; i++) {
								final ItemData data = new ItemData();
								final String index = String.valueOf(i);
								JSONObject item = resultSet.getJSONObject(index);
								data.name = item.getString("Name");
								data.url = item.getString("Url");
								data.thumbnailUrl = item.getJSONObject("Image").getString("Medium");
								data.publisherName = item.getJSONObject("Store").getString("Name");
								itemData.add(data);
							}
							
							final RankingAdapter adapter = new RankingAdapter(getActivity().getApplicationContext(), itemData, mQueue, mPoint);
							mGridView.setAdapter(adapter);
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// エラー処理 error.networkResponseで確認
						// エラー表示など
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
		strbuf.append("category_id=" + mCategoryId);
		
		if(TextUtils.isEmpty(mGender) == false){
			strbuf.append("&");
			strbuf.append("gender=" + mGender);
		}

		if(Const._DEBUG_){
			Log.d(TAG, "url : " + strbuf.toString());
		}
		return strbuf.toString();
	}
	

}