package CEP.PolisTecnics.m13appandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ElementAdapter(context: Context, val layout: Int, val elements: MutableList<Element>) :
    ArrayAdapter<Element>(context, layout, elements) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View

        if (convertView != null) {
            view = convertView
        } else {
            view =
                LayoutInflater.from(getContext()).inflate(layout, parent, false)
        }
        bindElement(view, elements[position])
        return view
    }

    fun bindElement(view: View, element: Element) {
        val imgElement = view.findViewById(R.id.ImgListElement) as ImageView
        imgElement.setImageResource(element.image)
        val elementNom = view.findViewById(R.id.NomListElement) as TextView
        elementNom.text = element.nomElement
    }
}