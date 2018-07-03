package com.example.q.project1ver3;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyContacts extends Fragment {


    String str =
            "[{'name':'배트맨','phone_number':'010-1111-2222'},"+
                    "{'name':'슈퍼맨','phone_number':'119'},"+
                    "{'name':'앤트맨','phone_number':'112'}]";


    //ALL JSON node names
    private static final String TAG_NAME = "name";
    private static final String TAG_PHONE_NUMBER = "phone_number";

    private ArrayList<mCreateList> contactList;

    boolean isFABOpen=false;
    FloatingActionButton fab, fab1, fab2;

    private class mCreateList {
        private String name;
        private String phone_number;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone_number() {
            return this.phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

    }

    private void jsonParsing() {

        contactList = new ArrayList<>();
        try{
            JSONArray jarray = new JSONArray(str);
            for(int i=0; i<jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);
                String name = jObject.getString(TAG_NAME);
                String phone_number = jObject.getString(TAG_PHONE_NUMBER);

                mCreateList mcreateList = new mCreateList();
                mcreateList.setName(name);
                mcreateList.setPhone_number(phone_number);

                contactList.add(mcreateList);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

    }

    public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
        private ArrayList<mCreateList> contactList;
        private Context context;

        public mAdapter(Context context, ArrayList<mCreateList> contactList) {
            this.contactList = contactList;
            this.context = context;
        }

        @Override
        public mAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(mAdapter.ViewHolder viewHolder, int i) {
            viewHolder.name.setText(contactList.get(i).getName());
            viewHolder.phone_number.setText(contactList.get(i).getPhone_number());
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView phone_number;

            public ViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                phone_number = (TextView) view.findViewById(R.id.phone_number);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.contact, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        jsonParsing();

        mAdapter adapter = new mAdapter(getContext(), contactList);
        recyclerView.setAdapter(adapter);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) rootView.findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                    fab1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
//                            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                                    .setAction("Action", null).show();
                        }
                    });
                }else{
                    closeFABMenu();
                }
            }
        });

        return rootView;
    }

    private void showFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_75));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
    }
    private void closeFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
    }



}
