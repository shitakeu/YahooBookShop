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
    	return new RankingFragment(Const.BOOK_GENRE_MAIN);
    case 1:
    	RankingFragment maleRankingFragment = new RankingFragment(Const.BOOK_GENRE_MAIN);
    	maleRankingFragment.setGender(Const.GENDER_MALE);
    	return maleRankingFragment;
    default:
    	RankingFragment femaleRankingFragment = new RankingFragment(Const.BOOK_GENRE_MAIN);
    	femaleRankingFragment.setGender(Const.GENDER_FEMALE);
    	return femaleRankingFragment;
    }

  }

  @Override
  public int getCount() {
    return 3;
  }

  @Override
  public CharSequence getPageTitle(int position) {
	  
	  String titleName = "";
	  switch (position) {
	  case 0:
		  titleName = "総合ランキング";
		  break;
	  case 1:
		  titleName = "男性ランキング";
		  break;
	  case 2:
		  titleName = "女性ランキング";
		break;

	  default:
		break;
	}
	  
    return titleName;
  }

}