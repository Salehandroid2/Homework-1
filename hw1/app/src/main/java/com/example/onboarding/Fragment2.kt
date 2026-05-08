package com.example.onboarding
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
class Fragment2 : Fragment() {
    interface Fragment2Listener { fun onFragment2Continue(name: String) }
    private var listener: Fragment2Listener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? Fragment2Listener
            ?: throw ClassCastException("$context must implement Fragment2Listener")
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_2, container, false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etName = view.findViewById<EditText>(R.id.etName)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        arguments?.getString("USER_NAME")?.let { etName.setText(it) }
        btnContinue.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            if (name.isEmpty()) { etName.error = "Name is required"; return@setOnClickListener }
            if (email.isEmpty()) { etEmail.error = "Email is required"; return@setOnClickListener }
            listener?.onFragment2Continue(name)
        }
    }
    override fun onDetach() { super.onDetach(); listener = null }
    companion object {
        fun newInstance(userName: String): Fragment2 {
            val f = Fragment2()
            val args = Bundle()
            args.putString("USER_NAME", userName)
            f.arguments = args
            return f
        }
    }
}