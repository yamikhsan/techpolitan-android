package com.studio.yami.banana.calculatevolume;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import static com.studio.yami.banana.calculatevolume.MainActivity.EXTRA_NAME;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName;
    private EditText eDomain;

    private PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvName = findViewById(R.id.tv_name);
        eDomain = findViewById(R.id.edit_domain);

        receiverData();

        Button triPrism = findViewById(R.id.triangular_prism);
        Button browser = findViewById(R.id.btn_browser);

        triPrism.setOnClickListener(this);
        browser.setOnClickListener(this);

        packageManager = this.getPackageManager();
    }

    private void receiverData(){
        Intent i = getIntent();
        String name = i.getStringExtra(EXTRA_NAME);
        tvName.setText(name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.triangular_prism:
                onDialog();
                break;
            case R.id.btn_browser:

                String domain = eDomain.getText().toString().trim();
                if(domain.equals("")){
                    eDomain.setError("harus diisi");
                } else {
                    Uri uri = Uri.parse("http://www." + domain);
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);

                    if(i.resolveActivity(packageManager) != null){
                        startActivity(i);
                    } else {
                        Snackbar.make(view, "No Found", Snackbar.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    private void onDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apa anda yakin ingin pindah activity?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent triPrism = new Intent(getApplicationContext(), TriangularPrismActivity.class);
                startActivity(triPrism);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
