package com.example.filmforfriends

import kotlinx.android.synthetic.main.for_element.*

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_view.*


class Films_Choice : Fragment() {
    val adapter = Adapter()



    companion object {

        fun newFragment(): Films_Choice {

            val fragment = Films_Choice() // создаём фрагмент
            val arguments = Bundle() // создаём коробочку для аргументов
            fragment.arguments = arguments // присоединяем аргументы к фрагменту
            return fragment // возвращаем фрагмент

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)
    }


    inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

        var genres = listOf<Int>(R.drawable.comedy, R.drawable.drama, R.drawable.horrors)


        override fun getItemCount(): Int {
            return genres?.size ?: 0
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.image_item, parent, false) // читаем макет и на его основе создаём view
            return ViewHolder(view)
        }

        /* а этот метод заполняет вьюшки содержимым */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val genre = genres!![position]
            Picasso.get().load(genre).fit().centerInside().into(holder.imageView)

            holder.itemView.setOnClickListener { // подписываемся на нажатие
                fragmentManager!!.beginTransaction()
                    .replace(R.id.Main_Activiti, DrammaFragment.newFragment(18))
                    .commit()
            }

        }

        /* класс вью холдера - он содержит ссылки на нужные нам вьюшки */
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView = itemView.findViewById<ImageView>(R.id.Image_item) // ImageView для постера
        }
    }
}
