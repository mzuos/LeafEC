package com.EWB_Tonibung.mcbcalculator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.github.chrisbanes.photoview.PhotoView;

// from: https://www.codingdemos.com/pinch-to-zoom-android-imageview-tutorial/

public class Image_gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        
        //INITIALISE ALL THE IMAGE VIEWS

        //Induction generators
        ImageView mIcon_delta = (ImageView) findViewById(R.id.IV_ind_delta);
        ImageView mIcon_star = (ImageView)findViewById(R.id.IV_ind_star);
        ImageView mIcon_caps = (ImageView)findViewById(R.id.IV_ind_caps);
        ImageView mIcon_igc = (ImageView)findViewById(R.id.IV_igc_wiring);
        ImageView mIcon_power1ph = (ImageView)findViewById(R.id.IV_power_1ph);

        //Synchronous generators
        ImageView mIcon_synch_star = (ImageView)findViewById(R.id.IV_synch_star);
        ImageView mIcon_zigzag = (ImageView)findViewById(R.id.IV_zigzag);
        ImageView mIcon_ELC = (ImageView)findViewById(R.id.IV_ELC);

        //Ballast wiring
        ImageView mIcon_ballast_1ph = (ImageView)findViewById(R.id.IV_ballast1ph);
        ImageView mIcon_ballast_3ph_ren = (ImageView)findViewById(R.id.IV_ballast_3ph_ren);
        ImageView mIcon_ballast_3ph_blue = (ImageView)findViewById(R.id.IV_ballast_3ph_blue);

        //Electrical review
        ImageView mIcon_symbols = (ImageView)findViewById(R.id.IV_symbols);
        ImageView mIcon_basics = (ImageView)findViewById(R.id.IV_basics);
        ImageView mIcon_ohm = (ImageView)findViewById(R.id.IV_ohm);
        ImageView mIcon_units = (ImageView)findViewById(R.id.IV_units);
        ImageView mIcon_sp = (ImageView)findViewById(R.id.IV_ser_parall);
        ImageView mIcon_star_delta = (ImageView)findViewById(R.id.IV_star_delta);
        ImageView mIcon_consumer = (ImageView)findViewById(R.id.IV_consumer);
        
        //LOAD AND ROUND THUMBNAIL
        //Induction generators
        Bitmap bitmap_delta = BitmapFactory.decodeResource(getResources(), R.drawable.ind_gen_delta_thumb);
        RoundedBitmapDrawable mDrawable_delta = RoundedBitmapDrawableFactory.create(getResources(), bitmap_delta);
        mDrawable_delta.setCircular(true);
        mIcon_delta.setImageDrawable(mDrawable_delta);

        Bitmap bitmap_star = BitmapFactory.decodeResource(getResources(), R.drawable.ind_gen_star_thumb);
        RoundedBitmapDrawable mDrawable_star = RoundedBitmapDrawableFactory.create(getResources(), bitmap_star);
        mDrawable_star.setCircular(true);
        mIcon_star.setImageDrawable(mDrawable_star);

        Bitmap bitmap_caps = BitmapFactory.decodeResource(getResources(), R.drawable.ind_gen_caps_cw_thumb);
        RoundedBitmapDrawable mDrawable_caps = RoundedBitmapDrawableFactory.create(getResources(), bitmap_caps);
        mDrawable_caps.setCircular(true);
        mIcon_caps.setImageDrawable(mDrawable_caps);

        Bitmap bitmap_igc = BitmapFactory.decodeResource(getResources(), R.drawable.igc_wiring_thumb);
        RoundedBitmapDrawable mDrawable_igc = RoundedBitmapDrawableFactory.create(getResources(), bitmap_igc);
        mDrawable_igc.setCircular(true);
        mIcon_igc.setImageDrawable(mDrawable_igc);

        Bitmap bitmap_power1ph = BitmapFactory.decodeResource(getResources(), R.drawable.power_1ph_thumb);
        RoundedBitmapDrawable mDrawable_power1ph = RoundedBitmapDrawableFactory.create(getResources(), bitmap_power1ph);
        mDrawable_power1ph.setCircular(true);
        mIcon_power1ph.setImageDrawable(mDrawable_power1ph);

        //Synchronous generators
        Bitmap bitmap_synch_star = BitmapFactory.decodeResource(getResources(), R.drawable.synch_star_wired_thumb);
        RoundedBitmapDrawable mDrawable_synch_star = RoundedBitmapDrawableFactory.create(getResources(), bitmap_synch_star);
        mDrawable_synch_star.setCircular(true);
        mIcon_synch_star.setImageDrawable(mDrawable_synch_star);

        Bitmap bitmap_zigzag = BitmapFactory.decodeResource(getResources(), R.drawable.synch_zizzag_thumb);
        RoundedBitmapDrawable mDrawable_zigzag = RoundedBitmapDrawableFactory.create(getResources(), bitmap_zigzag);
        mDrawable_zigzag.setCircular(true);
        mIcon_zigzag.setImageDrawable(mDrawable_zigzag);

        Bitmap bitmap_ELC = BitmapFactory.decodeResource(getResources(), R.drawable.elc_wiring_thumb);
        RoundedBitmapDrawable mDrawable_ELC = RoundedBitmapDrawableFactory.create(getResources(), bitmap_ELC);
        mDrawable_ELC.setCircular(true);
        mIcon_ELC.setImageDrawable(mDrawable_ELC);

        // Ballast load wiring
        Bitmap bitmap_ballast_1ph = BitmapFactory.decodeResource(getResources(), R.drawable.ballast_1ph_thumb);
        RoundedBitmapDrawable mDrawable_ballast_1ph = RoundedBitmapDrawableFactory.create(getResources(), bitmap_ballast_1ph);
        mDrawable_ballast_1ph.setCircular(true);
        mIcon_ballast_1ph.setImageDrawable(mDrawable_ballast_1ph);

        Bitmap bitmap_ballast_3ph_ren = BitmapFactory.decodeResource(getResources(), R.drawable.ballast_3ph_rener_thumb);
        RoundedBitmapDrawable mDrawable_ballast_3ph_ren = RoundedBitmapDrawableFactory.create(getResources(), bitmap_ballast_3ph_ren);
        mDrawable_ballast_3ph_ren.setCircular(true);
        mIcon_ballast_3ph_ren.setImageDrawable(mDrawable_ballast_3ph_ren);

        Bitmap bitmap_ballast_3ph_blue = BitmapFactory.decodeResource(getResources(), R.drawable.ballast_3ph_bluebird_thumb);
        RoundedBitmapDrawable mDrawable_ballast_3ph_blue = RoundedBitmapDrawableFactory.create(getResources(), bitmap_ballast_3ph_blue);
        mDrawable_ballast_3ph_blue.setCircular(true);
        mIcon_ballast_3ph_blue.setImageDrawable(mDrawable_ballast_3ph_blue);


        //Electrical review
        Bitmap bitmap_symbols = BitmapFactory.decodeResource(getResources(), R.drawable.ac_dc_symbols_thumb);
        RoundedBitmapDrawable mDrawable_symbols = RoundedBitmapDrawableFactory.create(getResources(), bitmap_symbols);
        mDrawable_symbols.setCircular(true);
        mIcon_symbols.setImageDrawable(mDrawable_symbols);
        
        Bitmap bitmap_basics = BitmapFactory.decodeResource(getResources(), R.drawable.basics_thumb);
        RoundedBitmapDrawable mDrawable_basics = RoundedBitmapDrawableFactory.create(getResources(), bitmap_basics);
        mDrawable_basics.setCircular(true);
        mIcon_basics.setImageDrawable(mDrawable_basics);

        Bitmap bitmap_ohm = BitmapFactory.decodeResource(getResources(), R.drawable.ohm_thumb);
        RoundedBitmapDrawable mDrawable_ohm = RoundedBitmapDrawableFactory.create(getResources(), bitmap_ohm);
        mDrawable_ohm.setCircular(true);
        mIcon_ohm.setImageDrawable(mDrawable_ohm);

        Bitmap bitmap_units = BitmapFactory.decodeResource(getResources(), R.drawable.units_thumb);
        RoundedBitmapDrawable mDrawable_units = RoundedBitmapDrawableFactory.create(getResources(), bitmap_units);
        mDrawable_units.setCircular(true);
        mIcon_units.setImageDrawable(mDrawable_units);

        Bitmap bitmap_sp = BitmapFactory.decodeResource(getResources(), R.drawable.series_parallel_thumb);
        RoundedBitmapDrawable mDrawable_sp = RoundedBitmapDrawableFactory.create(getResources(), bitmap_sp);
        mDrawable_sp.setCircular(true);
        mIcon_sp.setImageDrawable(mDrawable_sp);

        Bitmap bitmap_star_delta = BitmapFactory.decodeResource(getResources(), R.drawable.star_delta_thumb);
        RoundedBitmapDrawable mDrawable_star_delta = RoundedBitmapDrawableFactory.create(getResources(), bitmap_star_delta);
        mDrawable_star_delta.setCircular(true);
        mIcon_star_delta.setImageDrawable(mDrawable_star_delta);

        Bitmap bitmap_consumer = BitmapFactory.decodeResource(getResources(), R.drawable.consumer_unit_thumb);
        RoundedBitmapDrawable mDrawable_consumer = RoundedBitmapDrawableFactory.create(getResources(), bitmap_consumer);
        mDrawable_consumer.setCircular(true);
        mIcon_consumer.setImageDrawable(mDrawable_consumer);
        

        //SET LISTENERS TO LOAD FULL SIZE IMAGES

        //Induction generators
        mIcon_delta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.ind_gen_delta_comp);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.ind_gen_star_comp);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_caps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.ind_gen_caps_cw_comp);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_igc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.igc_wiring);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

        mIcon_power1ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.power_1ph);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

        //Synchronous generators
        mIcon_synch_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.synch_star_wired);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_zigzag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.sync_zigzag);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_ELC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.elc_wiring);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        //Ballast load wiring
        mIcon_ballast_1ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.ballast_1ph);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_ballast_3ph_ren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.ballast_3ph_renerconsys);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_ballast_3ph_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.ballast_3ph_bluebird);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


        //Electrical review
        mIcon_symbols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.ac_dc_symbols);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_basics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.basics);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_ohm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.ohm);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.units);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.series_parallel);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        mIcon_star_delta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.star_delta_white_large);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });



        mIcon_consumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Image_gallery.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.consumer_unit);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });




    }
}
