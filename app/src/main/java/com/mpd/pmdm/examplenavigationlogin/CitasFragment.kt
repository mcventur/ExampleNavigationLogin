package com.mpd.pmdm.examplenavigationlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.mpd.pmdm.examplenavigationlogin.adapter.RecyclerCitasAdapter
import com.mpd.pmdm.examplenavigationlogin.data.CitasSource
import com.mpd.pmdm.examplenavigationlogin.databinding.FragmentCitasBinding

class CitasFragment : Fragment() {
    private var _binding: FragmentCitasBinding? = null
    private val binding get() = _binding!!
    private val args: CitasFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setearTextoTitulo()
        inicializarRecycler()
    }

    private fun setearTextoTitulo() {
        binding.tvTitleCitas.text = args.usuario
    }

    private fun inicializarRecycler() {
        binding.recyclerCitas.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerCitas.adapter = RecyclerCitasAdapter(CitasSource.citasRandom)
    }
}