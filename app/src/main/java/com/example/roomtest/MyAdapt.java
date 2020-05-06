package com.example.roomtest;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * RecycleAdapt
 */
public class MyAdapt extends RecyclerView.Adapter<MyAdapt.MyViewHolder> {

    private List<Word> allWords = new ArrayList<>();

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    /**
     * 在适配器中创建 ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemViem = layoutInflater.inflate(R.layout.cell_card,parent,false);
        return new MyViewHolder(itemViem);
    }

    /**
     * 对holder进行数据绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = allWords.get(position);
        holder.textViewNumber.setText(String.valueOf(position+1));
        holder.textViewEnlish.setText(String.valueOf(word.getWord()));
        holder.textViewChinese.setText(String.valueOf(word.getChineseMeaning()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://m.youdao.com/dict?le=eng&q="+holder.textViewEnlish.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    /**
     * 返回holder个数
     * @return
     */
    @Override
    public int getItemCount() {
        return allWords.size();
    }

    /**
     * 自定义 holder对应 item
     * 内部类最好使用static修饰，防止内存泄漏
     */
    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNumber,textViewEnlish,textViewChinese;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewEnlish = itemView.findViewById(R.id.textViewEnlish);
            textViewChinese = itemView.findViewById(R.id.textViewChinese);
        }
    }
}
