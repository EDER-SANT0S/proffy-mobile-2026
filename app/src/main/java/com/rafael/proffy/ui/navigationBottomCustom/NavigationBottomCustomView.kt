package com.rafael.proffy.ui.navigationBottomCustom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ViewCustomBottomNavigationBinding

class NavigationBottomCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ViewCustomBottomNavigationBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var selectedItemId: Int = R.id.containerTeacherList
    var onItemSelectedListener: ((Int) -> Unit)? = null

    init {
        setupListener()
        updateSelection(R.id.containerTeacherList)
    }

    private fun setupListener() {
        binding.containerTeacherList.setOnClickListener {
            updateSelection(R.id.containerTeacherList)
        }
        binding.containerTeacherFavorite.setOnClickListener {
            updateSelection(R.id.containerTeacherFavorite)
        }
    }

    private fun updateSelection(itemId: Int) {
        selectedItemId = itemId

        val items = listOf(binding.containerTeacherList, binding.containerTeacherFavorite)
        val icons = listOf(binding.iconPresentation, binding.iconFavorite)
        val texts = listOf(binding.textProffy, binding.textFavorite)
        val ids = listOf(R.id.containerTeacherList, R.id.containerTeacherFavorite)

        for (i in items.indices) {
            val selected = ids[i] == itemId
            items[i].isSelected = selected

            val color = ContextCompat.getColor(
                context, if (selected) R.color.text_title else R.color.text_input
            )
            val colorIcon = ContextCompat.getColor(
                context, if (selected) R.color.purple else R.color.text_input
            )

            icons[i].setColorFilter(colorIcon)
            texts[i].setTextColor(color)
        }

        onItemSelectedListener?.invoke(itemId)
    }
}