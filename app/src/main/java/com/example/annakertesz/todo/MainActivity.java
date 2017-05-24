package com.example.annakertesz.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<ToDoCard> cardList;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        addBtn = (Button) findViewById(R.id.add_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        cardList = db.getAllCards();
        adapter = new CardAdapter(this, cardList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCards();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoCard card = new ToDoCard("bla bla");
                cardList.add(card);
                db.addCard(card);
                prepareCards();
            }
        });


    }

    private void prepareCards() {
        adapter.notifyDataSetChanged();
    }

}
