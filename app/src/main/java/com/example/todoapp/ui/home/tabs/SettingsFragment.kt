package com.example.todoapp.ui.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSettingsBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import java.util.Locale

class SettingsFragment:Fragment (){
    lateinit var languageSpinner: Spinner
    lateinit var modeSpinner: Spinner
    lateinit var viewBinding: FragmentSettingsBinding
    lateinit var languageAdapter:ArrayAdapter<String>
    lateinit var modeAdapter:ArrayAdapter<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=FragmentSettingsBinding.inflate(inflater,container,false)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        languageSpinner=view.findViewById(R.id.language_spinner)
        modeSpinner=view.findViewById(R.id.mode_spinner)

        languageSpinner.onItemSelectedListener=object :OnItemSelectedListener,
            AdapterView.OnItemSelectedListener {


            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position==0){}
                else if (position==1){
                    //AppCompactDelegate
                    val local=LocaleListCompat.create(Locale("en"))
                    AppCompatDelegate.setApplicationLocales(local)
                }
                else if (position==2){
                    val local=LocaleListCompat.create(Locale("ar"))
                    AppCompatDelegate.setApplicationLocales(local)                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                TODO("Not yet implemented")
            }
        }

        //mode onSelected
        modeSpinner.onItemSelectedListener=object :OnItemSelectedListener,
            AdapterView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position==0){}
                else if (position==1){
                    //AppCompactDelegate
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                else if (position==2){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}