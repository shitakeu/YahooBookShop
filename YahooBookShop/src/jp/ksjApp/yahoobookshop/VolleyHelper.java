package jp.ksjApp.yahoobookshop;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

public class VolleyHelper {

    public static final Object lock = new Object();
    
    public static RequestQueue mQueue;
    
    /**
     * RequestQueueのシングルトン生成
     * @param context アプリケーションコンテキスト
     * @return
     */
    public static RequestQueue getRequestQueue(final Context context) {
        synchronized (lock) {
            if (mQueue == null) {
//            	final Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024); // 10MB cap
//            	final Network network = new BasicNetwork(new HurlStack());
//            	mQueue = new RequestQueue(cache, network);
//            	mQueue.start();
            	
            	mQueue = Volley.newRequestQueue(context);
            }
            return mQueue;
        }
    }
}
