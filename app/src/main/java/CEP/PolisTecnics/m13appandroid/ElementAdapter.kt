package CEP.PolisTecnics.m13appandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ElementAdapter(val elements: List<Element>) :
    RecyclerView.Adapter<ElementAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_itema4, parent, false)
        return ElementViewHolder(view)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val element = elements[position]
        holder.bindElement(element)
    }

    override fun getItemCount(): Int = elements.size

    class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindElement(element: Element) {
            val imgElement = itemView.findViewById<ImageView>(R.id.ImgListElement)
            imgElement.setImageResource(element.image)
            val elementNom = itemView.findViewById<TextView>(R.id.NomListElement)
            elementNom.text = element.nomElement//comento per provar objecte element segons json
            //elementNom.text = element.nomElementCA//comento per provar objecte element segons json
        }
    }
}