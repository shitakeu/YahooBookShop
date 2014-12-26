package jp.ksjApp.yahoobookshop;

import jp.ksjApp.yahoobookshop.adapter.MainFragmentStatePagerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupView();
	}

	/**
	 * 
	 */
	private void setupView() {
		final EditText editText = (EditText) findViewById(R.id.edit_word);
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (event != null
						&& event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
					if (event.getAction() == KeyEvent.ACTION_UP) {
						final String searchWord = editText.getText().toString();
//						startSearchItemGridActivity(searchWord);
					}
					return true;
				}
				return false;
			}
		});
		
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(
	      new MainFragmentStatePagerAdapter(
	        getSupportFragmentManager()));
	}
}
