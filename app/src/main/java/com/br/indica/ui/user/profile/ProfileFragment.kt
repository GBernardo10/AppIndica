package com.br.indica.ui.user.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.br.indica.R
import com.br.indica.databinding.FragmentProfileBinding
import com.br.indica.ui.MainActivity
import com.br.indica.view_model.AuthViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.fragment_profile, container, false)
        val view: View = binding.root
        with(binding) {
            authViewmodel = viewModel
            lifecycleOwner = this@ProfileFragment
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.authStateEvent.observe(viewLifecycleOwner, Observer { authState ->
            when (authState) {
                is AuthViewModel.AuthState.ValidAuth -> {
                    (context as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_main).visibility =
                        View.VISIBLE
                }
                is AuthViewModel.AuthState.Unauthenticated -> {
                    findNavController().navigate(R.id.authFragment)
                }
            }
        })
    }

}
