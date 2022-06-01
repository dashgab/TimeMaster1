package com.example.timemaster;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.timemaster.model.Folder;
import com.example.timemaster.model.Note;

import org.w3c.dom.Text;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder> {

    private List<Folder> folderList;

    public FolderAdapter(List<Folder> folderList)
    {
        this.folderList = folderList;
    }

    @androidx.annotation.NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_folder_list, parent, false);

        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull FolderViewHolder holder, int position) {
        Folder folder = folderList.get(position);
        if (folder != null) {
            holder.folderName_text.setText(folder.getName());
            if (folder.getColor().equals("Желтый"))
                holder.color_text.setBackgroundColor(Color.YELLOW);
            else if (folder.getColor().equals("Красный"))
                holder.color_text.setBackgroundColor(Color.RED);
            else if (folder.getColor().equals("Синий"))
                holder.color_text.setBackgroundColor(Color.BLUE);
            else if (folder.getColor().equals("Зеленый"))
                holder.color_text.setBackgroundColor(Color.GREEN);
        }
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getInstance().getFolderDao().delete(folder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public void addFolderList(List<Folder> folders) {
        folderList = folders;
        notifyDataSetChanged();
    }

    public static class FolderViewHolder extends RecyclerView.ViewHolder {
        TextView folderName_text;
        TextView color_text;
        View delete_button;
        Folder folder;

        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            folderName_text = itemView.findViewById(R.id.folder_text);
            color_text = itemView.findViewById(R.id.color_text);
            delete_button = itemView.findViewById(R.id.delete_folder_button);


        }







    }


}
