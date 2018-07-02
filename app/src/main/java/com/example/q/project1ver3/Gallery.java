package com.example.q.project1ver3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Gallery extends Fragment {
    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img5",
            "Img6",
            "Img7",
            "Img8",
            "Img9",
            "Img10",
            "Img11",
            "Img12",
            "Img13",
    };
    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
    };
    public class CreateList {
        private String image_title;
        private Integer image_id;
        public String getImage_title() {
            return image_title;
        }
        public void setImage_title(String android_version_name) {
            this.image_title = android_version_name;
        }
        public Integer getImage_ID() {
            return image_id;
        }
        public void setImage_ID(Integer android_image_url) {
            this.image_id = android_image_url;
        }
    }
    private ArrayList<CreateList> prepareData(){

        ArrayList<CreateList> theimage = new ArrayList<>();
        for(int i = 0; i< image_titles.length; i++){
            CreateList createList = new CreateList();
            createList.setImage_title(image_titles[i]);
            createList.setImage_ID(image_ids[i]);
            theimage.add(createList);
        }
        return theimage;
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<CreateList> galleryList;
        private Context context;

        public MyAdapter(Context context, ArrayList<CreateList> galleryList) {
            this.galleryList = galleryList;
            this.context = context;
        }
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, final int i) {
            viewHolder.title.setText(galleryList.get(i).getImage_title());
            viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewHolder.img.setImageResource((galleryList.get(i).getImage_ID()));

            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    //Toast.makeText(context,galleryList.get(i).getImage_ID(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, EnlargeImage.class);
                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                    //Bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                    byte[] byteArray = bStream.toByteArray();
                    intent.putExtra("picture_id", byteArray);
                    //Bitmap image = v.getDrawingCache();
                    //Bundle extras = new Bundle();
                    //extras.putParcelable("picture_id", image);
                    //intent.putExtras(extras);
                    startActivity(intent);
                    //Toast.makeText(context,"You just clicked image!",Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public int getItemCount() {
            return galleryList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView title;
            private ImageView img;
            public ViewHolder(View view) {
                super(view);
                title = (TextView)view.findViewById(R.id.title);
                img = (ImageView) view.findViewById(R.id.img);
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.gallery, container, false);
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext().getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<CreateList> createLists = prepareData();
        MyAdapter adapter = new MyAdapter(getContext(), createLists);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}