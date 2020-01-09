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
import com.github.elwyncrestha.darazreplica.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Elvin Shrestha on 1/10/20
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CustomViewHolder> {

    Context context;
    List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_product, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        final Product product = products.get(position);

        holder.tvProductName.setText(product.getName());
        holder.tvProductPrice.setText(product.getPrice());

        Picasso.get()
                .load(ResourceLoader.getProductImage(product.getImage()))
                .placeholder(R.drawable.ic_launcher_background)
                .resize(220, 220)
                .centerCrop()
                .into(holder.ivProduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName, tvProductPrice;
        ImageView ivProduct;

        public CustomViewHolder(View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvPrice);
            ivProduct = itemView.findViewById(R.id.ivProduct);
        }
    }
}