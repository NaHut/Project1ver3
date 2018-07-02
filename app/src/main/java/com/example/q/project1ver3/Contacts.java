package com.example.q.project1ver3;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Contacts extends ListFragment {

    ArrayList<HashMap<String,String>> contactList;

    String str =
            "[{'name':'배트맨','phone_number':'010-1111-2222'},"+
                    "{'name':'슈퍼맨','phone_number':'119'},"+
                    "{'name':'앤트맨','phone_number':'112'}]";

    //ALL JSON node names
    private static final String TAG_NAME = "name";
    private static final String TAG_PHONE_NUMBER = "phone_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.contact, container, false);
        ListView list = (ListView) rootView.findViewById(android.R.id.list);
        contactList = new ArrayList<HashMap<String, String>>();
        parsingJson(str);

        ListAdapter adapter = new SimpleAdapter(
                getActivity(), contactList,
                R.layout.contact_item, new String[] { TAG_NAME, TAG_PHONE_NUMBER },
                new int[] { R.id.name, R.id.phone_number});
        list.setAdapter(adapter);
        return rootView;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub

        Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    public void parsingJson(String jsonStr){
        try{
            JSONArray jarray = new JSONArray(jsonStr);
            for(int i =0; i<jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);
                String name = jObject.getString(TAG_NAME);
                String phone_number = jObject.getString(TAG_PHONE_NUMBER);

                HashMap<String,String>map = new HashMap<String, String>();
                map.put(TAG_NAME,name);
                map.put(TAG_PHONE_NUMBER,phone_number);

                contactList.add(map);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
