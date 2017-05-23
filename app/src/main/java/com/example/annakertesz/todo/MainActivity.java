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
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        cardList = Arrays.asList(new ToDoCard("Title 1"), new ToDoCard("Title 2"), new ToDoCard("Title 3"));


//        btn = (Button) findViewById(R.id.add_button);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToDoCard card = new ToDoCard("bla bla");
//                db.addCard(card);
//            }
//        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        cardList = db.getAllCards();
        System.out.println("!!! " + cardList);
        adapter = new CardAdapter(this, cardList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCards();
    }

    private void prepareCards() {
        adapter.notifyDataSetChanged();
    }

}
