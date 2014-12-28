package jp.ksjApp.yahoobookshop;

import jp.ksjApp.yahoobookshop.adapter.MainFragmentStatePagerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity implements OnClickListener {
	
	protected static final String TAG = MainActivity.class.getSimpleName();

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
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					startSearchGridActivity();
					return true;
				}
				return false;
			}
		});
		findViewById(R.id.btn_search).setOnClickListener(this);
		
		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MainFragmentStatePagerAdapter(getSupportFragmentManager()));
	}
	
	@Override
	public void onClick(View v) {
		final int id = v.getId();
		if (id == R.id.btn_search) {
			startSearchGridActivity();
		}
	}
	
	private void startSearchGridActivity(){
		
		final EditText editText = (EditText) findViewById(R.id.edit_word);
		final String searchWord = editText.getText().toString();
		final Intent intent = new Intent(MainActivity.this,
				SearchGridActivity.class);
		intent.putExtra(Const.INTENT_KEY_SEARCH_WORD,
				searchWord);
		startActivity(intent);
	}
}
