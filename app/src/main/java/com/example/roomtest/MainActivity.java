package com.example.roomtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button button_insert, button_update, button_clear, button_delete;
    WordViewModel wordViewModel;
    RecyclerView recyclerView;
    MyAdapt myAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        myAdapt = new MyAdapt();
        //此处为一维列表，设置为线性的LinearLayoutManager 。如果是行列式设置为 GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapt);


        wordViewModel =new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getAllWordLive().observe(this, words -> {
            //将观察的数据注入RecycleAdapt中
            myAdapt.setAllWords(words);
            //数据发生改变，调用notifyDataSetChanged刷新视图
            myAdapt.notifyDataSetChanged();
        });


        button_insert = findViewById(R.id.button_insert);
        button_update = findViewById(R.id.button_update);
        button_clear = findViewById(R.id.button_clear);
        button_delete = findViewById(R.id.button_delete);

        button_insert.setOnClickListener(v -> {
            String[] english = {
                    "Hello",
                    "World",
                    "Android",
                    "Google",
                    "Studio",
                    "Project",
                    "Database",
                    "Recycler",
                    "View",
                    "String",
                    "Value",
                    "Integer"
            };
            String[] chinese = {
                    "你好",
                    "世界",
                    "安卓系统",
                    "谷歌公司",
                    "工作室",
                    "项目",
                    "数据库",
                    "回收站",
                    "视图",
                    "字符串",
                    "价值",
                    "整数类型"
            };
            for (int i = 0; i < chinese.length; i++) {
                wordViewModel.insertWords(new Word(english[i],chinese[i]));
                
            }
        });

        button_update.setOnClickListener(v -> {
            Word word1 = new Word();
            word1.setId(5);
            word1.setWord("hey");
            word1.setChineseMeaning("你好");

            Word word2 = new Word();
            word2.setId(6);
            word2.setWord("thank you");
            word2.setChineseMeaning("谢谢");

            wordViewModel.updateWords(word1,word2);
        });

        button_clear.setOnClickListener(v -> {
            wordViewModel.deleteAllWords();
        });

        button_delete.setOnClickListener(v -> {
            Word word1 = new Word();
            word1.setId(7);

            Word word2 = new Word();
            word2.setId(8);

            wordViewModel.deleteWords(word1,word2);
        });
    }
}
