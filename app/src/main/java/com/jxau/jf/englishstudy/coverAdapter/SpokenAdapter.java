package com.jxau.jf.englishstudy.coverAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jxau.jf.englishstudy.R;

import java.util.List;

public class SpokenAdapter extends BaseAdapter {
    private List<String> mList;//数据源
    private LayoutInflater mInflater;//布局装载器对象
    // 通过构造方法将数据源与数据适配器关联起来
    // context:要使用当前的Adapter的界面对象
    public SpokenAdapter(Context context, List<String> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_spoken_title, null);
            viewHolder.title = convertView.findViewById(R.id.spoken_title);
            convertView.setTag(viewHolder);

        }else{//如果缓存池中有对应的view缓存，则直接通过getTag取出viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 取出bean对象
        String title = mList.get(position);

        // 设置控件的数据
        viewHolder.title.setText(title);

        return convertView;
    }
    // ViewHolder用于缓存控件，三个属性分别对应item布局文件的三个控件
    class ViewHolder{
        public TextView title;
    }
}
