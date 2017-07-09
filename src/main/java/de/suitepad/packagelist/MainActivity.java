package de.suitepad.packagelist;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.suitepad.packagelist.models.Pkg;
import de.suitepad.packagelist.util.Dialog;

public class MainActivity extends AppCompatActivity {

    public static MainActivity getInstance() {
        return instance;
    }

    private static MainActivity instance;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        dialog = Dialog.create(this);

        setContentView(R.layout.activity_main);
        populatePackages();
    }

    public void onPackageUninstalled(String pkgName) {
        dialog.popup(pkgName + R.string.is_uninstalled_message);
        populatePackages();
    }

    public void onPackageInstalled(String pkgName) {
        dialog.popup(pkgName + R.string.is_installed_message);
        populatePackages();
    }

    public void onPackageChanged(String pkgName) {
        dialog.popup(pkgName + R.string.is_changed_message);
        populatePackages();
    }

    private void populatePackages() {
        List<Pkg> pkgList = new ArrayList<>();
        PackageListAdapter packageListAdapter = new PackageListAdapter(this, pkgList);

        ListView listView = (ListView) findViewById(R.id.package_list);
        listView.setAdapter(packageListAdapter);

        for (PackageInfo packageInfo : getPackageManager().getInstalledPackages(
                PackageManager.GET_META_DATA)) {

            pkgList.add(Pkg.create(packageInfo.packageName, packageInfo.packageName,
                    packageInfo.versionName, packageInfo.versionCode));
        }
    }

    class PackageListAdapter extends ArrayAdapter<Pkg> {

        public PackageListAdapter(Context context, List<Pkg> packages) {
            super(context, R.layout.list_item_package, packages);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View currentView = convertView;

            if (currentView == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                currentView = vi.inflate(R.layout.list_item_package, null);
            }
            final Pkg p = getItem(position);
            if (p != null) {
                TextView tt1 = (TextView)currentView.findViewById(R.id.pkg_label);
                tt1.setText(p.getLabel());
                TextView tt2 = (TextView)currentView.findViewById(R.id.pkg_name);
                tt2.setText(p.getName());
                TextView tt3 = (TextView)currentView.findViewById(R.id.pkg_version);
                tt3.setText(p.getVersionName());
                Button btn1 = (Button)currentView.findViewById(R.id.pkg_uninstall);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.popup(R.string.uninstalling_package_message + p.getName());
                    }
                });

                Button btn2 = (Button)currentView.findViewById(R.id.pkg_launch);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.popup(R.string.launching_package_message + p.getName());
                    }
                });
            }

            return currentView;
        }
    }
}
