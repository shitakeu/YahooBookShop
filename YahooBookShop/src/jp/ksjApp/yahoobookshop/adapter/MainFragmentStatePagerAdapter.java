package jp.ksjApp.yahoobookshop.adapter;

import jp.ksjApp.yahoobookshop.Const;
import jp.ksjApp.yahoobookshop.fragment.RankingFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainFragmentStatePagerAdapter
  extends FragmentStatePagerAdapter {

  public MainFragmentStatePagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int i) {

    switch(i){
    case 0:
    	// 総合ランキング
    	return new RankingFragment(Const.BOOK_GENRE_MAIN);
    case 1:
    	return new RankingFragment(Const.BOOK_GENRE_COMIC);
    case 2:
    	return new RankingFragment(Const.BOOK_GENRE_LITERATURE);
    case 3:
    	return new RankingFragment(Const.BOOK_GENRE_MAGAZINE);
    case 4:
    	return new RankingFragment(Const.BOOK_GENRE_PHOTO);
    case 5:
    	return new RankingFragment(Const.BOOK_GENRE_BUSINESS);
    case 6:
    	return new RankingFragment(Const.BOOK_GENRE_COMPUTER);
    case 7:
    	return new RankingFragment(Const.BOOK_GENRE_ENTERTAINMENT);
    case 8:
    	return new RankingFragment(Const.BOOK_GENRE_LIFE);
    case 9:
    	return new RankingFragment(Const.BOOK_GENRE_HOBBY);
    case 10:
    	return new RankingFragment(Const.BOOK_GENRE_HISTORY);
    case 11:
    	return new RankingFragment(Const.BOOK_GENRE_LEARNING);
    default:
    	return new RankingFragment(Const.BOOK_GENRE_MAIN);
    }

  }

  @Override
  public int getCount() {
    return 9;
  }

  @Override
  public CharSequence getPageTitle(int position) {
	  
	  String titleName = "";
	  switch (position) {
	  case 0:
		  titleName = "総合ランキング";
		  break;
	  case 1:
		  titleName = "コミック、アニメ";
		  break;
	  case 2:
		  titleName = "文芸書籍";
		break;
	  case 3:
		  titleName = "雑誌";
		break;
	  case 4:
		  titleName = "写真集";
		break;
	  case 5:
		  titleName = "ビジネス";
		break;
	  case 6:
		  titleName = "コンピュータ";
		break;
	  case 7:
		  titleName = "エンターテインメント";
		break;
	  case 8:
		  titleName = "生活";
		break;
	  case 9:
		  titleName = "趣味";
		break;
	  case 10:
		  titleName = "歴史、心理、教育";
		break;
	  case 11:
		  titleName = "学習";
		break;
	  default:
		  titleName = "その他";
		break;
	}
	  
    return titleName;
  }

}