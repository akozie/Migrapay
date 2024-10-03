package com.migrapay.androidmigrapay.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.migrapay.androidmigrapay.R
import com.migrapay.androidmigrapay.adapter.CountryAdapter
import com.migrapay.androidmigrapay.databinding.FragmentRechargeBinding
import com.migrapay.androidmigrapay.model.Country

class RechargeFragment : Fragment() {
    private lateinit var binding: FragmentRechargeBinding
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var selectedCountryTextView: TextView
    private lateinit var countryRecyclerView: RecyclerView
    private val countries =
        listOf(
            Country("🇳🇬", "Nigeria", "+234"),
            Country("🇲🇦", "Morocco", "+212"),
            Country("🇱🇾", "Libya", "+218"),
            Country("🇺🇸", "United States", "+1"),
            Country("🇨🇦", "Canada", "+1"),
            Country("🇬🇧", "United Kingdom", "+44"),
            Country("🇯🇵", "Japan", "+81"),
            Country("🇮🇳", "India", "+91"),
            Country("🇦🇺", "Australia", "+61"),
            Country("🇫🇷", "France", "+33"),
            Country("🇧🇷", "Brazil", "+55"),
            Country("🇩🇪", "Germany", "+49"),
            Country("🇿🇦", "South Africa", "+27"),
            Country("🇮🇹", "Italy", "+39"),
            Country("🇪🇸", "Spain", "+34"),
            Country("🇨🇳", "China", "+86"),
            Country("🇲🇽", "Mexico", "+52"),
            Country("🇷🇺", "Russia", "+7"),
            Country("🇰🇷", "South Korea", "+82"),
            Country("🇸🇦", "Saudi Arabia", "+966"),
            Country("🇦🇷", "Argentina", "+54"),
            Country("🇹🇷", "Turkey", "+90"),
            Country("🇳🇱", "Netherlands", "+31"),
            Country("🇸🇪", "Sweden", "+46"),
            Country("🇨🇭", "Switzerland", "+41"),
            Country("🇧🇪", "Belgium", "+32"),
            Country("🇦🇹", "Austria", "+43"),
            Country("🇳🇴", "Norway", "+47"),
            Country("🇩🇰", "Denmark", "+45"),
            Country("🇵🇹", "Portugal", "+351"),
            Country("🇬🇷", "Greece", "+30"),
            Country("🇵🇱", "Poland", "+48"),
            Country("🇮🇪", "Ireland", "+353"),
            Country("🇫🇮", "Finland", "+358"),
            Country("🇨🇴", "Colombia", "+57"),
            Country("🇨🇱", "Chile", "+56"),
            Country("🇵🇪", "Peru", "+51"),
            Country("🇻🇪", "Venezuela", "+58"),
            Country("🇳🇿", "New Zealand", "+64"),
            Country("🇮🇱", "Israel", "+972"),
            Country("🇵🇭", "Philippines", "+63"),
            Country("🇮🇩", "Indonesia", "+62"),
            Country("🇲🇾", "Malaysia", "+60"),
            Country("🇸🇬", "Singapore", "+65"),
            Country("🇹🇭", "Thailand", "+66"),
            Country("🇻🇳", "Vietnam", "+84"),
            Country("🇦🇪", "United Arab Emirates", "+971"),
            Country("🇶🇦", "Qatar", "+974"),
            Country("🇰🇼", "Kuwait", "+965"),
            Country("🇪🇬", "Egypt", "+20"),
            Country("🇰🇪", "Kenya", "+254"),
            Country("🇬🇭", "Ghana", "+233"),
            Country("🇿🇲", "Zambia", "+260"),
            Country("🇿🇼", "Zimbabwe", "+263"),
            Country("🇺🇬", "Uganda", "+256"),
            Country("🇹🇿", "Tanzania", "+255"),
            Country("🇪🇹", "Ethiopia", "+251"),
            Country("🇸🇳", "Senegal", "+221"),
            Country("🇩🇿", "Algeria", "+213"),
            Country("🇹🇳", "Tunisia", "+216"),
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recharge, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchCountry.setOnClickListener {
            if (binding.searchCountry.visibility == View.VISIBLE) {
                binding.phoneNumberLayout.visibility = View.VISIBLE
            }
        }

        val countryCodePicker = binding.countryCodePicker
        val editTextPhone = binding.editTextPhone

        // Example: Getting full phone number
        countryCodePicker.registerCarrierNumberEditText(editTextPhone)

        // Validate and restrict the length
        editTextPhone.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s != null) {
                        // Filter out all non-digit characters
                        val onlyDigits = s.toString().replace(Regex("[^\\d]"), "")

                        // Ensure the phone number doesn't exceed 10 digits
                        if (onlyDigits.length > 10) {
                            editTextPhone.setText(onlyDigits.substring(0, 10)) // Trim to 10 digits
                            editTextPhone.setSelection(10) // Move cursor to the end
                            binding.materialButton.visibility = View.VISIBLE
                            Log.d("SELECTED_COUNTRY", "${countryCodePicker.fullNumber}")
                        }
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int,
                ) {}
            },
        )

        // Example: Getting the full phone number on button click
        val fullNumber = countryCodePicker.fullNumberWithPlus
    }
}
