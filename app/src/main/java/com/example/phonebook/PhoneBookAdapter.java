package com.example.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneBookAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<User> userList;
    public PhoneBookAdapter(Activity activity, ArrayList<User> userList) {
        this.activity=activity;
        this.userList=userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(activity).inflate(R.layout.item_category,parent,false);

        TextView txt1=convertView.findViewById(R.id.item_name);
        TextView txt2=convertView.findViewById(R.id.item_contact);
        User user=userList.get(position);
        int id=user.getId();
        String name=user.getName();
        String contact=user.getContact();

        txt1.setText(""+name);
        txt2.setText(""+contact);

        ImageView menu=convertView.findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                PopupMenu popupMenu = new PopupMenu(activity,menu);

                activity.getMenuInflater().inflate(R.menu.edit_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.item_update)
                        {
                            DBHelper dbHelper=new DBHelper(activity);
                            Intent intent = new Intent(activity,MainActivity.class );
                            intent.putExtra("Id",id);
                            intent.putExtra("Name",name);
                            intent.putExtra("Contact",contact);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                        else if(item.getItemId()==R.id.item_delete)
                        {
                            DBHelper dbHelper=new DBHelper(activity);
                            dbHelper.deleteData(id);
                            userList.remove(position);
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                popupMenu.show();


            }
        });
        return convertView;
    }
}
