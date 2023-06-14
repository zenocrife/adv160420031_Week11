package com.example.adv160420031week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.adv160420031week4.R
import com.example.adv160420031week4.viewmodel.ListViewModel

class StudentListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.textErr)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view?.findViewById<RecyclerView>(R.id.recView)?.visibility = View.GONE
                view?.findViewById<ProgressBar>(R.id.progressLoad)?.visibility = View.VISIBLE
            } else {
                view?.findViewById<RecyclerView>(R.id.recView)?.visibility = View.VISIBLE
                view?.findViewById<ProgressBar>(R.id.progressLoad)?.visibility = View.GONE
            }
        })



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        view.findViewById<RecyclerView>(R.id.recView).layoutManager = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.recView).adapter = studentListAdapter
        observeViewModel()

        //buat jadi satu
        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefresh.setOnRefreshListener {

            view.findViewById<RecyclerView>(R.id.recView).visibility = View.GONE
            view.findViewById<TextView>(R.id.textErr).visibility = View.GONE
            view.findViewById<ProgressBar>(R.id.progressLoad).visibility = View.VISIBLE

            viewModel.refresh()

            view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).isRefreshing = false
        }


    }


}