package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeDetailBinding
import java.util.*

// 9.5 make CrimeDetailFragment inherit from the Fragment() class
class CrimeDetailFragment : Fragment() {

    // 9.7 implement binding by using the FragmentCrimeDetailBinding class
    private lateinit var binding: FragmentCrimeDetailBinding

    // 9.6 Add a property for the Crime instance
    private lateinit var crime: Crime

    // 9.6 Override Fragment.onCreate(Bundle?)
    // Fun facts: Fragment.onCreate(Bundle?) is a public function & you don't inflate a fragment's view here
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime(
            id = UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false
        )
    }

    // 9.7 Override onCreateView()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* The inflate() function now has 3 parameters:
            1. the same layoutInflater we used before (layoutInflater)
            2. the view's parent (container)
            3. whether to immediately add the inflated view to the view's parent (false)
        */
        binding =
            FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    // The onViewCreated(…) lifecycle callback is invoked immediately after onCreateView(…),
    //      which makes it a good place to wire up views since we want onCreateView(…) to be simple
    // 9.8 Add a listener to the EditText view
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            crimeTitle.doOnTextChanged { text, _, _, _ ->   // lambda arguments named _ are ignored; we only care about text
                crime = crime.copy(title = text.toString())
            }

            // 9.9 Set Button text
            crimeDate.apply {
                text = crime.date.toString()
                isEnabled = false       // is false bc we don't want the user pressing it
            }

            // 9.10 Listen for Checkbox changes
            crimeSolved.setOnCheckedChangeListener { _, isChecked ->
                crime = crime.copy(isSolved = isChecked)
            }
        }
    }
}