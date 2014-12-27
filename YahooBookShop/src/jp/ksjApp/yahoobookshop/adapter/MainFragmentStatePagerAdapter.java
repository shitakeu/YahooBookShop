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
    	// 総合ランキング（男性）
    	RankingFragment maleRankingFragment = new RankingFragment(Const.BOOK_GENRE_MAIN);
    	maleRankingFragment.setGender(Const.GENDER_MALE);
    	return maleRankingFragment;
    case 2:
    	// 総合ランキング（女性）
    	RankingFragment femaleRankingFragment = new RankingFragment(Const.BOOK_GENRE_MAIN);
    	femaleRankingFragment.setGender(Const.GENDER_FEMALE);
    	return femaleRankingFragment;
    case 3:
    	return new RankingFragment(Const.BOOK_GENRE_COMIC);
    case 4:
    	return new RankingFragment(Const.BOOK_GENRE_LITERATURE);
    case 5:
    	return new RankingFragment(Const.BOOK_GENRE_MAGAZINE);
    case 6:
    	return new RankingFragment(Const.BOOK_GENRE_PHOTO);
    case 7:
    	return new RankingFragment(Const.BOOK_GENRE_BUSINESS);
    case 8:
    	return new RankingFragment(Const.BOOK_GENRE_COMPUTER);
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
		  titleName = "男性向け";
		  break;
	  case 2:
		  titleName = "女性向け";
		break;
	  case 3:
		  titleName = "コミック、アニメ";
		break;
	  case 4:
		  titleName = "文芸書籍";
		break;
	  case 5:
		  titleName = "雑誌";
		break;
	  case 6:
		  titleName = "写真集";
		break;
	  case 7:
		  titleName = "ビジネス";
		break;
	  case 8:
		  titleName = "コンピュータ";
		break;
		
	  default:
		break;
	}
	  
    return titleName;
  }

}