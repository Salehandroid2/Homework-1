package com.example.onboarding
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
class Fragment3 : Fragment() {
    interface Fragment3Listener { fun onCheckboxStateChanged(isChecked: Boolean) }
    private var listener: Fragment3Listener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? Fragment3Listener
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_3, container, false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvUserName = view.findViewById<TextView>(R.id.tvUserName)
        val cbAgree = view.findViewById<CheckBox>(R.id.cbAgree)
        val btnFinish = view.findViewById<Button>(R.id.btnFinish)
        val userName = arguments?.getString("USER_NAME") ?: "User"
        tvUserName.text = userName
        btnFinish.isEnabled = false
        cbAgree.setOnCheckedChangeListener { _, isChecked ->
            listener?.onCheckboxStateChanged(isChecked)
            btnFinish.isEnabled = isChecked
            btnFinish.alpha = if (isChecked) 1.0f else 0.5f
            btnFinish.text = if (isChecked) "Finish" else "Continue"
        }
        btnFinish.setOnClickListener {
            Toast.makeText(requireContext(), "Welcome, $userName!", Toast.LENGTH_LONG).show()
        }
    }
    override fun onDetach() { super.onDetach(); listener = null }
    companion object {
        fun newInstance(userName: String): Fragment3 {
            val f = Fragment3()
            val args = Bundle()
            args.putString("USER_NAME", userName)
            f.arguments = args
            return f
        }
    }
}