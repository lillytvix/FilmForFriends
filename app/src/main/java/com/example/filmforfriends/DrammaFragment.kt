package com.example.filmforfriends

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.for_element.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DrammaFragment : Fragment() {
    val adapter = Adapter()




    companion object {
        private const val EXTRA_HORROR = "HORROR"

        fun newFragment(film: Film): DrammaFragment {

            val fragment = DrammaFragment() // создаём фрагмент
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
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onResume() {
        super.onResume()
        updateMovies()
    }

    fun updateMovies(){
        Database.service.requestmovies().enqueue(object : Callback<MovieResults> {
            override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                Toast.makeText(context, "Ошибка(((", LENGTH_LONG).show()
                Log.e("GamesFragment", "onFailure", t)
            }

            override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
                adapter.image = response.body()?.results
            }
        })
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
            Picasso.get().load("http://image.tmdb.org/t/p/w185" + film.poster_path ).fit().centerCrop().into(holder.imageView) // загружаем картинку

            holder.itemView.setOnClickListener { // подписываемся на нажатие

            }
        }

        /* класс вью холдера - он содержит ссылки на нужные нам вьюшки */
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView = itemView.findViewById<ImageView>(R.id.Image_item) // ImageView для постера
        }
    }

}
