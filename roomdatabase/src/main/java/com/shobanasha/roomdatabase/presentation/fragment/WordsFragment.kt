package com.shobanasha.roomdatabase.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shobanasha.roomdatabase.R


class WordsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
//        val adapter = WordListAdapter(view.context)
//
//        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
//            override fun onChanged() {
//                super.onChanged()
//                emptyView.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.INVISIBLE
//            }
//        })

//        recyclerview.adapter = adapter
//        recyclerview.layoutManager = LinearLayoutManager(activity)
//
//        viewModel.allWords.observe(this, Observer<List<Word>> { words ->
//            adapter.setWords(words)
//        })
    }
}