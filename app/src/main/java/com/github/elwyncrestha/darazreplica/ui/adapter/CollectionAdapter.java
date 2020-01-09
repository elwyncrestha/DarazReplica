package com.github.elwyncrestha.darazreplica.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.elwyncrestha.darazreplica.R;
import com.github.elwyncrestha.darazreplica.endpoint.ResourceLoader;
import com.github.elwyncrestha.darazreplica.model.Collection;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Elvin Shrestha on 1/10/20
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CustomViewHolder> {

    Context context;
    List<Collection> collections;

    public CollectionAdapter(Context context, List<Collection> collections) {
        this.context = context;
        this.collections = collections;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_collection, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        final Collection collection = collections.get(position);

        holder.tvCollectionTitle.setText(collection.getTitle());
        holder.tvCollectionDescription.setText(collection.getDescription());

        Picasso.get()
                .load(ResourceLoader.getCollectionImage(collection.getImage()))
                .placeholder(R.drawable.ic_launcher_background)
                .resize(180, 180)
                .centerCrop()
                .into(holder.ivCollectionImage);

        Picasso.get()
                .load(ResourceLoader.getCollectionImage(collection.getBackground()))
                .placeholder(R.drawable.background_collection_banner)
                .resize(180, 180)
                .centerCrop()
                .into(holder.ivCollectionBackground);

    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvCollectionTitle, tvCollectionDescription;
        ImageView ivCollectionImage, ivCollectionBackground;

        public CustomViewHolder(View itemView) {
            super(itemView);

            tvCollectionTitle = itemView.findViewById(R.id.collectionTitle);
            tvCollectionDescription = itemView.findViewById(R.id.collectionDes);
            ivCollectionImage = itemView.findViewById(R.id.collectionImage);
            ivCollectionBackground = itemView.findViewById(R.id.background_collection);
        }
    }

}
