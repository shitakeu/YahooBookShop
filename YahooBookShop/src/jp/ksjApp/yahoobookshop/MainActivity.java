package jp.ksjApp.yahoobookshop;

import jp.ksjApp.yahoobookshop.adapter.MainFragmentStatePagerAdapter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setSoftInputMode(
				LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupView();
	}


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
		
		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MainFragmentStatePagerAdapter(getSupportFragmentManager()));
	}
}
