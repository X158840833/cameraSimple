package com.mrxmgd.camera.simple

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.mrxmgd.orcameralib.CameraActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {

            val intent = Intent(this, CameraActivity::class.java)
            intent.putExtra(
                CameraActivity.KEY_OUTPUT_FILE_PATH,
                Environment.getExternalStorageDirectory().absolutePath + File.separator + System.currentTimeMillis() + ".jpg"
            )
            intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL)
//            intent.putExtra(CameraActivity.KEY_ENABLE_ALBUM, false)
//            intent.putExtra(CameraActivity.KEY_ENABLE_FRONT, false)
            startActivityForResult(intent, 10001)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                10001 -> {
                    Log.e("loge", data?.getStringExtra(CameraActivity.KEY_OUTPUT_FILE_PATH))
                    Glide.with(this)
                        .load(data?.getStringExtra(CameraActivity.KEY_OUTPUT_FILE_PATH))
                        .into(image)
                }
            }
        }

    }
}
