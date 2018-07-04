package com.example.q.project1ver3;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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

import com.example.q.project1ver3.database.DatabaseHelper;
import com.example.q.project1ver3.database.model.Contact;
import com.example.q.project1ver3.utils.RecyclerTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyContacts extends Fragment {
    String str =
            "[{'name':'배트맨','phone_number':'010-1111-2222'},"+
                    "{'name':'슈퍼맨','phone_number':'010-1234-8590'},"+
                    "{'name':'앤트맨','phone_number':'019-064-3945'}]";

    //ALL JSON node names
    private static final String TAG_NAME = "name";
    private static final String TAG_PHONE_NUMBER = "phone_number";


    public DatabaseHelper db;
    private mAdapter adapter;
    private ArrayList<Contact> contactList = new ArrayList<>();

    boolean isFABOpen=false;
    FloatingActionButton fab, fab1, fab2;

    private void jsonParsing() {

        contactList = new ArrayList<>();
        try{
            JSONArray jarray = new JSONArray(str);
            for(int i=0; i<jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);
                String name = jObject.getString(TAG_NAME);
                String phone_number = jObject.getString(TAG_PHONE_NUMBER);

                Contact contact = new Contact();
                contact.setName(name);
                contact.setPhone_number(phone_number);

                createContact(name,phone_number);
                contactList.add(contact);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

    }

    public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
        private ArrayList<Contact> contactList;
        private Context context;

        public mAdapter(Context context, ArrayList<Contact> contactList) {
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

        //
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //database initialized
        db = new DatabaseHelper(getActivity());
        //수정필요
        contactList.addAll(db.getAllContacts());

        jsonParsing();

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
                        }
                    });
                }else{
                    closeFABMenu();
                }
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new mAdapter(getContext(), contactList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));

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

                // check if user updating note
                if (shouldUpdate && note != null) {
                    // update note by it's id
                    updateContact(inputName.getText().toString(),inputPhoneNumber.getText().toString(), position);
                } else {
                    // create new note
                    createContact(inputName.getText().toString(), inputPhoneNumber.getText().toString());
                }
            }
        });
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showContactDialog(true, contactList.get(position), position);
                } else {
                    deleteContact(position);
                }
            }
        });
        builder.show();
    }

    private void createContact(String name, String phone_number){

        if(db.isDuplicate(name)){
            return;
        }
        long id = db.insertContact(name,phone_number);

        Contact contact = db.getContact(id);

        if(contact != null){
            contactList.add(0,contact);
            if(adapter == null){

            }
             else{
                adapter.notifyDataSetChanged();
            }

        }
    }
    private void updateContact(String name, String phone_number, int position) {
        Contact contact = contactList.get(position);
        // updating note text
        contact.setName(name);
        contact.setPhone_number(phone_number);

        // updating note in db
        db.updateContact(contact);

        // refreshing the list
        contactList.set(position, contact);
        adapter.notifyItemChanged(position);


    }
    private void deleteContact(int position) {
        // deleting the note from db
        db.deleteContact(contactList.get(position));

        // removing the note from the list
        contactList.remove(position);
        adapter.notifyItemRemoved(position);

    }

}
