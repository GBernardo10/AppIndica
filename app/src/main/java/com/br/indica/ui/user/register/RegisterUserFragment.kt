package com.br.indica.ui.user.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import com.br.indica.R
import com.br.indica.databinding.FragmentRegisterUserBinding
import com.br.indica.ui.MainActivity
import com.br.indica.view_model.RegisterUserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class RegisterUserFragment : Fragment() {

    private val viewModel: RegisterUserViewModel by viewModels()
    private lateinit var binding: FragmentRegisterUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.fragment_register_user, container, false)
        val view: View = binding.root
        with(binding) {
            viewmodel = viewModel
            lifecycleOwner = this@RegisterUserFragment
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_main).visibility =
            View.INVISIBLE
    }
}
