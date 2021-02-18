package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_serie_detail.view.*

class DetailFragment : Fragment() {

    private var name: String=""
    private var author: String=""
    private var season: String=""
    private var description: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        name= arguments?.getString("name").toString()
        author= arguments?.getString("author").toString()
        season= arguments?.getString("season").toString()
        description= arguments?.getString("description").toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_serie_detail, container, false)

        rootView.nameTextView.text =name
        rootView.authorTextView.text =author
        rootView.seasonTextView.text =season
        rootView.descriptionTextView.text =description

        return rootView
    }

}
