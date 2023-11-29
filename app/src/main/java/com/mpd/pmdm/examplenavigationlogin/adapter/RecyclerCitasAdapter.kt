package com.mpd.pmdm.examplenavigationlogin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpd.pmdm.examplenavigationlogin.R
import com.mpd.pmdm.examplenavigationlogin.databinding.ItemCitaBinding
import com.mpd.pmdm.examplenavigationlogin.model.Cita

/*
Sigo lo hecho en
https://github.com/mcventur/MensajesRecyclerView/blob/main/app/src/main/java/com/mpd/pmdm/mensajesrecyclerview/adapter/MessagesRVAdapter.kt
 */
class RecyclerCitasAdapter(val datos: List<Cita>) :
    RecyclerView.Adapter<RecyclerCitasAdapter.CitasViewHolder>() {
    class CitasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCitaBinding.bind(view)

        val textoCitaTextView = binding.tvCitaTexto
        val autorCitaTextView = binding.tvCitaAutor

        fun vincularDatos(cita: Cita) {
            textoCitaTextView.text = cita.texto
            autorCitaTextView.text = cita.autor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cita, parent, false)
        return CitasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datos.count()
    }

    override fun onBindViewHolder(holder: CitasViewHolder, position: Int) {
        val message = datos[position]
        holder.vincularDatos(message)
    }
}