package com.example.examen2;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExamenAdapter examenAdapter;
    private FloatingActionButton buttonAdd;
    public  List<ExamenModel> examenModelList;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        examenModelList = new ArrayList<>();
        initToList();

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewExamen);
        buttonAdd = findViewById(R.id.buttonAdd);

        //Form title and subtitle
        //titleText = findViewById(R.id.titleText);
        //subtitleText = findViewById(R.id.subtitleText);

        //RecyclerView and Adpater
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        examenAdapter = new ExamenAdapter(examenModelList);
        recyclerView.setAdapter(examenAdapter);

        //Button setOnClinkListener

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("main Activity","view listener button add");
                addItemFromDialog();
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new MyItemTouchHelperCallback(examenAdapter));
        helper.attachToRecyclerView(recyclerView);
    }

    private void addItemFromDialog() {
        final EditText titleText, subtitleText;
        final Button confirmButton;

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_preferences);

        titleText =  dialog.findViewById(R.id.titleText);
        subtitleText = dialog.findViewById(R.id.subtitleText);
        confirmButton = dialog.findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString();
                String subtitle = subtitleText.getText().toString();

                if(title.isEmpty() || subtitle.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Campo vacío",Toast.LENGTH_SHORT).show();
                }else{
                    examenAdapter.addItem(new ExamenModel(title,subtitle),1);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void initToList() {

        examenModelList.add(new ExamenModel("Primera partida","Dora"));
        examenModelList.add(new ExamenModel("Second Game","Dora"));
        examenModelList.add(new ExamenModel("Casi pierdes","Dora"));
        examenModelList.add(new ExamenModel("Nivel 80","Dora"));
        examenModelList.add(new ExamenModel("Guardado en ...","Dora"));
        examenModelList.add(new ExamenModel("Otro intento","Dora"));
        examenModelList.add(new ExamenModel("Nuevo intento","Dora"));
        examenModelList.add(new ExamenModel("Casi casi casi","Dora"));
        examenModelList.add(new ExamenModel("Dora","Dora"));
        examenModelList.add(new ExamenModel("Dora","Dora"));
        examenModelList.add(new ExamenModel("Dora","Dora"));

    }
}

