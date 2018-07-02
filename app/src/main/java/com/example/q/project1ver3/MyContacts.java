package com.example.q.project1ver3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Inherited;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MyContacts extends Fragment {


//    String str =
//            "[{'name':'배트맨','phone_number':'010-1111-2222'},"+
//                    "{'name':'슈퍼맨','phone_number':'119'},"+
//                    "{'name':'앤트맨','phone_number':'112'}]";

    private final String name[] = {
            "name1",
            "name2",
            "name3",
            "name4",
    };

    private final String phone_number[] = {
            "010-1111-2222",
            "010-1234-5467",
            "010-2354-1234",
            "010-1234-6593",
    };

    //ALL JSON node names
    private static final String TAG_NAME = "name";
    private static final String TAG_PHONE_NUMBER = "phone_number";

    private ArrayList<mCreateList> contactList;
    private Context contactContext;

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

    private void mPrepareDate() {

        contactList = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            mCreateList mcreateList = new mCreateList();
            mcreateList.setName(name[i]);
            mcreateList.setPhone_number(phone_number[i]);
            contactList.add(mcreateList);
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
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(android.R.id.list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mPrepareDate();
        mAdapter adapter = new mAdapter(getContext(), contactList);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
    //
//    public void parsingJson(String jsonStr){
//        try{
//            JSONArray jarray = new JSONArray(jsonStr);
//            for(int i =0; i<jarray.length(); i++){
//                JSONObject jObject = jarray.getJSONObject(i);
//                String name = jObject.getString(TAG_NAME);
//                String phone_number = jObject.getString(TAG_PHONE_NUMBER);
//
//                HashMap<String,String> map = new HashMap<String, String>();
//                map.put(TAG_NAME,name);
//                map.put(TAG_PHONE_NUMBER,phone_number);
//
//                contactList.add(map);
//            }
//        }catch(JSONException e){
//            e.printStackTrace();
//        }
//    }

