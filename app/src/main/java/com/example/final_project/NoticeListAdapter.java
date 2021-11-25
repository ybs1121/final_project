package com.example.final_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter {
    private Context context;
    private List<Notice> noticeList;

    public NoticeListAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.notice,null);
        TextView num = (TextView) v.findViewById(R.id.num);
        TextView upload_date = (TextView) v.findViewById(R.id.upload_date);
        TextView kind = (TextView) v.findViewById(R.id.kind);

        num.setText(noticeList.get(i).getNum());
        upload_date.setText(noticeList.get(i).getUpload_date());
        kind.setText(noticeList.get(i).getKind());

        v.setTag(noticeList.get(i).getNum());
        return v;
    }


}
