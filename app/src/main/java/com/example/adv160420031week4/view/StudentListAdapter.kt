package com.example.adv160420031week4.view

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.adv160420031week4.R
import com.example.adv160420031week4.databinding.StudentListItemBinding
import com.example.adv160420031week4.model.Student
import com.example.adv160420031week4.util.loadImage


class StudentListAdapter(val studenList:ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view  = DataBindingUtil.inflate<StudentListItemBinding>(inflater,R.layout.student_list_item,parent,false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studenList[position]
        holder.view.listener = this
        /*holder.view.findViewById<TextView>(R.id.txtID).text = studenList[position].id //id
        holder.view.findViewById<TextView>(R.id.txtName).text = studenList[position].name //button
        holder.view.findViewById<Button>(R.id.btnDetail).setOnClickListener{
            val action = StudentListFragmentDirections.actionStudentDetail(studenList[position].id.toString(),studenList[position].name.toString()
            , studenList[position].dob.toString(), studenList[position].phone.toString())
            Navigation.findNavController(it).navigate(action)
        }
        var progressBar =holder.view.findViewById<ProgressBar>(R.id.progressBar)
        var imageView =holder.view.findViewById<ImageView>(R.id.imageView)
        imageView.loadImage(studenList[position].photoUrl,progressBar)*/
        /* val txtID = holder.view.findViewById<TextView>(R.id.txtID)
        txtID.text = studenList[position].id

        val txtName = holder.view.findViewById<TextView>(R.id.txtName)
        txtName.text = studenList[position].name

        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)
        btnDetail.setOnClickListener{
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)*/


    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    override fun getItemCount(): Int {
        return studenList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studenList.clear()
        studenList.addAll(newStudentList)
        notifyDataSetChanged()
    }

}

