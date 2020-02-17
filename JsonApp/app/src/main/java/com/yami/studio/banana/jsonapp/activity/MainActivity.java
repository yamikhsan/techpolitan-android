package com.yami.studio.banana.jsonapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yami.studio.banana.jsonapp.R;
import com.yami.studio.banana.jsonapp.adapter.MainAdapter;
import com.yami.studio.banana.jsonapp.entity.Category;
import com.yami.studio.banana.jsonapp.entity.Merchant;
import com.yami.studio.banana.jsonapp.entity.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMain = findViewById(R.id.rv_main);
        MainAdapter adapter = new MainAdapter();
        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(new GridLayoutManager(this, 2));

        setJson();
        adapter.setProducts(products);
    }

    private void setJson(){
        String json = getJson();
        if(!json.equals("")){
            try {

                JSONObject dataObj = new JSONObject(json);
                JSONArray dataArr = dataObj.getJSONArray("data");

                for(int i=0; i<dataArr.length(); i++){

                    JSONObject product = dataArr.getJSONObject(i);
                    JSONObject merchant = product.getJSONObject("merchant");
                    JSONObject category = product.getJSONObject("category");

                    int id = product.getInt("productId");
                    String name = product.getString("productName");
                    String slug = product.getString("productSlug");
                    int qty = product.getInt("productQty");
                    String image = product.getString("productImage");

                    Merchant _merchant = createMerchant(merchant);
                    Category _category = createCategory(category);

                    Product _product = new Product(id, qty, name, slug, image, _merchant, _category);
                    products.add(_product);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    private String getJson(){
        String json = "";
        try {
            InputStream is = getResources().openRawResource(R.raw.data);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    private Merchant createMerchant(JSONObject obj){
        try {
            int id = obj.getInt("merchantId");
            String name = obj.getString("merchantName");
            String slug = obj.getString("merchantSlug");
            return new Merchant(id, name, slug);

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    private Category createCategory(JSONObject obj){
        try {
            int id = obj.getInt("categoryId");
            String name = obj.getString("categoryName");
            return new Category(id, name);

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
