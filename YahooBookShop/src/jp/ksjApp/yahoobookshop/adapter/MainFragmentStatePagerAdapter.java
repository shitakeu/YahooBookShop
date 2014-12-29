package jp.ksjApp.yahoobookshop.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import jp.ksjApp.yahoobookshop.Const;
import jp.ksjApp.yahoobookshop.fragment.EventFragment;
import jp.ksjApp.yahoobookshop.fragment.RankingFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

	// 表示するカテゴリ
	// ここを編集することで順番や表示カテゴリを編集できる
	private ArrayList<String> mCategoryList = new ArrayList<String>() {
		{
			add("キャンペーン");
			add("総合");
			add("コミック、アニメ");
			add("文芸書籍");
			add("雑誌");
			add("ビジネス");
			add("エンターテインメント");
			add("IT");
			add("生活");
			add("趣味");
			add("写真集");
//			add("歴史、教養");
//			add("学習");
		}
	};

	// カテゴリを管理するMap
	// 新たにカテゴリを追加する際はここに記載する
	private HashMap<String, Fragment> mCategoryMap = new HashMap<String, Fragment>() {
		{
			put("キャンペーン", new EventFragment());
			put("総合", new RankingFragment(Const.BOOK_GENRE_MAIN));
			put("コミック、アニメ", new RankingFragment(Const.BOOK_GENRE_COMIC));
			put("文芸書籍", new RankingFragment(Const.BOOK_GENRE_LITERATURE));
			put("雑誌", new RankingFragment(Const.BOOK_GENRE_MAGAZINE));
			put("写真集", new RankingFragment(Const.BOOK_GENRE_PHOTO));
			put("ビジネス", new RankingFragment(Const.BOOK_GENRE_BUSINESS));
			put("IT", new RankingFragment(Const.BOOK_GENRE_COMPUTER));
			put("エンターテインメント", new RankingFragment(
					Const.BOOK_GENRE_ENTERTAINMENT));
			put("生活", new RankingFragment(Const.BOOK_GENRE_LIFE));
			put("趣味", new RankingFragment(Const.BOOK_GENRE_HOBBY));
			put("歴史、教養", new RankingFragment(Const.BOOK_GENRE_HISTORY));
			put("学習", new RankingFragment(Const.BOOK_GENRE_LEARNING));
		}
	};

	public MainFragmentStatePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		 final String categoryName = mCategoryList.get(i);
		 return mCategoryMap.get(categoryName);
	}

	@Override
	public int getCount() {
		return mCategoryList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mCategoryList.get(position);
	}
}