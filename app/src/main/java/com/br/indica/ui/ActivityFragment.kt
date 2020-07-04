package com.br.indica.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import com.br.indica.view_model.ActivityViewModel
import com.br.indica.R
import com.br.indica.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {

    private val viewModel: ActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentActivityBinding =
            inflate(inflater,
                R.layout.fragment_activity, container, false)
        val view: View = binding.root
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return view
    }
}
