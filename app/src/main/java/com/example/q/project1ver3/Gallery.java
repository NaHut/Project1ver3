package com.example.q.project1ver3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Gallery extends Fragment {
    private final String image_titles[] = {
            "윤소영",
            "전형준",
            "윤소영2",
            "전형준2",
            "Splash_Screen",
            "전형준3",
            "Pizza",
            "Cat",
            "bear",
            "cake",
            "something",
            "Chicken",
            "LoL",
            "marbles",
            "penguin",
            "Sample"
    };
    private final Integer image_ids[] = {
            R.drawable.soyoung,
            R.drawable.heongjun1,
            R.drawable.soyoung2,
            R.drawable.heongjun4,
            R.drawable.sample,
            R.drawable.heongjun3,
            R.drawable.pizza,
            R.drawable.cat,
            R.drawable.bear,
            R.drawable.cake,
            R.drawable.capture,
            R.drawable.chicken,
            R.drawable.lol,
            R.drawable.img14,
            R.drawable.img15,
            R.drawable.img16
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


        public Integer getImage_ID()
        {
            return image_id;
        }

        public void setImage_ID(Integer android_image_url)
        {
            this.image_id = android_image_url;
        }
    }
    private ArrayList<CreateList> prepareData(){

        ArrayList<CreateList> theimage = new ArrayList<>();
        for(int i = 0; i< image_titles.length; i++){
            Bitmap bmp;
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
            final int THUMBSIZE = 64;
            final int id;
            viewHolder.title.setText(galleryList.get(i).getImage_title());
            viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //display only thumbnail versions of image.
            viewHolder.img.setImageResource((galleryList.get(i).getImage_ID()));
            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Integer raw = galleryList.get(i).getImage_ID();
                    Context context = getActivity();
                    Intent intent = new Intent(context, EnlargedImageActivity.class);
                    ByteArrayOutputStream bStream  = new ByteArrayOutputStream();
                    byte[] byteArray = bStream.toByteArray();
                    //intent.putExtra("picture_id", raw);
                    intent.putExtra("picture_id", galleryList.get(i).getImage_ID());
                    //Toast.makeText(context,(galleryList.get(i).getImage_ID()),Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
//                    getActivity().finish();
                    //startActivity(intent);
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

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<CreateList> createLists = prepareData();
        MyAdapter adapter = new MyAdapter(getContext(), createLists);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}