package com.github.elwyncrestha.darazreplica.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.github.elwyncrestha.darazreplica.R;
import com.github.elwyncrestha.darazreplica.endpoint.DarazService;
import com.github.elwyncrestha.darazreplica.endpoint.RetrofitUtils;
import com.github.elwyncrestha.darazreplica.model.Collection;
import com.github.elwyncrestha.darazreplica.model.Product;
import com.github.elwyncrestha.darazreplica.ui.adapter.CollectionAdapter;
import com.github.elwyncrestha.darazreplica.ui.adapter.CustomPagerAdapter;
import com.github.elwyncrestha.darazreplica.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final Integer[] slideImage = {
            R.drawable.slider1, R.drawable.slider2, R.drawable.slider3,
            R.drawable.slider4, R.drawable.slider5, R.drawable.slider6
    };
    private static ViewPager viewPager;
    private static int currentPage = 0;
    RecyclerView rvCollection, rvProduct;
    private ArrayList<Integer> pagerDataSet = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));

        rvCollection = view.findViewById(R.id.rvCollection);
        rvProduct = view.findViewById(R.id.rvProduct);

        rvCollection.setNestedScrollingEnabled(false);
        rvProduct.setNestedScrollingEnabled(false);

        pagerDataSet.addAll(Arrays.asList(slideImage));

        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(new CustomPagerAdapter(getActivity(), pagerDataSet));
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == slideImage.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

        getDarazCollections();
        getDarazProducts();

        return view;
    }

    private void getDarazCollections() {
        DarazService darazService = RetrofitUtils.getRetrofit().create(DarazService.class);
        Call<List<Collection>> productModalCall = darazService.getCollection();
        productModalCall.enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                CollectionAdapter collectionAdapter = new CollectionAdapter(getActivity(), response.body());
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                rvCollection.setLayoutManager(horizontalLayoutManager);
                rvCollection.setHasFixedSize(true);
                rvCollection.setAdapter(collectionAdapter);
                collectionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load collection", Toast.LENGTH_SHORT).show();
                Log.e("Collection Load Error", t.getLocalizedMessage(), t);
            }
        });
    }

    private void getDarazProducts() {
        DarazService darazService = RetrofitUtils.getRetrofit().create(DarazService.class);
        Call<List<Product>> productModalCall = darazService.getProduct();
        productModalCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                ProductAdapter productAdapter = new ProductAdapter(getActivity(), response.body());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                rvProduct.setLayoutManager(layoutManager);
                rvProduct.setHasFixedSize(true);
                rvProduct.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load products", Toast.LENGTH_SHORT).show();
                Log.e("Product Load Error", t.getLocalizedMessage(), t);
            }
        });
    }

}
