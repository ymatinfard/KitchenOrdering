package com.matinfard.kitchenordering.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.matinfard.kitchenordering.R

/**
 * Host activity of application. It is a single activity that hosts application fragments.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
