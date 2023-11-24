package com.example.myapplication

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityMagazineBinding

class Magazine : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: VideoAdaptar
    private var videoList: MutableList<Video> = ArrayList()

    private lateinit var binding: ActivityMagazineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMagazineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager2 = findViewById(R.id.viewPager2)



        videoList.add(
            Video(
                "android.resource://" + packageName + "/" + R.raw.deep_vein,
                resources.getResourceEntryName(R.raw.deep_vein),
                "Description 1"
            )
        )
        videoList.add(
            Video(
                "android.resource://" + packageName + "/" + R.raw.exercices,
                resources.getResourceEntryName(R.raw.exercices),
                "Description 2"
            )
        )
        videoList.add(
            Video(
                "android.resource://" + packageName + "/" + R.raw.ultimate_chicken_sandwich,
                resources.getResourceEntryName(R.raw.ultimate_chicken_sandwich),
                "Description 3"
            )
        )
        videoList.add(
            Video(
                "android.resource://" + packageName + "/" + R.raw.small_sausage_tastes_better,
                resources.getResourceEntryName(R.raw.small_sausage_tastes_better),
                "Description 4"
            )
        )
        videoList.add(
            Video(
                "android.resource://" + packageName + "/" + R.raw.injection,
                resources.getResourceEntryName(R.raw.injection),
                "Description 4"
            )
        )
        videoList.add(
            Video(
                "android.resource://" + packageName + "/" + R.raw.knee_replacement,
                resources.getResourceEntryName(R.raw.knee_replacement),
                "Description 4"
            )
        )
        videoList.add(
            Video(
                "android.resource://" + packageName + "/" + R.raw.shoulder,
                resources.getResourceEntryName(R.raw.shoulder),
                "Description 4"
            )
        )
        videoList.add(
            Video(
                "android.resource://" + packageName + "/" + R.raw.entrainement,
                resources.getResourceEntryName(R.raw.entrainement),
                "Description 4"
            )
        )

        videoList.shuffle()
        adapter = VideoAdaptar(videoList)
        viewPager2.adapter = adapter
    }


}
