# karunadakala.java-
package com.example.karunadakalajava3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Adding SuppressLint at the class level to clear all programmatic UI warnings
@SuppressLint({"SetTextI18n", "ViewConstructor"})
public class MainActivity extends AppCompatActivity {

    private static final String COLOR_RED = "#C8102E";
    private static final String COLOR_GOLD = "#FFCD00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDashboard();
    }

    // --- HOME DASHBOARD ---
    private void loadDashboard() {
        LinearLayout layout = createBaseLayout();
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView title = new TextView(this);
        title.setText("Karunada-Kala");
        title.setTextSize(32);
        title.setTextColor(Color.parseColor(COLOR_RED));
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setPadding(0, 40, 0, 10);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        String currentDate = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = new TextView(this);
        dateView.setText("Today: " + currentDate);
        dateView.setTextSize(14);
        dateView.setPadding(0, 0, 0, 60);
        dateView.setGravity(Gravity.CENTER);
        layout.addView(dateView);

        layout.addView(makeButton("Explore Arts", COLOR_GOLD, v -> openExplorer()));
        layout.addView(makeButton("Artisan Map", COLOR_RED, v -> openMap()));
        layout.addView(makeButton("Workshops", COLOR_GOLD, v -> openWorkshops()));
        layout.addView(makeButton("Live Event Feed", COLOR_RED, v -> openEventFeed()));

        setContentView(layout);
    }

    // --- WORKSHOP REGISTRATION ---
    private void openWorkshops() {
        final LinearLayout layout = createBaseLayout();
        addLabel(layout, "Register for Workshops");

        final EditText nameInput = new EditText(this);
        nameInput.setHint("Full Name");
        nameInput.setMinHeight(150);
        layout.addView(nameInput);

        final EditText mobileInput = new EditText(this);
        mobileInput.setHint("Mobile Number");
        mobileInput.setInputType(InputType.TYPE_CLASS_PHONE);
        mobileInput.setMinHeight(150);
        layout.addView(mobileInput);

        Button submitBtn = makeButton("SUBMIT REGISTRATION", COLOR_RED, v -> {
            String name = nameInput.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success: " + name, Toast.LENGTH_SHORT).show();
                loadDashboard();
            }
        });

        layout.addView(submitBtn);
        addBack(layout);
        setContentView(layout);
    }

    // --- EVENT FEED ---
    private void openEventFeed() {
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = createBaseLayout();
        addLabel(layout, "Upcoming Events");

        String[][] events = {
                {"🔥 Hampi Utsav", "Jan 10, 2027"},
                {"🎭 Yakshagana Night", "May 25, 2026"},
                {"🎨 Mysore Art Palace", "Oct 15, 2026"}
        };


        for (String[] event : events) {
            TextView tv = new TextView(this);
            tv.setText(event[0] + " - " + event[1]);
            tv.setPadding(40, 40, 40, 40);
            tv.setBackgroundColor(Color.parseColor("#F0F0F0"));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.setMargins(0, 15, 0, 15);
            tv.setLayoutParams(p);
            layout.addView(tv);
        }

        addBack(layout);
        scrollView.addView(layout);
        setContentView(scrollView);
    }

    // --- EXPLORER & MAP ---
    private void openExplorer() {
        LinearLayout layout = createBaseLayout();
        addLabel(layout, "Artisan Directory");
        String[] arts = {"Yakshagana", "Bidriware", "Silk Weaving"};
        for (String art : arts) {
            layout.addView(makeButton("Call " + art, "#444444", v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9876543210"));
                startActivity(intent);
            }));
        }
        addBack(layout);
        setContentView(layout);
    }

    private void openMap() {
        LinearLayout layout = createBaseLayout();
        addLabel(layout, "Artisan Locations");
        TextView mapText = new TextView(this);
        mapText.setText("\n📍 Mysore\n📍 Bidar\n📍 Channapatna");
        mapText.setTextSize(20);
        layout.addView(mapText);
        addBack(layout);
        setContentView(layout);
    }

    // --- HELPERS ---
    private LinearLayout createBaseLayout() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(50, 50, 50, 50);
        l.setBackgroundColor(Color.WHITE);
        return l;
    }

    private void addLabel(LinearLayout l, String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(24);
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        tv.setPadding(0, 0, 0, 30);
        l.addView(tv);
    }

    private Button makeButton(String text, String color, View.OnClickListener listener) {
        Button b = new Button(this);
        b.setText(text);
        b.setBackgroundColor(Color.parseColor(color));
        b.setTextColor(Color.WHITE);
        b.setOnClickListener(listener);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 160);
        p.setMargins(0, 15, 0, 15);
        b.setLayoutParams(p);
        return b;
    }

    private void addBack(LinearLayout l) {
        TextView space = new TextView(this);
        space.setHeight(80);
        l.addView(space);

        Button b = new Button(this);
        b.setText("BACK TO HOME");
        b.setOnClickListener(v -> loadDashboard());
        l.addView(b);
    }
}
