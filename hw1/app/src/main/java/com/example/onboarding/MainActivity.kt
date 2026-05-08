package com.example.onboarding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity(),
    Fragment1.Fragment1Listener,
    Fragment2.Fragment2Listener,
    Fragment3.Fragment3Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Fragment1())
                .commit()
        }
    }
    override fun onFragment1Continue(name: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Fragment2.newInstance(name))
            .addToBackStack(null).commit()
    }
    override fun onFragment2Continue(name: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Fragment3.newInstance(name))
            .addToBackStack(null).commit()
    }
    override fun onCheckboxStateChanged(isChecked: Boolean) {}
}