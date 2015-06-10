//상품을 클릭했을 때 나오는 상세 설명 페이지 액티비티의 레이아웃

package kr.ac.sogang.hangertag;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailViewActivity extends Activity implements View.OnClickListener {

    ImageView itemImage;
    TextView itemDescription;
    Gallery itemGallery;
    Button itemGoBack;
    ImageButton itemOthers1;
    ImageButton itemOthers2;
    ImageButton itemOthers3;
    ArrayList<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);


        itemGallery = (Gallery)findViewById(R.id.ItemGallery);
        itemImage = (ImageView)findViewById(R.id.ItemImage);
        itemDescription = (TextView)findViewById(R.id.ItemDescription);
        //itemGoBack = (Button)findViewById(R.id.ItemGoBack);
        //itemGoBack.setOnClickListener(new View.OnClickListener(){
        //        public void onClick(View v){
        //            finish();
        //        }
        //});
        itemOthers1 = (ImageButton)findViewById(R.id.ibDetail1);
        itemOthers1.setOnClickListener(this);
        itemOthers2 = (ImageButton)findViewById(R.id.ibDetail2);
        itemOthers2.setOnClickListener(this);
        itemOthers3 = (ImageButton)findViewById(R.id.ibDetail3);
        itemOthers3.setOnClickListener(this);

        images = new ArrayList<>();

        Button ItemGoBack = (Button)findViewById(R.id.ItemGoBack);
        ItemGoBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DetailViewActivity.this, SpecifyViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null){
            ItemSet itemSet;
            itemSet = (ItemSet)intent.getSerializableExtra("itemSet");
            itemDescription.setText(itemSet.description);
            //images[0] = IntegeritemSet.imageList.get(0)
            images = itemSet.imageList;
        }


        itemGallery.setAdapter(new GalleryAdapter(this));
        itemGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemImage.setImageResource(images.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    class GalleryAdapter extends BaseAdapter {
        Context context;

        public GalleryAdapter(Context context){
            this.context = context;
        }

        public int getCount(){
            return images.size();
        }

        public Object getItem(int position){
            return images.get(position);
        }

        public long getItemId(int position){
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView image;

            if(convertView == null){
                image = new ImageView(context);
            }
            else {
                image = (ImageView)convertView;
            }

            image.setImageResource(images.get(position));
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            image.setLayoutParams(new Gallery.LayoutParams(200,200));

            return image;
        }

    }

    public void onClick(View v){
        Intent intent = new Intent(DetailViewActivity.this,DetailViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        ItemSet itemSet = new ItemSet();
        if(v.getId()==R.id.ibDetail1) {
            itemSet.description = "1번 상품이다";
            itemSet.imageList.add(R.mipmap.blouson0);
            itemSet.imageList.add(R.mipmap.blouson1);
            itemSet.imageList.add(R.mipmap.blouson2);
            itemSet.imageList.add(R.mipmap.blouson3);
            intent.putExtra("itemSet",itemSet);
        }
        if(v.getId()==R.id.ibDetail2) {
            itemSet.description = "2번 상품이다";
            itemSet.imageList.add(R.mipmap.coat0);
            itemSet.imageList.add(R.mipmap.coat1);
            itemSet.imageList.add(R.mipmap.coat2);
            itemSet.imageList.add(R.mipmap.coat2);
            intent.putExtra("itemSet",itemSet);
        }
        if(v.getId()==R.id.ibDetail3) {
            itemSet.description = "3번 상품이다";
            itemSet.imageList.add(R.mipmap.denim0);
            itemSet.imageList.add(R.mipmap.denim1);
            itemSet.imageList.add(R.mipmap.denim2);
            itemSet.imageList.add(R.mipmap.denim3);
            intent.putExtra("itemSet",itemSet);
        }
        startActivity(intent);

    }

    public void onBackPressed() {
        Intent intent = new Intent(DetailViewActivity.this, SpecifyViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

}
