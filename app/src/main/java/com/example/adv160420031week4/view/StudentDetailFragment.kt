package com.example.adv160420031week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.adv160420031week4.R
import com.example.adv160420031week4.model.Student
import com.example.adv160420031week4.viewmodel.DetailViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*

class StudentDetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
           super.onViewCreated(view, savedInstanceState)
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentID)
            viewModel.studentLD.observe(viewLifecycleOwner, Observer {


                val txtID = view.findViewById<TextInputEditText>(R.id.txtID)
                val txtName = view.findViewById<TextInputEditText>(R.id.txtName)
                val txtBod = view.findViewById<TextInputEditText>(R.id.txtBod)
                val txtPhone = view.findViewById<TextInputEditText>(R.id.txtPhone)

                txtID.setText(it.id.toString())
                txtName.setText(it.name.toString())
                txtBod.setText(it.dob.toString())
                txtPhone.setText(it.phone.toString())


                var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
                val student = viewModel.studentLD.value
                student?.let{
                    imageView2.loadImage(student.photoUrl, progressLoad)
                }


            })
        }


}