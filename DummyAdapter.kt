package com.notimp.adapter.researcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pg.mvvmrxjava.R
import com.pg.mvvmrxjava.databinding.RowAdapterBinding

import com.pg.rxjava.webservice.response.SymptomResponse


/**
 * Created by PG on 14/7/2018.
 */
class DummyAdapter(hashMapTherapy: ArrayList<SymptomResponse.SymptomList>) :
    RecyclerView.Adapter<DummyAdapter.ViewHolder>() {
    private var allTherapyText = ArrayList<SymptomResponse.SymptomList>()

    init {
        this.allTherapyText = hashMapTherapy
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent!!.context).inflate(R.layout.row_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = allTherapyText.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding() as RowAdapterBinding
        binding.symptomName.text = allTherapyText[position].Name
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: ViewDataBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        fun getBinding(): ViewDataBinding {
            return binding!!
        }

    }
}