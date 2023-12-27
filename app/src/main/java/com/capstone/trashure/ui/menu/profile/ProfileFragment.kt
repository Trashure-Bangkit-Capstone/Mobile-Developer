package com.capstone.trashure.ui.menu.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.trashure.MainActivity
import com.capstone.trashure.databinding.FragmentProfileBinding
import com.capstone.trashure.ui.settings.PersonalInformationActivity
import com.capstone.trashure.ui.settings.SettingsActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private lateinit var sharedPreferences: SharedPreferences

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Initialize sharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // UI Code
        binding.personalInformationBtn.setOnClickListener {
            val intent = Intent(activity, PersonalInformationActivity::class.java)
            startActivity(intent)
        }

        binding.settingsBtn.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.logOutBtn.setOnClickListener {
            // Logout the user
            logoutUser()
        }

        return binding.root
    }

    private fun logoutUser() {
        // Clear login status from SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", false)
        editor.apply()

        // Navigate back to the main activity (assuming ProfileFragment is hosted in MainActivity)
        val intent = Intent(requireActivity(), MainActivity::class.java)
        // Add flags to clear the back stack and start a new task
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish() // finish current activity to prevent going back to user home
    }


}
