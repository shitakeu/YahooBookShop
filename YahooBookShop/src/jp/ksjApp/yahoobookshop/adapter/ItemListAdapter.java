package jp.ksjApp.yahoobookshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jp.ksjApp.yahoobookshop.Item;
import jp.ksjApp.yahoobookshop.R;


public class ItemListAdapter extends ArrayAdapter<Item> {

    private static final int resource = R.layout.custom_listview;

    public ItemListAdapter(Context context){
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        Item item = getItem(position);

        TextView id = (TextView) view.findViewById(R.id.id);
        id.setText(item.id);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(item.name);

        return view;
    }

    public ArrayList<Item> getItemList() {

        int size = getCount();
        ArrayList<Item> itemList = new ArrayList<Item>(size);
        for (int index = 0; index < size; index++) {
            itemList.add(getItem(index));
        }
        return itemList;
    }


    public void addAll(ArrayList<Item> parcelableArrayList) {
        @SuppressWarnings("unchecked")
        ArrayList<Item> itemList = (ArrayList<Item>) parcelableArrayList;
        super.addAll(itemList);
    }

    public void add(String id, String name) {
        Item item = new Item(id, name);
        super.add(item);
    }

    // çÌèú
    public void remove(int index) {
        if (index < 0 || index >= getCount()) {
            return;
        }
        remove(getItem(index));
    }
}
