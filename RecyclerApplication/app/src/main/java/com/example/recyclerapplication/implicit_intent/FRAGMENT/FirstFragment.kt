import android.os.Bundle
import android.view.*

import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.PopupMenu
import com.example.recyclerapplication.R

class FirstFragment : Fragment() {

    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        listView = view.findViewById(R.id.listView)
        val staticValues = arrayOf("Item 1", "Item 2", "Item 3")

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, R.id.itemText, staticValues)
        listView.adapter = adapter

        listView.setOnItemLongClickListener { _, view, position, _ ->
            showPopupMenu(view, position)
            true
        }

        return view
    }

    private fun showPopupMenu(view: View, position: Int) {
        val optionsButton = view.findViewById<ImageButton>(R.id.optionsButton)
        val popupMenu = PopupMenu(requireContext(), optionsButton)
        popupMenu.menuInflater.inflate(R.menu.context_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_edit -> {
                    // Handle edit action
                    true
                }
                R.id.action_delete -> {
                    // Handle delete action
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}
