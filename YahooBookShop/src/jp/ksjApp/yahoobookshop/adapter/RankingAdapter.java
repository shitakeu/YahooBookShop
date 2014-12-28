package jp.ksjApp.yahoobookshop.adapter;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import jp.ksjApp.yahoobookshop.ItemData;
import jp.ksjApp.yahoobookshop.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.util.LruCache;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RankingAdapter extends BaseAdapter {
	private ArrayList<ItemData> mItemData;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private Point mPoint;

	public RankingAdapter(Context context, ArrayList<ItemData> itemData,
			RequestQueue queue) {
		mItemData = itemData;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mImageLoader = new ImageLoader(queue, new ImageLruCache());
	}
	
	public RankingAdapter(Context context, ArrayList<ItemData> itemData,
			RequestQueue queue, Point point) {
		mItemData = itemData;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mImageLoader = new ImageLoader(queue, new ImageLruCache());
		mPoint = point;
	}

	public int getCount() {
		return mItemData.size();
	}

	public Object getItem(int position) {
		return mItemData.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}
	
	public void addItem(ArrayList<ItemData> itemData) {
		mItemData.addAll(itemData);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null || convertView.getId() != position) {
			convertView = mInflater.inflate(R.layout.grid_item, parent, false);

			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.img_thumbnail);
			holder.textView = (TextView) convertView
					.findViewById(R.id.text_name);

			final int imgWidth = (int)mPoint.x / 2;
			holder.imageView.setMinimumWidth(imgWidth);
			holder.imageView.setMinimumHeight(imgWidth);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final ItemData item = (ItemData) mItemData.get(position);
		if (item == null) {
			return convertView;
		}

		// コンテンツの更新
		final String imageUrl = item.thumbnailUrl;
		ImageListener listener = ImageLoader.getImageListener(holder.imageView,
				android.R.drawable.spinner_background /* 表示待ち時の画像 */,
				android.R.drawable.ic_dialog_alert /* エラー時の画像 */);
		mImageLoader.get(imageUrl, listener); /* URLから画像を取得する */

		holder.textView.setText(item.name);
		
		final int height = holder.imageView.getHeight() + holder.textView.getHeight();
		convertView.setMinimumHeight(height);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Uri uri = Uri.parse(item.url);
				final Intent intent = new Intent(
						Intent.ACTION_VIEW, uri);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				v.getContext().startActivity(intent);
			}
		});
		
		return convertView;
	}

	private class ViewHolder {
		ImageView imageView;
		TextView textView;
	}

	/**
     * 画像のキャッシュを行う LruCache
     * ※ 参考: http://qiita.com/gari_jp/items/829a54bfa937f4733e29
     */
    public static class ImageLruCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mMemoryCache;

        public ImageLruCache() {
            int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            int cacheSize = maxMemory / 8;

            mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getByteCount() / 1024;
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mMemoryCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mMemoryCache.put(url, bitmap);
        }
    }
	
}
