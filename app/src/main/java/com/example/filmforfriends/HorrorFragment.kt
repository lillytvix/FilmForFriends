package com.example.filmforfriends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class HorrorFragment : Fragment() {
    val adapter = Adapter()

    companion object {
        private const val EXTRA_HORROR = "HORROR"

        /* метод для создания фрагмента, который будет отображать game */
        fun newFragment(film: Film): HorrorFragment {

            val fragment = HorrorFragment() // создаём фрагмент
            val arguments = Bundle() // создаём коробочку для аргументов
            arguments.putSerializable(EXTRA_HORROR, film) // кладём игру в коробочку
            fragment.arguments = arguments // присоединяем аргументы к фрагменту
            return fragment // возвращаем фрагмент
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.for_element, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = arguments!!.getSerializable(EXTRA_HORROR) as Film
        Picasso.get().load(film.image.original).fit().centerCrop().into()
    }


    inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

        var image: List<Film>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount(): Int {
            return image?.size ?: 0
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(context) // получаем из контекста создаватель макетов inflater
            val view = inflater.inflate(R.layout.image_item, parent, false) // читаем макет и на его основе создаём view
            return ViewHolder(view)
        }

        /* а этот метод заполняет вьюшки содержимым */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val film = image!![position]
            Picasso.get().load(film.original).fit().centerCrop().into(holder.imageView) // загружаем картинку

            holder.itemView.setOnClickListener { // подписываемся на нажатие
                val fragment = HorrorFragment.newFragment(film)
            }
        }

        /* класс вью холдера - он содержит ссылки на нужные нам вьюшки */
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView = itemView.findViewById<ImageView>(R.id.screen) // ImageView для постера
        }
    }

}
