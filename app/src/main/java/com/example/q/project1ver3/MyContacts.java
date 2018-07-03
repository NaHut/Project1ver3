package com.example.q.project1ver3;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.q.project1ver3.database.model.Contact;

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
                            showContactDialog(false,null,-1);
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
    private void showContactDialog(final boolean shouldUpdate, final Contact note, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity().getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.contact_dialog, null);

        //여기 이상
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
        alertDialogBuilderUserInput.setView(view);

        final EditText inputPhoneNumber = view.findViewById(R.id.note);
        final EditText inputName = view.findViewById(R.id.note_name);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_contact_title) : getString(R.string.lbl_edit_contact_title));

        if (shouldUpdate && note != null) {
            inputPhoneNumber.setText(note.getPhone_number());
            inputName.setText(note.getName());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputPhoneNumber.getText().toString()) &&TextUtils.isEmpty(inputName.getText().toString()) ) {
                    Toast.makeText(v.getContext(), "Enter please!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(inputPhoneNumber.getText().toString())) {
                    Toast.makeText(v.getContext(), "Enter Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(inputName.getText().toString())) {
                    Toast.makeText(v.getContext(), "Enter Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                 else {
                    alertDialog.dismiss();
                }

//                // check if user updating note
//                if (shouldUpdate && note != null) {
//                    // update note by it's id
//                    updateNote(inputNote.getText().toString(), position);
//                } else {
//                    // create new note
//                    createNote(inputNote.getText().toString());
//                }
            }
        });
    }


}
