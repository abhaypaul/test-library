package com.lilac.reedbi_seller.ui.test

import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abhay.testlibrary.R
import com.abhay.testlibrary.databinding.ItemStepperBinding

class StepperAdapter(val items:List<String>,var selectedPosition:Int ):RecyclerView.Adapter<StepperAdapter.ViewHolder>(){
    private  val list= mutableListOf<StepperModel>()
    init {
        list.clear()
           for (x in items.indices)
           {
               val position=when(x)
               {
                   0->StepIndex.START
                   items.size-1->StepIndex.END
                   else-> StepIndex.MID
               }

               val selected=when(x)
               {
                   in 0..selectedPosition->true
                   else ->false

               }
               list.add(StepperModel(position,items[x],selected))
           }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_stepper,parent,false))
    }
    override fun getItemCount(): Int {
        return list.size

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.scrollToPosition(selectedPosition)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.step=list[position]
    }

    inner class ViewHolder(var binding: ItemStepperBinding):RecyclerView.ViewHolder(binding.root)
    {
        init {
            binding.stepCheck.setOnCheckedChangeListener(object:CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if(isChecked)selectedPosition=adapterPosition
                    notifyDataSetChanged()
                }
            })

        }
    }




}