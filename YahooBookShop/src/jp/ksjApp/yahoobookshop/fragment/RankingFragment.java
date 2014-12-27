package jp.ksjApp.yahoobookshop.fragment;

import jp.ksjApp.yahoobookshop.Const;
import jp.ksjApp.yahoobookshop.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
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

	private static final String TAG = RankingFragment.class.getSimpleName();

	private RequestQueue mQueue;
	
	private TextView mTxt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.ranking_fragment, null);
		mTxt =(TextView) view.findViewById(R.id.test);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		request();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private void request() {

		final String url = Const.YAHOO_SHOP_CATEGORY_RANKING_API_URL;

		mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
		mQueue.add(new JsonObjectRequest(Method.GET, url, null,
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						JSONObject resultSet;
						try {
							resultSet = response.getJSONObject("ResultSet").getJSONObject("0").getJSONObject("Result");
							StringBuffer sb = new StringBuffer();
							for (int i = 0; i < 20; i++) {
								final String index = String.valueOf(i);
								JSONObject item = resultSet.getJSONObject(index);
								
								item.getString("Name");
								item.getString("Url");
								item.getJSONObject("Image").getString("Medium");
								item.getJSONObject("Store").getString("Name");
								
								sb.append(item.getString("Name"));
								sb.append("\n");
							}
							mTxt.setText(sb);
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

}