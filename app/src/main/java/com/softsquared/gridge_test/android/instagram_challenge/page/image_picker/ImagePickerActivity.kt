package com.softsquared.gridge_test.android.instagram_challenge.page.image_picker

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseActivity
import com.softsquared.gridge_test.android.instagram_challenge.base_component.IMAGE_URL
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ActivityImagePickerBinding
import com.softsquared.gridge_test.android.instagram_challenge.page.write_feed.WriteFeedActivity
import com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter.ImageInPickerAdapter
import com.softsquared.gridge_test.android.instagram_challenge.recycler.item_decoration.Grid4ItemDecoration
import kotlinx.coroutines.launch

class ImagePickerActivity : BaseActivity<ActivityImagePickerBinding>(R.layout.activity_image_picker) {
    override val viewModel: ImagePickerViewModel by lazy { ViewModelProvider(this)[ImagePickerViewModel::class.java] }
    private lateinit var feedWriteResult : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        feedWriteResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intent = Intent(this, BaseActivity::class.java)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        viewModel.initRepository(this)

        setButton()
        setRecyclerView()

        lifecycleScope.launch{
            launch {
                viewModel.currentPosition.collect{ currentPosition ->
                    if (currentPosition != -1) {
                        (binding.rvImage.adapter as ImageInPickerAdapter).applyChangeCurrentPosition(currentPosition = currentPosition)
                        Glide.with(baseContext).load(viewModel.imageList[currentPosition].imageUrl).into(binding.ivCurrent)
                    }
                }
            }
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.loadImageListResult.collect{ loadSuccess ->
                        if (loadSuccess) {
                            (binding.rvImage.adapter as ImageInPickerAdapter).applyAllData()
                        }
                    }
                }
            }
        }

        viewModel.loadImageList()
    }

    override fun setButton() {
        binding.tvbtnCancel.setOnClickListener {
            finish()
        }

        binding.tvbtnNext.setOnClickListener {
            val intent = Intent (this, WriteFeedActivity::class.java)
            intent.putExtra(IMAGE_URL, viewModel.imageList[viewModel.currentPosition.value].imageUrl)
            feedWriteResult.launch(intent)
        }
    }

    override fun setRecyclerView() {
        binding.rvImage.layoutManager = GridLayoutManager(this, 4)
        binding.rvImage.adapter = ImageInPickerAdapter(viewModel.imageList) { position ->
            viewModel.changeCurrentPosition(position = position)
        }
        binding.rvImage.addItemDecoration(Grid4ItemDecoration())
    }
}