package com.br.indica.ui.user.auth

import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.br.indica.R
import com.br.indica.constants.Constants
import com.br.indica.databinding.FragmentAuthBinding
import com.br.indica.extensions.dimissError
import com.br.indica.ui.MainActivity
import com.br.indica.view_model.AuthViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout


class AuthFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(
            inflater,
            R.layout.fragment_auth, container, false
        )
        val view: View = binding.root
        with(binding) {
            viewmodel = viewModel
            lifecycleOwner = this@AuthFragment
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel?.username?.observe(viewLifecycleOwner, Observer {
            binding.labelUsername.dimissError()
        })

        binding.viewmodel?.password?.observe(viewLifecycleOwner, Observer {
            binding.labelPassword.dimissError()
        })

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_nav_register_user)
        }

        viewModel.authStateEvent.observe(viewLifecycleOwner, Observer { authState ->
            when (authState) {
                is AuthViewModel.AuthState.InvalidAuth -> {
                    val validationFields: Map<String, TextInputLayout> = validationFields()
                    authState.fields.forEach { fieldErr ->
                        validationFields[fieldErr.first]?.error = getString(fieldErr.second)
                    }
                }
                is AuthViewModel.AuthState.ValidAuth -> {
                    binding.labelUsername.error = null
                    binding.labelPassword.error = null
                    findNavController().popBackStack()
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val config = (context as MainActivity)

        with(config) {
            findViewById<BottomNavigationView>(R.id.bottom_nav_main).visibility = View.INVISIBLE
            val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_main)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun validationFields() = mapOf(
        AuthViewModel.INPUT_USERNAME.first to binding.labelUsername,
        AuthViewModel.INPUT_PASSWORD.first to binding.labelPassword
    )


}
