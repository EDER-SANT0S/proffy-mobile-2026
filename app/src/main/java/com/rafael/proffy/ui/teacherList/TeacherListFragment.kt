package com.rafael.proffy.ui.teacherList

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafael.proffy.R
import com.rafael.proffy.databinding.FragmentTeacherListBinding
import androidx.core.net.toUri

class TeacherListFragment : Fragment() {

    private lateinit var binding: FragmentTeacherListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeacherListBinding.inflate(inflater, container, false)
        val buttonSendContact = binding.buttonSendContact

        buttonSendContact.setOnClickListener {
            val url = "http://api.whatsapp.com/send?phone=+5534999999999"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(url.toUri())
            startActivity(intent)
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeacherListFragment().apply {}
    }
}