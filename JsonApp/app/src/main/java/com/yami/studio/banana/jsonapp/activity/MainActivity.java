package com.yami.studio.banana.jsonapp.activity;

import android.os.AsyncTask;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products = new ArrayList<>();
    private String url = "http://192.168.6.221:81/api/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMain = findViewById(R.id.rv_main);
        MainAdapter adapter = new MainAdapter();
        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(new GridLayoutManager(this, 2));

        new NetworkAsync(adapter, products).execute(url);
//        setJson();
//        adapter.setProducts(products);
    }

//    private void setJson(){
//        String json = getJson();
//        if(!json.equals("")){
//            try {
//
//                JSONObject dataObj = new JSONObject(json);
//                JSONArray dataArr = dataObj.getJSONArray("data");
//
//                for(int i=0; i<dataArr.length(); i++){
//
//                    JSONObject product = dataArr.getJSONObject(i);
//                    JSONObject merchant = product.getJSONObject("merchant");
//                    JSONObject category = product.getJSONObject("category");
//
//                    int id = product.getInt("productId");
//                    String name = product.getString("productName");
//                    String slug = product.getString("productSlug");
//                    int qty = product.getInt("productQty");
//                    String image = product.getString("productImage");
//
//                    Merchant _merchant = createMerchant(merchant);
//                    Category _category = createCategory(category);
//
//                    Product _product = new Product(id, qty, name, slug, image, _merchant, _category);
//                    products.add(_product);
//                }
//
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//        }
//    }

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

    private static Merchant createMerchant(JSONObject obj){
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

    private static Category createCategory(JSONObject obj){
        try {
            int id = obj.getInt("categoryId");
            String name = obj.getString("categoryName");
            return new Category(id, name);

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    private static class NetworkAsync extends AsyncTask<String, Void, String>{

        private WeakReference<List<Product>> products;
        private WeakReference<MainAdapter> adapter;

        NetworkAsync(MainAdapter adapter, List<Product> products){
            this.adapter = new WeakReference<>(adapter);
            this.products = new WeakReference<>(products);
        }

        @Override
        protected String doInBackground(String... strings) {
            String json = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection conn = null;

                try{
                    conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    InputStream in = conn.getInputStream();

                    StringBuilder stringBuilder = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    json = stringBuilder.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(conn != null){
                        conn.disconnect();
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return json;
        }

        @Override
        protected void onPostExecute(String s) {

            if(!s.equals("")){
                try {

                    JSONObject dataObj = new JSONObject(s);
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
                        products.get().add(_product);
                    }

                    adapter.get().setProducts(products.get());

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        }
    }
}
