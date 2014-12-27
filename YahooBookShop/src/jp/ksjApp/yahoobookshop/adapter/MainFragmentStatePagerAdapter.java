package jp.ksjApp.yahoobookshop.adapter;

import jp.ksjApp.yahoobookshop.fragment.RankingFragment;
import jp.ksjApp.yahoobookshop.fragment.Fragment1;
import jp.ksjApp.yahoobookshop.fragment.Fragment2;
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
      return new RankingFragment();
    case 1:
      return new Fragment1();
    default:
      return new Fragment2();
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
		  titleName = "ランキング";
		  break;
	  case 1:
		  titleName = "テスト①";
		  break;
	  case 2:
		  titleName = "テスト②";
		break;

	  default:
		break;
	}
	  
    return titleName;
  }

}