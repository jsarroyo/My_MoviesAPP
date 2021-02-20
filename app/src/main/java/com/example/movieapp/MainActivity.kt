package com.example.movieapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movieapp.model.Moovie
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.serie_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*

class MainActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    companion object{
        val mMovie:MutableList<Moovie> = ArrayList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMoovies();

        btnGoToAddMovie.setOnClickListener {
            val intent = Intent(this, AddMovie::class.java)
            startActivity(intent)
        }
        if (detailFrameLayout != null) {
            twoPane = true

            val fragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putString("name", "joker")
                    putString("author", "Todd Phillips")
                    putString("season", "1")
                    putString(
                        "description",
                        " Situada en los años 80′. Un cómico fallido es arrastrado a la locura, convirtiendo su vida en una vorágine de caos y delincuencia que poco a poco lo llevará a ser el psicópata criminal más famoso de Gotham."
                    )
                    putString(
                        "url",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/v0eQLbzT6sWelfApuYsEkYpzufl.jpg"
                    )
                }
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailFrameLayout, fragment)
                .commit()
        }

        setupRecyclerView(mRecyclerView)

    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SerieViewAdapter(this, twoPane, recyclerView)
    }

    class SerieViewAdapter(private val parentActivity: MainActivity,
                           /*private val values: List<Moovie>,*/
                           private val twoPane: Boolean,
                           private val recyclerView: RecyclerView):
        RecyclerView.Adapter<SerieViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->

                if (twoPane) {
                    val fragment = DetailFragment().apply {
                        arguments = Bundle().apply {
                            putString("name",mMovie[recyclerView.getChildAdapterPosition(v)].name)
                            putString("author",mMovie[recyclerView.getChildAdapterPosition(v)].author)
                            putString("season",mMovie[recyclerView.getChildAdapterPosition(v)].season.toString())
                            putString("description",mMovie[recyclerView.getChildAdapterPosition(v)].description)
                            putString("url",mMovie[recyclerView.getChildAdapterPosition(v)].url)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.detailFrameLayout, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, DetActivity::class.java).apply {
                        putExtra("name",mMovie[recyclerView.getChildAdapterPosition(v)].name)
                        putExtra("author",mMovie[recyclerView.getChildAdapterPosition(v)].author)
                        putExtra("season",mMovie[recyclerView.getChildAdapterPosition(v)].season.toString())
                        putExtra("description",mMovie[recyclerView.getChildAdapterPosition(v)].description)
                        putExtra("url",mMovie[recyclerView.getChildAdapterPosition(v)].url)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.serie_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val movie = mMovie[position]
            holder.mPosterImageView?.let {
                Glide.with(holder.itemView.context)
                    .load(movie.url)
                    .into(it)
            }

            with(holder.itemView) {
                tag = movie
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = mMovie.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val mPosterImageView: ImageView? = view.posterImageView
        }
    }

    private fun setMoovies(){

        mMovie.add(Moovie("joker", "Todd Phillips", 1,"Situada en los años 80′. Un cómico fallido es arrastrado a la locura, convirtiendo su vida en una vorágine de caos y delincuencia que poco a poco lo llevará a ser el psicópata criminal más famoso de Gotham.","https://image.tmdb.org/t/p/w185_and_h278_bestv2/v0eQLbzT6sWelfApuYsEkYpzufl.jpg"))

        mMovie.add(Moovie("Parasite", "Bong Joon-ho", 1,"anto Gi Taek (Song Kang Ho) como su familia están sin trabajo. Cuando su hijo mayor, Gi Woo (Choi Woo Shik), empieza a recibir clases particulares en casa de Park (Lee Sun Gyun), las dos familias, que tienen mucho en común pese a pertenecer a dos mundos totalmente distintos, comienzan una interrelación de resultados impresivibles.","https://cuevana3.io/wp-content/uploads/2019/08/parasite-20039-poster-211x300.jpg"))

        mMovie.add(Moovie("Bohemian Rhapsody", "Aaron McCusker",1,"Bohemian Rhapsody es una rotunda y sonora celebración de Queen, de su música y de su extraordinario cantante Freddie Mercury, que desafió estereotipos e hizo añicos tradiciones para convertirse en uno de los showmans más queridos del mundo. La película plasma el meteórico ascenso al olimpo de la música de la banda a través de sus icónicas canciones y su revolucionario sonido, su crisis cuando el estilo de vida de Mercury estuvo fuera de control, y su triunfal reunión en la víspera del Live Aid, en la que Mercury, mientras sufría una enfermedad que amenazaba su vida, lidera a la banda en uno de los conciertos de rock más grandes de la historia. Veremos cómo se cimentó el legado de una banda que siempre se pareció más a una familia, y que continúa inspirando a propios y extraños, soñadores y amantes de la música hasta nuestros días.","https://cuevana3.io/wp-content/uploads/2018/11/bohemian-rhapsody-7089-poster-208x300.jpg"))

        mMovie.add(Moovie("John Wick", "Chad Stahelski, David Leitch",1, "En Nueva York, John Wick, un asesino a sueldo retirado, vuelve otra vez a la acción para vengarse de los gángsters que le quitaron todo.","https://cuevana3.io/wp-content/uploads/2019/05/john-wick-3-parabellum-14464-poster-200x300.jpg"))

        mMovie.add(Moovie("The Flash", "Glen Winter",1 ,"Nueve meses después de que el laboratorio S.T.A.R. explotara, Barry despierta del coma y descubre que tiene el poder de la súper velocidad. Con la ayuda de su nuevo equipo, Barry, denominado ahora `Flash', luchará contra el crimen en Ciudad Central.","https://www.formulatv.com/images/series/posters/800/834/7_m1.jpg"))

    }
}


