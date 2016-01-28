package com.example.lijo.cloverboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LIJO on 1/20/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<CardItemModel> cardItems;
    private boolean isLongPressed = false;
    private Context context ;
    public RecyclerAdapter(List<CardItemModel> cardItems){
        this.cardItems = cardItems;
    }









    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        Button power_button;
        ImageButton more;
        public ViewHolder(View itemView) {
            super(itemView);
          /*  this.title = (TextView)itemView.findViewById(R.id.seekBar);*/
            this.content = (TextView)itemView.findViewById(R.id.card_content);
            this.power_button=(Button)itemView.findViewById(R.id.powerbutton);
            this.more=(ImageButton)itemView.findViewById(R.id.morevert);
            this.power_button.setBackgroundResource(R.drawable.off);






        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {





     /*   holder.title.setText(cardItems.get(position).title);*/
        holder.content.setText(cardItems.get(position).content);



        holder.power_button.setOnClickListener(new View.OnClickListener() {
            boolean isOddClicked = true;

            @Override
            public void onClick(View view) {

                if (!isLongPressed) {
                    if (isOddClicked) {
                        holder.power_button.setBackgroundResource(R.drawable.on);
                        isOddClicked = false;

                    } else {
                        holder.power_button.setBackgroundResource(R.drawable.off);
                        isOddClicked = true;
                    }
                } else {
                }


            }
        });

        holder.power_button.setOnLongClickListener(new View.OnLongClickListener() {
            boolean isLongPressed = true;

            @Override
            public boolean onLongClick(View v) {
                if (isLongPressed) {
                    holder.power_button.setBackgroundResource(R.drawable.power);
                    isLongPressed = false;

                } else {

                    isLongPressed = true;
                }
                return true;
            }
        });



        holder.more.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(final View view) {

                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.edit_appliance:
                                Intent i = new Intent(view.getRootView().getContext(), edit_appliances.class);
                                view.getRootView().getContext().startActivity(i);
                                return true;
                            case R.id.schedule_appliance:

                                return true;

                        }
                        return false;
                    }
                });
                popup.show();


            }
        });










    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }
}