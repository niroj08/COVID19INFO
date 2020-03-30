package com.example.covid_19info.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19info.BuildConfig;
import com.example.covid_19info.R;
import com.example.covid_19info.data.model.Country;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SettingFragment extends Fragment {

    private SettingViewModel mViewModel;

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @BindView(R.id.tvAppVersion)
    TextView tvAppVersion;
    private Unbinder unbinder;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, root);

        List<Country> countryList = mViewModel.getCountries();
        ArrayAdapter<Country> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, countryList);
        autoCompleteTextView.setAdapter(dataAdapter);
        autoCompleteTextView.setText(mViewModel.getCurrentCountry());

        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) ->
                mViewModel.saveCountry(parent.getItemAtPosition(position).toString()));

        tvAppVersion.setText(String.format("%s%s\n%s", "App Version ", BuildConfig.VERSION_NAME, getResources().getString(R.string.about)));

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
