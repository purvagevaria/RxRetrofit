package com.notimp.listener

import android.view.View



interface OnRecyclerViewItemClickListener
{
    fun onItemClick(view: View, position: Int)

    fun onItemChildClick(view: View, position: Int, T: Any)
}
